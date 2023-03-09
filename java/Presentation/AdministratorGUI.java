package Presentation;

import BussinessLogic.BaseProduct;
import BussinessLogic.DeliveryService;
import BussinessLogic.MenuItem;
import DataAccess.Serializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Ioneasa Cristina
 */

public class AdministratorGUI extends JFrame {
    private JFrame frame;

    private JButton importProductsBtn;
    private JButton addBtn;
    private JButton delete;
    private JButton updateBtn;
    private JButton addProdToMenuBtn;
    private JButton createBtn;
    private JButton timeIntervalBtn;
    private JButton favoritesBtn;
    private JButton loyalClientsBtn;
    private JButton specificDayBtn;
    private JLabel title;
    private JLabel manageProductsLabel;
    private JLabel nameLabel;
    private JLabel ratingLabel;
    private JLabel caloriesLabel;
    private JLabel proteinLabel;
    private JLabel fatsLabel;
    private JLabel sodiumLabel;
    private JLabel priceLabel;
    private JLabel generateReportsLabel;
    private JLabel startHourLabel;
    private JLabel endHourLabel;
    private JLabel timesLabel;
    private JLabel valueLabel;
    private JLabel DayLabel;
    private JLabel menuTitleLabel;
    private JTextField name;
    private JTextField rating;
    private JTextField calories;
    private JTextField protein;
    private JTextField fat;
    private JTextField sodium;
    private JTextField price;
    private JTextField startHour;
    private JTextField endHour;
    private JTextField nbTimes;
    private JTextField value;
    private JTextField day;
    private JTextField menu;
    private JTextArea report;
    private JScrollPane scroll;
    private JLabel importLabel;

    private DeliveryService service;
    private ArrayList<MenuItem> listMenu;

