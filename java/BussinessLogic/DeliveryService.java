package BussinessLogic;

import DataAccess.FileWriter1;

import java.io.IOException;
import java.io.Serializable;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author  Ioneasa Cristina
 * Clasa care implementeaza metodele din interfata DeliveryServiceProcessing
 * @invariant isWellFormed();
 */

public class DeliveryService  extends Observable implements DeliveryServiceProcessing, Serializable {

    private LinkedHashMap<Order, ArrayList<MenuItem>> ordersMap;
    private ArrayList<MenuItem> menu;
    private ArrayList<Order> orders;
    private ArrayList<Client> clients;
    private Administrator administrator;
    private int price = 0;
    private String products = "";

    /**
     * Contructor
     */
    public DeliveryService() {
        ordersMap = new LinkedHashMap<Order, ArrayList<MenuItem>>();
        orders = new ArrayList<>();
        clients = new ArrayList<>();
    }

    /**
     * Adauga un nou produs in lista
     * @pre product != null && isWellFormed()
     * @post isWellFormed() && menu.size() == size + 1
     * @param product
     */
    @Override
    public void addMenuItem(BaseProduct product) {
        assert product != null && isWellFormed();

        int oldSize = menu.size();
        menu.add(product);

        assert isWellFormed() && menu.size() == oldSize + 1;
    }

    /**
     * Actualizeaza datele despre un produs
     * @pre product != null && isWellFormed()
     * @post  && isWellFormed()
     * @param product
     */
    @Override
    public void updateMenuItem(BaseProduct product) {
        assert product != null && isWellFormed();

        for (MenuItem prod : menu) {
            if (prod.getTitle().equals(product.getTitle())) {
                menu.remove(prod);
                menu.add(product);
            }
        }
        assert isWellFormed();
    }

    /**
     * Sterge un produs din lista
     * @pre product != null && isWellFormed()
     * @post isWellFormed() && menu.size() == oldSize - 1
     * @param product
     */
    @Override
    public void deleteMenuItem(String product) {
        assert product != null && isWellFormed();

        int oldSize = menu.size();
        menu.removeIf(m -> m.getTitle().equals(product));

        assert isWellFormed() && menu.size() == oldSize - 1;
    }

    /**
     * Adauga un client nou in lista de clienti
     * @pre clientID > 0 && name != null && password != null;
     * @post isWellFormed();
     * @param clientID
     * @param name
     * @param password
     */
    public void newClient(int clientID, String name, String password) {
        assert clientID > 0 && name != null && password != null;

        clients.add(new Client(clientID, name, password));

        assert isWellFormed();
    }

