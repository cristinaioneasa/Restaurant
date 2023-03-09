package BussinessLogic;

import java.io.Serializable;

/**
 * Clasa MenuItem ce are ca atribute toate datele despre un produs
 * @author Ioneasa Cristina
 */

public abstract class MenuItem implements Serializable {
    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    public MenuItem(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRaiting() {
        return rating;
    }

    public void setRaiting(double raiting) {
        this.rating = raiting;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public abstract int computePrice();
    public abstract double computeRating();
    public abstract int computeCalories();
    public abstract int computeProteins();
    public abstract int computeFats();
    public abstract int computeSodium();


    @Override
    public String toString() {
        return "MenuItem{" +
                "title=" + title + '\'' +
                ", rating=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", price=" + price +
                '}';
    }
}
