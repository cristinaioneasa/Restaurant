package BussinessLogic;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Ioneasa Cristina
 */

public interface DeliveryServiceProcessing {

    void addMenuItem(BaseProduct product);
    void updateMenuItem(BaseProduct product);
    void deleteMenuItem(String product);
    void importProducts();
    void createOrder(int orderId, int clientId, Date date, ArrayList<MenuItem> items);
    void createMenuItem(ArrayList<MenuItem> products, String name);
    String searchByName(String str);
    String searchByRating(double rating);
    String searchByCalories(int calories);
    String searchByProteins(int proteins);
    String searchByFats(int fats);
    String searchBySodium(int sodium);
    String searchByPrice(int price);
    String viewMenu();
    String generateReportType1(int startHour, int endHour);
    String generateReportType2(int number);
    String generateReportType3(int times, int value);
    String generateReportType4(int day);
}
