package BussinessLogic;

import java.util.ArrayList;

/**
 * Clasa care extinde MenuItem
 * @author Ioneasa Cristina
 */

public class CompositeProduct extends MenuItem{
    private String title;
    private int price;
    ArrayList<MenuItem> menuItems;

    public CompositeProduct(String title, ArrayList<MenuItem> menuItems) {
        this.title = title;
        this.menuItems = menuItems;
    }

    public int computePrice(){
        int p = 0;
        for(MenuItem baseProduct : menuItems)
            p += baseProduct.computePrice();

        price = p;
        super.setPrice(p);

        return p;
    }

    public double computeRating(){
        float rating=0;
        for(MenuItem item: menuItems){
            rating+=item.computeRating();
        }
        return rating/menuItems.size();
    }

    public  int computeCalories(){
        int calories = 0;
        for(MenuItem item: menuItems)
            calories += item.computeCalories();

        return calories;
    }

    public  int computeProteins(){
        int proteins = 0;
        for(MenuItem item: menuItems)
            proteins += item.computeProteins();

        return proteins;
    }

    public  int computeFats(){
        int fats = 0;
        for(MenuItem item: menuItems)
            fats += item.computeFats();

        return fats;
    }

    public int computeSodium(){
        int sodium = 0;
        for(MenuItem item: menuItems)
            sodium += item.computeSodium();

        return sodium;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public String toString() {
        return "CompositeProduct{" +
                "title='" + title + '\'' +
                ", price=" + computePrice() +
                "raiting=" + computeRating()+
                ", calories=" + computeCalories() +
                ", protein=" + computeProteins() +
                ", fat=" + computeFats() +
                ", sodium=" + computeSodium() +
                "}\n";
    }
}