    /**
     * Se iau datele (produsele) dintr-un fisier trimis ca parametru si se pun intr-o lista care va fi returnata
     * @param filePath
     * @return products
     */
    public ArrayList<MenuItem> ReadFromFile(String filePath) {
        int existaDuplicate = 0;
        ArrayList<MenuItem> products = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            List<List<String>> items = lines.skip(1).map(line -> Arrays.asList(line.split(","))).collect(Collectors.toList());

            for(List<String> item : items){
                MenuItem product = new BaseProduct(item.get(0), Double.parseDouble(item.get(1)), Integer.parseInt(item.get(2)), Integer.parseInt(item.get(3)), Integer.parseInt(item.get(4)), Integer.parseInt(item.get(5)), Integer.parseInt(item.get(6)));

                for(MenuItem prod : products){
                    //se verifica sa nu existe duplicate
                    if(product.getTitle().equals(prod.getTitle()))
                        existaDuplicate = 1;
                }
                if(existaDuplicate == 0)
                    products.add(product);
                else existaDuplicate = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * importa produsele din excel
     * @post isWellFormed()
     */
    @Override
    public void importProducts() {
        menu = new ArrayList<>();
        menu.addAll(ReadFromFile("products.csv"));

        assert isWellFormed();
    }

    /**
     * creeaza o noua comanda
     * @pre orderId > 0 && clientId > 0 && items.size() != 0 && isWellFormed()
     * @post price > 0 && products != null && isWellFormed()
     * @param orderId
     * @param clientId
     * @param date
     * @param items
     */
    @Override
    public void createOrder(int orderId, int clientId, Date date, ArrayList<MenuItem> items) {
        assert orderId > 0 && clientId > 0 && items.size() != 0 && isWellFormed();

        Order newOrder = new Order(orderId, clientId, date);
        orders.add(newOrder);
        ordersMap.put(newOrder, items);

        for(MenuItem it : items){
            price += it.computePrice();
            products += it.getTitle() + "\n";
        }

        setChanged();
        notifyObservers(newOrder);
        FileWriter1.bill(newOrder, ordersMap.get(newOrder));

        assert price > 0 && products != null && isWellFormed();
    }

    /**
     * Cauta un client in lista dupa nume
     * @pre name != null
     * @post isWellFormed();
     * @param name
     * @return
     */
    public String searchClient(String name){
        assert name != null;

        for(Client c: clients){
            if(c.getName().equals(name))
                return c.getPassword();
        }
        assert isWellFormed();
        return null;
    }

    /**
     * Cauta produsele care au numele dat ca parametru
     * @pre string != null
     * @post isWellFormed();
     * @param string
     * @return un string cu toate produsele cautate
     */
    public String searchByName(String string) {
        assert string != null;

        String result = "";
        List<MenuItem> products = menu.stream().filter(p -> p.getTitle().contains(string)).collect(Collectors.toList());

        for(MenuItem p: products)
            result += p.toString();

        assert isWellFormed();
        return result;
    }

    /**
     * Cauta produsele care au rating-ul dat ca parametru
     * @pre rating >= 0
     * @post isWellFormed();
     * @param rating
     * @return un string cu toate produsele cautate
     */
    public String searchByRating(double rating) {
        assert rating >= 0;

        String result = "";
        List<MenuItem> products = menu.stream().filter(p -> p.computeRating() == rating).collect(Collectors.toList());

        for(MenuItem p: products)
            result += p.toString();

        assert isWellFormed();
        return result;
    }

    /**
     * Cauta produsele care au nr de calorii dat ca parametru
     * @pre calories > 0
     * @post isWellFormed();
     * @param calories
     * @return un string cu toate produsele cautate
     */
    public String searchByCalories(int calories) {
        assert calories > 0;

        String result = "";
        List<MenuItem> products = menu.stream().filter(p -> ( p).computeCalories() == calories).collect(Collectors.toList());

        for(MenuItem p: products)
            result += p.toString();

        assert isWellFormed();
        return result;
    }

    /**
     * Cauta produsele care au nr grame de proteine dat ca parametru
     * @pre proteins >= 0
     * @post isWellFormed();
     * @param proteins
     * @return un string cu toate produsele cautate
     */
    public String searchByProteins(int proteins) {
        assert proteins >= 0;

        String result = "";
        List<MenuItem> products = menu.stream().filter(p -> p.computeProteins() == proteins).collect(Collectors.toList());

        for(MenuItem p: products)
            result += p.toString();

        assert isWellFormed();
        return result;
    }

    /**
     * Cauta produsele care au nr de grame de grasimi dat ca parametru
     * @pre fats >= 0
     * @post isWellFormed();
     * @param fats
     * @return un string cu toate produsele cautate
     */
    public String searchByFats(int fats) {
        assert fats >= 0;

        String result = "";
        List<MenuItem> products = menu.stream().filter(p -> p.computeFats() == fats).collect(Collectors.toList());

        for(MenuItem p: products)
            result += p.toString();

        assert isWellFormed();
        return result;
    }

    /**
     * Cauta produsele care au nr de sodium dat ca parametru
     * @pre sodium >= 0
     * @post isWellFormed();
     * @param sodium
     * @return un string cu toate produsele cautate
     */
    public String searchBySodium(int sodium) {
        assert sodium >= 0;

        String result = "";
        List<MenuItem> products = menu.stream().filter(p ->  p.computeSodium() == sodium).collect(Collectors.toList());

        for(MenuItem p: products)
            result += p.toString();

        assert isWellFormed();
        return result;
    }

    /**
     * Cauta produsele care au pretul dat ca parametru
     * @pre price >= 0
     * @post isWellFormed();
     * @param price
     * @return un string cu toate produsele cautate
     */
    public String searchByPrice(int price) {
        assert price >= 0;

        String result = "";
        List<MenuItem> products = menu.stream().filter(p -> p.computePrice() == price).collect(Collectors.toList());

        for(MenuItem p: products)
            result += p.toString();

        assert isWellFormed();
        return result;
    }

    /**
     * pune intr-un string toate produsele din meniul
     * @post isWellFormed();
     * @return acel string
     */
    public String viewMenu(){
        String result = "";

        for(MenuItem m: menu){
            result += m.toString();
        }
        assert isWellFormed();
        return result;
    }

    /**
     * cauta un produs dupa nume
     * @post isWellFormed();
     * @param name
     * @return acel produs daca l-am gasit, altfel null
     */
    public MenuItem searchProduct(String name){
        for(MenuItem p: menu){
            if(p.getTitle().equals(name)){
                return p;
            }
        }
        assert isWellFormed();
        return null;
    }

    /**
     * Creaza un produs compus
     * @pre isWellFormed() && products != null
     * @post isWellFormed() && menu.size() == oldSize + 1
     * @param products
     * @param name
     */
    public void createMenuItem(ArrayList<MenuItem> products, String name) {
        assert isWellFormed() && products != null;
        int oldSize = menu.size();

        MenuItem newItem = new CompositeProduct(name, products);
        newItem.setTitle(name);
        newItem.computePrice();
        newItem.computeCalories();
        newItem.computeFats();
        newItem.computeProteins();
        newItem.computeRating();
        newItem.computeSodium();

        menu.add(newItem);

        assert isWellFormed() && menu.size() == oldSize + 1;
    }

    /**
     * Genereaza un raport cu toate comenzile date intr-un interval orar dat
     * @pre startHour >= 0 && startHour <= 23 && endHour >= 0 && endHour <= 23
     *
     * @param startHour
     * @param endHour
     * @return
     */
    @Override
    public String generateReportType1(int startHour, int endHour) {
        assert startHour >= 0 && startHour <= 23 && endHour >= 0 && endHour <= 23;

        String result = "";
        List<Order> ord = orders
                .stream()
                .filter(o -> o.getOrderDate().getHours() >= startHour && o.getOrderDate().getHours() <= endHour)
                .collect(Collectors.toList());

        for (Order o : ord)
            result += o.toString();

        result += "\n";
        FileWriter1.generateRaport(result, "ReportTimeInterval");

        return result;
    }

    /**
     * Genereaza un raport cu produsele cele mai comandate
     * @pre number >= 0;
     * @param number
     * @return
     */
    @Override
    public String generateReportType2(int number) {
        assert number >= 0;
        long f = 0;
        String result = "";

        for (MenuItem item : menu) {
            f = ordersMap.entrySet()
                    .stream().filter(p -> p.getValue().contains(item)).count();
            if (f >= number)
                result += item.getTitle() + "\n";
        }
        FileWriter1.generateRaport(result, "ReportPopularProducts");
        return result;
    }

    /**
     * Genereaza un raport cu clientii care au comandat cel mai mult
     * @pre times >= 0 && value >= 0
     * @param times
     * @param value
     * @return
     */
    @Override
    public String generateReportType3(int times, int value) {
        assert times >= 0 && value >= 0;
        String result = "";

        for (Client c : clients) {
            long nr = ordersMap.entrySet()
                    .stream()
                    .filter(p -> p.getKey().getClientId() == c.getClientID()).count();

            if (nr >= times) {
                int val = 0;
                List<Order> filtered = orders
                        .stream().filter(p -> p.getClientId() == c.getClientID())
                        .collect(Collectors.toList());

                for (Order o : filtered) {
                    List<MenuItem> list = ordersMap.get(o);
                    for(MenuItem item:list)
                        val+=item.computePrice();
                }
                if (val >= value) {
                    result += c.getName() + "\n";
                }
            }
        }
        FileWriter1.generateRaport(result, "ReportLoyalClients");
        return result;
    }

    /**
     * Genereaza un raport cu comenzile date intr-o anumita zi
     * @pre day >= 1 && day <= 31
     * @param day
     * @return
     */
    @Override
    public String generateReportType4(int day) {
        assert day >= 0 && day <= 6;
        String result = "";
        List<MenuItem> products = ordersMap.entrySet()
                .stream()
                .filter(p -> p.getKey().getOrderDate().getDay() == day).flatMap(p -> p.getValue().stream())
                .collect(Collectors.toList());

        for (MenuItem itm : products) {
            long times = ordersMap.entrySet()
                    .stream().filter(p -> p.getValue().contains(itm)).count();

            result += itm.getTitle() + " ,times: " + times + "\n";
        }
        FileWriter1.generateRaport(result, "ReportDaysProducts");
        return result;
    }

    public boolean isWellFormed(){
        if(ordersMap == null || menu == null || clients == null)
            return false;

        for(MenuItem item: menu)
            if(item == null)
                return false;

        for(Map.Entry<Order, ArrayList<MenuItem>> order : ordersMap.entrySet())
            if(order == null)
                return false;

        for(Client client: clients)
            if(client == null)
                return false;

        return true;
    }

    public void newAdmin(String name, String password){
        assert name!=null && password !=null;
        administrator = new Administrator(name, password);
    }

    public Map<Order, ArrayList<MenuItem>> getOrders() {
        return ordersMap;
    }

    public void setOrders(LinkedHashMap<Order, ArrayList<MenuItem>> orders) {
        this.ordersMap = orders;
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<MenuItem> menu) {
        this.menu = menu;
    }

    public ArrayList<Order> getOrd() {
        return orders;
    }

    public void setOrd(ArrayList<Order> ord) {
        this.orders = ord;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public Administrator getAdministrator(){
        return administrator;
    }

    public Map<Order, ArrayList<MenuItem>> getOrdersInfo(){
        return ordersMap;
    }

    public String getAdminName(){
        if(administrator==null)
            return null;

        return administrator.getName();
    }

    public void setAdministratorName(String name){
        administrator.setName(name);
    }

}