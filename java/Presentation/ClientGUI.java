package Presentation;

import BussinessLogic.DeliveryService;
import BussinessLogic.MenuItem;
import DataAccess.Serializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Ioneasa Cristina
 */

public class ClientGUI extends JFrame {

    private JFrame frame;
    private JLabel titleLabel;
    private JButton viewMenuBtn;
    private JLabel searchLabel;
    private JLabel createOrder;
    private JLabel productName;
    private JTextField name;
    private JTextField rating;
    private JTextField calories;
    private JTextField proteins;
    private JTextField fats;
    private JTextField sodium;
    private JTextField price;
    private JTextField product;
    private JScrollPane scroll;
    private JButton nameBtn;
    private JButton ratintgBtn;
    private JButton caloriesBtn;
    private JButton proteinBtn;
    private JButton fatBtn;
    private JButton sodiumBtn;
    private JButton priceBtn;
    private JButton addBtn;
    private JButton orderBtn;
    private JTextArea textArea;

    private DeliveryService service;
    private ArrayList<MenuItem> list;
    private static int orderId = 0;
    private static int clientid = 0;

    public ClientGUI(DeliveryService service, EmployeeGUI employeeGUI) {
        list = new ArrayList<>();
        this.service = service;

        frame = new JFrame();

        frame.getContentPane().setBackground(Color.CYAN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(500, 150, 800, 700);
        frame.getContentPane().setLayout(null);

        titleLabel = new JLabel("Hello, dear client! ");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        titleLabel.setBounds(270, 20, 150, 20);
        frame.getContentPane().add(titleLabel);

        viewMenuBtn = new JButton("View menu");
        viewMenuBtn.setForeground(Color.WHITE);
        viewMenuBtn.setBackground(Color.BLACK);
        viewMenuBtn.setBounds(100, 450, 150, 20);
        frame.getContentPane().add(viewMenuBtn);

        searchLabel = new JLabel("Search products by:");
        searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        searchLabel.setBounds(20, 55, 400, 20);
        frame.getContentPane().add(searchLabel);

        nameBtn = new JButton("Name");
        nameBtn.setForeground(Color.WHITE);
        nameBtn.setBackground(Color.BLACK);
        nameBtn.setBounds(10, 100, 120, 20);
        frame.getContentPane().add(nameBtn);

        name = new JTextField();
        name.setBounds(150, 100, 100, 20);
        frame.getContentPane().add(name);

        ratintgBtn = new JButton("Rating");
        ratintgBtn.setForeground(Color.WHITE);
        ratintgBtn.setBackground(Color.BLACK);
        ratintgBtn.setBounds(10, 150, 120, 20);
        frame.getContentPane().add(ratintgBtn);

        rating = new JTextField();
        rating.setBounds(150, 150, 100, 20);
        frame.getContentPane().add(rating);

        proteinBtn = new JButton("Proteins");
        proteinBtn.setForeground(Color.WHITE);
        proteinBtn.setBackground(Color.BLACK);
        proteinBtn.setBounds(10, 200, 120, 20);
        frame.getContentPane().add(proteinBtn);

        proteins = new JTextField();
        proteins.setBounds(150, 200, 100, 20);
        frame.getContentPane().add(proteins);

        caloriesBtn = new JButton("Calories");
        caloriesBtn.setForeground(Color.WHITE);
        caloriesBtn.setBackground(Color.BLACK);
        caloriesBtn.setBounds(10, 250, 120, 20);
        frame.getContentPane().add(caloriesBtn);

        calories = new JTextField();
        calories.setBounds(150, 250, 100, 20);
        frame.getContentPane().add(calories);

        fatBtn = new JButton("Fats");
        fatBtn.setForeground(Color.WHITE);
        fatBtn.setBackground(Color.BLACK);
        fatBtn.setBounds(10, 300, 120, 20);
        frame.getContentPane().add(fatBtn);

        fats = new JTextField();
        fats.setBounds(150, 300, 100, 20);
        frame.getContentPane().add(fats);

        sodiumBtn = new JButton("Sodium");
        sodiumBtn.setForeground(Color.WHITE);
        sodiumBtn.setBackground(Color.BLACK);
        sodiumBtn.setBounds(10, 350, 120, 20);
        frame.getContentPane().add(sodiumBtn);

        sodium = new JTextField();
        sodium.setBounds(150, 350, 100, 20);
        frame.getContentPane().add(sodium);

        priceBtn = new JButton("Price");
        priceBtn.setForeground(Color.WHITE);
        priceBtn.setBackground(Color.BLACK);
        priceBtn.setBounds(10, 400, 120, 20);
        frame.getContentPane().add(priceBtn);

        price = new JTextField();
        price.setBounds(150, 400, 100, 20);
        frame.getContentPane().add(price);

        createOrder = new JLabel("To place an order, enter the name of the desired product.");
        createOrder.setFont(new Font("Tahoma", Font.PLAIN, 12));
        createOrder.setBounds(10, 550, 350, 30);
        frame.getContentPane().add(createOrder);

        productName = new JLabel("Product name:");
        productName.setFont(new Font("Tahoma", Font.BOLD, 12));
        productName.setBounds(10, 600, 100, 20);
        frame.getContentPane().add(productName);

        product = new JTextField();
        product.setBounds(110, 600, 200, 20);
        frame.getContentPane().add(product);

        addBtn = new JButton("Add");
        addBtn.setForeground(Color.WHITE);
        addBtn.setBackground(Color.BLACK);
        addBtn.setBounds(70, 630, 100, 20);
        frame.getContentPane().add(addBtn);

        orderBtn = new JButton("Place order");
        orderBtn.setForeground(Color.WHITE);
        orderBtn.setBackground(Color.BLACK);
        orderBtn.setBounds(200, 630, 120, 20);
        frame.getContentPane().add(orderBtn);

        scroll = new JScrollPane();
        scroll.setBounds(330, 100, 420, 430);
        frame.getContentPane().add(scroll);

        textArea = new JTextArea();
        scroll.setViewportView(textArea);

        nameBtn.addActionListener(e -> textArea.setText(service.searchByName(name.getText())));
        ratintgBtn.addActionListener(e -> textArea.setText(service.searchByRating(Double.parseDouble(rating.getText()))));
        caloriesBtn.addActionListener(e -> textArea.setText(service.searchByCalories(Integer.parseInt(calories.getText()))));
        proteinBtn.addActionListener(e -> textArea.setText(service.searchByProteins(Integer.parseInt(proteins.getText()))));
        sodiumBtn.addActionListener(e -> textArea.setText(service.searchBySodium(Integer.parseInt(sodium.getText()))));
        fatBtn.addActionListener(e -> textArea.setText(service.searchByFats(Integer.parseInt(fats.getText()))));
        priceBtn.addActionListener(e -> textArea.setText(service.searchByPrice(Integer.parseInt(price.getText()))));

        viewMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(service.viewMenu());
            }
        });

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (service.searchProduct(product.getText()) != null) {
                    list.add(service.searchProduct((product.getText())));
                }
            }
        });

        orderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.createOrder(++orderId, StartGUI.getClId(), new Date(), list);
                Serializator.serialize(service);
                employeeGUI.getFrame().setVisible(true);
                list.clear();
            }
        });

    }

    public JFrame getFrame() {
        return frame;
    }
}
