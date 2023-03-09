package Presentation;

import BussinessLogic.DeliveryService;
import BussinessLogic.Order;
import BussinessLogic.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Ioneasa Cristina
 */

public class EmployeeGUI extends JFrame implements Observer {
    private JFrame frame;
    private JLabel titleLabel;
    private JTextArea result;
    private JScrollPane scroll;

    private DeliveryService service;

    private StartGUI main;

    public JFrame getFrame() {
        return frame;
    }

    public EmployeeGUI(DeliveryService service) {

        frame = new JFrame();

        frame.getContentPane().setBackground(Color.CYAN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(500, 150, 400, 400);
        frame.getContentPane().setLayout(null);

        titleLabel = new JLabel("Hello! A new order has been placed.");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        titleLabel.setBounds(50, 35, 300, 50);
        frame.getContentPane().add(titleLabel);

        result = new JTextArea();
        result.setEditable(false);

        scroll = new JScrollPane(result);
        scroll.setBounds(40, 100, 300, 200);
        scroll.setViewportView(result);
        frame.getContentPane().add(scroll);
    }
    @Override
    public void update(Observable observable, Object object) {
        JOptionPane.showMessageDialog(null,"The order was has been taken");

        String res="";
        float price = 0;
        DeliveryService service = (DeliveryService) observable;
        Order order = (Order) object;

        res += "Order ID: " + order.getOrderId()+"\n";
        res += "Client ID: " + order.getClientId()+"\n";
        res += "Date: "+ order.getOrderDate()+"\n";
        res += "Products: " + "\n";

        for(MenuItem item: service.getOrdersInfo().get(order)){
            res += item.getTitle()+ ", price: " + item.computePrice() + "\n";
            price += item.computePrice();
        }
        res += "\n";
        res += "TOTAL: " + price;

        result.setText(res);
        this.setVisible(true);
    }

}

