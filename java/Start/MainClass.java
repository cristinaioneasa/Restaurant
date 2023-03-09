package Start;


import BussinessLogic.DeliveryService;
import DataAccess.Serializator;
import Presentation.AdministratorGUI;
import Presentation.ClientGUI;
import Presentation.EmployeeGUI;
import Presentation.StartGUI;

/**
 * @author Ioneasa Cristina
 */

public class MainClass {

    public static void main(String[] args){

        DeliveryService ds = Serializator.deserialize();
        EmployeeGUI emp = new EmployeeGUI(ds);
        AdministratorGUI admin = new AdministratorGUI(ds);
        ClientGUI client = new ClientGUI(ds, emp);
        StartGUI main = new StartGUI(ds, admin, emp, client);
        main.getFrame().setVisible(true);

        ds.addObserver(emp);
    }
}

