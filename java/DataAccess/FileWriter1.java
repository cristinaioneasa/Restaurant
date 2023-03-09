package DataAccess;

import BussinessLogic.MenuItem;
import BussinessLogic.Order;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Ioneasa Cristina
 */

public class FileWriter1 {

    public static void bill(Order o, List<MenuItem> p) {
        FileWriter fisier;
        int total = 0;

        try{
            fisier = new FileWriter("Order no." + o.getOrderId() + ".txt");
            String contentOfBill = "Order no" + o.getOrderId() + "\nClient: " + o.getClientId() + "\nDate: " + o.getOrderDate() + "\n\nProducts:";
            for(MenuItem prod: p) {
                contentOfBill += "\n" + prod.getTitle() + "  " + prod.computePrice();
                total += prod.computePrice();
            }
            contentOfBill += "\n\n TOTAL to pay: " + total;
            fisier.append(contentOfBill);
            fisier.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateRaport(String s, String raport_type) {
        FileWriter fisier;
        try{
            fisier = new FileWriter(raport_type + ".txt");
            fisier.append(s);
            fisier.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}

