package DataAccess;

import BussinessLogic.DeliveryService;

import java.io.*;

/**
 * @author Ioneasa Cristina
 */

public class Serializator {

    public static void serialize(DeliveryService service) {
        try {
            FileOutputStream fileOut = new FileOutputStream("DeliveryService.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(service);

            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DeliveryService deserialize() {
        DeliveryService service;
        try {
            FileInputStream fileIn = new FileInputStream("DeliveryService.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            service = (DeliveryService) in.readObject();

            in.close();
            fileIn.close();

            return service;
        }catch (IOException i) {
            service = new DeliveryService();
            serialize(service);
            return service;
        } catch (ClassNotFoundException c) {
            System.out.println("Error: class not found");
            c.printStackTrace();
            return new DeliveryService();
        }
    }
}
