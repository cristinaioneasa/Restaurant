package Presentation;

import BussinessLogic.DeliveryService;
import BussinessLogic.Client;
import DataAccess.Serializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Ioneasa Cristina
 */

public class StartGUI extends JFrame {

    private JFrame frame;

    private JLabel title;
    private JLabel identity;
    private JButton clientBtn;
    private JButton administratorBtn;
    private JLabel nameLabel;
    private JLabel passLabel;
    private JTextField userName;
    private JTextField userPass;
    private JButton logInBtn;
    private JButton registerBtn;
    private JLabel mess1Label;
    private JLabel mess2Label;

    private static int clientId;
    private Client client;
    private int chooseRole = 0; //1- admin, 2- employee, 3- client

    public StartGUI(DeliveryService service, AdministratorGUI administratorGUI, EmployeeGUI employeeGUI, ClientGUI clientGUI){

        frame = new JFrame();

        frame.setBounds(500,150,700,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);

        title = new JLabel("WELCOME");
        title.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
        title.setBounds(250,50,200,50);
        frame.getContentPane().add(title);

        identity = new JLabel("Choose your role:");
        identity.setFont(new Font("Tahoma", Font.BOLD, 12));
        identity.setBounds(50,300,200,20);
        frame.getContentPane().add(identity);

        clientBtn  = new JButton("Client");
        clientBtn.setForeground(Color.WHITE);
        clientBtn.setBackground(Color.BLACK);
        clientBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
        clientBtn.setBounds(50,340,100,20);
        frame.getContentPane().add(clientBtn);

        administratorBtn  = new JButton("Admin");
        administratorBtn.setForeground(Color.WHITE);
        administratorBtn.setBackground(Color.BLACK);
        administratorBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
        administratorBtn.setBounds(50,380,100,20);
        frame.getContentPane().add(administratorBtn);

        nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        nameLabel.setBounds(50,150,80,20);
        frame.getContentPane().add(nameLabel);

        userName = new JTextField();
        userName.setBounds(100,150,200,20);
        frame.getContentPane().add(userName);

        passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        passLabel.setBounds(50,200,80,20);
        frame.getContentPane().add(passLabel);

        userPass = new JTextField();
        userPass.setBounds(120,200,200,20);
        frame.getContentPane().add(userPass);

        logInBtn = new JButton("Login");
        logInBtn.setForeground(Color.WHITE);
        logInBtn.setBackground(Color.BLACK);
        logInBtn.setBounds(420,150,100,20);
        frame.getContentPane().add(logInBtn);

        mess1Label = new JLabel("If you don't have an account,");
        mess1Label.setBounds(400, 175, 170, 15);
        frame.getContentPane().add(mess1Label);

        mess2Label = new JLabel("click the button to register.");
        mess2Label.setBounds(407, 190, 165, 13);
        frame.getContentPane().add(mess2Label);

        registerBtn = new JButton("Register");
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setBackground(Color.BLACK);
        registerBtn.setBounds(420,210,100,20);
        frame.getContentPane().add(registerBtn);

        administratorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseRole = 1;
            }
        });

        clientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseRole = 2;
            }
        });

        logInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chooseRole == 1){
                    if(service.getAdminName() != null){
                        administratorGUI.getFrame().setVisible(true);
                    }
                    else JOptionPane.showMessageDialog(null,"You don't have an account!\n Please, register.");
                }

                if(chooseRole == 2){
                    if(service.searchClient(userName.getText())!=null){
                        clientGUI.getFrame().setVisible(true);
                    }
                    else JOptionPane.showMessageDialog(null,"You don't have an account!\n Please, register.");
                }
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(chooseRole == 1){
                    if(service.getAdminName() == null){
                        service.newAdmin(userName.getText(), passLabel.getText());
                        administratorGUI.getFrame().setVisible(true);
                    }
                    else JOptionPane.showMessageDialog(null,"This username is already used!");
                }

                if(chooseRole == 2){
                    if(service.searchClient(userName.getText() )== null){
                        service.newClient(++clientId, userName.getText(), passLabel.getText());
                        client = new Client(clientId, userName.getText(), passLabel.getText());
                        service.getClients().add(client);

                        clientGUI.getFrame().setVisible(true);

                    }
                    else JOptionPane.showMessageDialog(null,"This username is already used!");
                }
            }
        });

        Serializator.serialize(service);

    }
    public static int getClId(){
        return clientId;
    }

    public JFrame getFrame() {
        return frame;
    }
}