    public AdministratorGUI(DeliveryService service) {

        listMenu = new ArrayList<>();
        this.service=service;

        frame = new JFrame();

        frame.getContentPane().setBackground(Color.CYAN);
        frame.setBounds(500, 50, 800, 600);
        frame.getContentPane().setLayout(null);

        title = new JLabel("Hello, Administrator!");
        title.setFont(new Font("Tahoma", Font.BOLD, 15));
        title.setBounds(50, 20, 200, 20);
        frame.getContentPane().add(title);

        manageProductsLabel = new JLabel("To modify a product,  insert the new values and click the button.");
        manageProductsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        manageProductsLabel.setBounds(10, 85, 370, 20);
        frame.getContentPane().add(manageProductsLabel);

        nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        nameLabel.setBounds(10, 110, 70, 20);
        frame.getContentPane().add(nameLabel);

        name = new JTextField();
        name.setBounds(80, 110, 100, 20);
        frame.getContentPane().add(name);

        ratingLabel = new JLabel("Rating:");
        ratingLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        ratingLabel.setBounds(10, 135, 80, 20);
        frame.getContentPane().add(ratingLabel);

        rating = new JTextField();
        rating.setBounds(80, 135, 100, 20);
        frame.getContentPane().add(rating);

        caloriesLabel = new JLabel("Calories:");
        caloriesLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        caloriesLabel.setBounds(10, 160, 80, 20);
        frame.getContentPane().add(caloriesLabel);

        calories = new JTextField();
        calories.setBounds(80, 160, 100, 20);
        frame.getContentPane().add(calories);

        proteinLabel = new JLabel("Proteins:");
        proteinLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        proteinLabel.setBounds(10, 185, 80, 20);
        frame.getContentPane().add(proteinLabel);

        protein = new JTextField();
        protein.setBounds(80, 185, 100, 20);
        frame.getContentPane().add(protein);

        fatsLabel = new JLabel("Fats:");
        fatsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        fatsLabel.setBounds(10, 210, 80, 20);
        frame.getContentPane().add(fatsLabel);

        fat = new JTextField();
        fat.setBounds(80, 210, 100, 20);
        frame.getContentPane().add(fat);

        sodiumLabel = new JLabel("Sodium:");
        sodiumLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        sodiumLabel.setBounds(10, 235, 80, 20);
        frame.getContentPane().add(sodiumLabel);

        sodium = new JTextField();
        sodium.setBounds(80, 235, 100, 20);
        frame.getContentPane().add(sodium);

        priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        priceLabel.setBounds(10, 260, 80, 20);
        frame.getContentPane().add(priceLabel);

        price = new JTextField();
        price.setBounds(80, 260, 100, 20);
        frame.getContentPane().add(price);

        menuTitleLabel = new JLabel("Menu title:");
        menuTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        menuTitleLabel.setBounds(250, 110, 80, 20);
        frame.getContentPane().add(menuTitleLabel);

        menu = new JTextField();
        menu.setBounds(320, 110, 120, 20);
        frame.getContentPane().add(menu);

        addBtn = new JButton("Add");
        addBtn.setForeground(Color.WHITE);
        addBtn.setBackground(Color.BLACK);
        addBtn.setBounds(220, 185, 100, 20);
        frame.getContentPane().add(addBtn);

        delete = new JButton("Delete");
        delete.setForeground(Color.WHITE);
        delete.setBackground(Color.BLACK);
        delete.setBounds(220, 250, 100, 20);
        frame.getContentPane().add(delete);

        updateBtn = new JButton("Update");
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setBackground(Color.BLACK);
        updateBtn.setBounds(220, 220, 100, 20);
        frame.getContentPane().add(updateBtn);

        addProdToMenuBtn = new JButton("Add product");
        addProdToMenuBtn.setForeground(Color.WHITE);
        addProdToMenuBtn.setBackground(Color.BLACK);
        addProdToMenuBtn.setBounds(250,140,120,20);
        frame.getContentPane().add(addProdToMenuBtn);

        createBtn = new JButton("Create menu");
        createBtn.setForeground(Color.WHITE);
        createBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
        createBtn.setBackground(Color.BLACK);
        createBtn.setBounds(390,140,120,20);
        frame.getContentPane().add(createBtn);

        importProductsBtn = new JButton("Import");
        importProductsBtn.setForeground(Color.WHITE);
        importProductsBtn.setBackground(Color.BLACK);
        importProductsBtn.setFont(new Font("Tahoma", Font.BOLD, 10));
        importProductsBtn.setBounds(280,50,100,20);
        frame.getContentPane().add(importProductsBtn);

        generateReportsLabel = new JLabel("To generate reports based on:");
        generateReportsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        generateReportsLabel.setBounds(10,320,200,20);
        frame.getContentPane().add(generateReportsLabel);

        startHourLabel = new JLabel("Start hour:");
        startHourLabel.setBounds(20,355,80,20);
        frame.getContentPane().add(startHourLabel);

        startHour = new JTextField();
        startHour.setBounds(80,355,60,20);
        frame.getContentPane().add(startHour);

        endHourLabel = new JLabel("End hour:");
        endHourLabel.setBounds(20,385,80,20);
        frame.getContentPane().add(endHourLabel);

        endHour = new JTextField();
        endHour.setBounds(80,385,60,20);
        frame.getContentPane().add(endHour);

        timesLabel = new JLabel("Times:");
        timesLabel.setBounds(20,410,80,20);
        frame.getContentPane().add(timesLabel);

        nbTimes = new JTextField();
        nbTimes.setBounds(80,410,60,20);
        frame.getContentPane().add(nbTimes);

        valueLabel = new JLabel("Value:");
        valueLabel.setBounds(20,435,80,20);
        frame.getContentPane().add(valueLabel);

        value = new JTextField();
        value.setBounds(80,435,60,20);
        frame.getContentPane().add(value);

        DayLabel = new JLabel("Day:");
        DayLabel.setBounds(20,460,80,20);
        frame.getContentPane().add(DayLabel);

        day = new JTextField();
        day.setBounds(80,460,60,20);
        frame.getContentPane().add(day);

        timeIntervalBtn = new JButton("Time interval");
        timeIntervalBtn.setForeground(Color.WHITE);
        timeIntervalBtn.setBackground(Color.BLACK);
        timeIntervalBtn.setBounds(200,355,170,20);
        frame.getContentPane().add(timeIntervalBtn);

        favoritesBtn = new JButton("Favorites");
        favoritesBtn.setForeground(Color.WHITE);
        favoritesBtn.setBackground(Color.BLACK);
        favoritesBtn.setBounds(200,390,170,20);
        frame.getContentPane().add(favoritesBtn);

        loyalClientsBtn = new JButton("Loyal clients");
        loyalClientsBtn.setBackground(Color.BLACK);
        loyalClientsBtn.setForeground(Color.WHITE);
        loyalClientsBtn.setBounds(200,425,170,20);
        frame.getContentPane().add(loyalClientsBtn);

        specificDayBtn = new JButton("Specific day");
        specificDayBtn.setBackground(Color.BLACK);
        specificDayBtn.setForeground(Color.WHITE);
        specificDayBtn.setBounds(200,460,170,20);
        frame.getContentPane().add(specificDayBtn);
        scroll = new JScrollPane();
        scroll.setBounds(420,190,350,350);
        frame.getContentPane().add(scroll);

        report = new JTextArea();
        report.setBounds(420, 190, 350, 350);
        frame.getContentPane().add(report);
        report.setEditable(false);

        importLabel = new JLabel("To import the products from excel, click here ->");
        importLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        importLabel.setBounds(10, 50, 265, 20);
        frame.getContentPane().add(importLabel);

        importProductsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.importProducts();
                Serializator.serialize(service);
            }
        });

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BaseProduct newProd = new BaseProduct(name.getText(), Double.valueOf(rating.getText()), Integer.valueOf(calories.getText()), Integer.valueOf(protein.getText()), Integer.valueOf(fat.getText()), Integer.valueOf(sodium.getText()), Integer.parseInt(price.getText()));
                service.addMenuItem(newProd);
                JOptionPane.showMessageDialog(null,"The product was added.");
                Serializator.serialize(service);
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BaseProduct newProd = new BaseProduct(name.getText(), Double.valueOf(rating.getText()), Integer.valueOf(calories.getText()), Integer.valueOf(protein.getText()), Integer.valueOf(fat.getText()), Integer.valueOf(sodium.getText()), Integer.parseInt(protein.getText()));
                service.updateMenuItem(newProd);
                JOptionPane.showMessageDialog(null,"The product was update.");
                Serializator.serialize(service);
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.deleteMenuItem(name.getText());
                JOptionPane.showMessageDialog(null,"The product was delete.");
                Serializator.serialize(service);
            }
        });

        addProdToMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuItem p = service.searchProduct(name.getText());
                listMenu.add(p);
            }
        });

        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.createMenuItem(listMenu, menu.getText());
                Serializator.serialize(service);
            }
        });

        timeIntervalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                report.setText(service.generateReportType1(Integer.valueOf(startHour.getText()),Integer.valueOf(endHour.getText())));
            }
        });

        favoritesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                report.setText(service.generateReportType2(Integer.valueOf(nbTimes.getText())));
            }
        });

        loyalClientsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                report.setText(service.generateReportType3(Integer.valueOf(nbTimes.getText()),Integer.valueOf(value.getText())));
            }
        });

        specificDayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                report.setText(service.generateReportType4(Integer.valueOf(day.getText())));
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}