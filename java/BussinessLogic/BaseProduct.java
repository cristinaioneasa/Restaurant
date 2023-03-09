package BussinessLogic;

/**
 * Clasa care extinde MenuItem
 * @author Ioneasa Cristina
 */
public class BaseProduct extends MenuItem{
    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    public BaseProduct(String title, double raiting, int calories, int protein, int fat, int sodium, int  price) {
        this.title = title;
        this.rating = raiting;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public double getRaiting() {
        return rating;
    }

    public void setRaiting(double rating) {
        this.rating = rating;
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

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object product){
        MenuItem p = (MenuItem) product;
        if(this.title.equals(p.getTitle()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "BaseProduct{" +
                "title= " + getTitle() +
                ", raiting=" + rating +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", price=" + price +
                "}\n";
    }

    public int computePrice(){
        return price;
    }

    public  double computeRating(){
        return rating;
    }

    public  int computeCalories(){
        return calories;
    }

    public  int computeProteins(){
        return protein;
    }

    public  int computeFats(){
        return fat;
    }

    public int computeSodium(){
        return sodium;
    }
}
