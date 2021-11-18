package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import controller.Controller;
import javax.swing.JOptionPane;
import model.User;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;

public class Login implements ActionListener , ItemListener{
    JFrame fLogin;
    JPanel panelLogin;
    JButton btnLogin, btnBack;
    JLabel lUsername, lPassword, lEmail, lLogin;
    JTextField jtUsername, jtEmail;
    JPasswordField jPassword;
    JCheckBox showPassword;
    Controller control = new Controller();

    public Login() {
        // object for querry
       
        fLogin = new JFrame("Login");
        fLogin.setSize(550, 700);
        fLogin.setLayout(null);
        fLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image icon = Toolkit.getDefaultToolkit().getImage("src\\images\\icon-for-social-media-19.jpg");
        fLogin.setIconImage(icon);

        
        panelLogin = new JPanel();
        panelLogin.setBackground(new Color(135, 206, 235));
        panelLogin.setSize(550, 700);
        panelLogin.setLayout(null);

        lLogin = new JLabel("Welcome!");
        lLogin.setFont(new Font("Sans-Serif", Font.BOLD, 34));
        lLogin.setBounds(200, 15, 300, 30);

        // username input
        lUsername = new JLabel("USERNAME ");
        lUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
        lUsername.setBounds(230, 50, 100, 100);

        jtUsername = new JTextField();
        jtUsername.setBounds(160, 120, 220, 30);

        // email input
        lEmail = new JLabel("EMAIL");
        lEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
        lEmail.setBounds(250, 140, 100, 100);

        jtEmail = new JTextField();
        jtEmail.setBounds(160, 210, 220, 30);
        // password input

        lPassword = new JLabel("PASSWORD");
        lPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
        lPassword.setBounds(230, 230, 100, 100);

        jPassword = new JPasswordField();
        jPassword.setBounds(160, 300, 220, 30);

           // button for showing password
           showPassword = new JCheckBox("Show Password ");
           showPassword.setBounds(jPassword.getX(), jPassword.getY() + 30, 150, 20);
           showPassword.setBackground(new Color(135, 206, 235));
           char passwordDefault= jPassword.getEchoChar();
           showPassword.addItemListener(new ItemListener() {
               public void itemStateChanged(ItemEvent e) {
                   if (e.getStateChange() == ItemEvent.SELECTED) {
                       jPassword.setEchoChar((char) 0);
                   } else {
                       jPassword.setEchoChar(passwordDefault);
                   }
               }
           });
           
        // button
        btnLogin = new JButton("Login");
        btnLogin.setBounds(200, showPassword.getY()+showPassword.getHeight()+50, 120, 50);
        btnLogin.addActionListener(this);

        btnBack = new JButton("Back");
        btnBack.setBounds(10, 580, 90, 30);
        btnBack.addActionListener(this);

        
        // add to panel
        panelLogin.add(lLogin);
        panelLogin.add(lUsername);
        panelLogin.add(lPassword);
        panelLogin.add(lEmail);
        panelLogin.add(jtEmail);
        panelLogin.add(jtUsername);
        panelLogin.add(jPassword);
        panelLogin.add(btnLogin);
        panelLogin.add(btnBack);
        panelLogin.add(showPassword);

        // add to Frame
        fLogin.add(panelLogin);
        panelLogin.setVisible(true);
        fLogin.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
        case "Login":
            // Get value
            String email = jtEmail.getText();
            String password = String.valueOf(jPassword.getPassword());

            // Checking value
            if (email.equals("") && password.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill all field !");
            } else {
                // Get data from database
                User user = control.getUser(email, password);
                if (user == null) {
                    JOptionPane.showMessageDialog(null, "User not found");
                } else {
                    new UserMenu(user);
                    fLogin.dispose();
                }
            }
            break;

        case "Back":
            new MainMenu();
            fLogin.dispose();
            break;

        default:
            break;
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        
    }
}
