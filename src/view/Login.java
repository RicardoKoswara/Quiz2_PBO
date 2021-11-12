package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import controller.Controller;

public class Login implements ActionListener {
    JFrame fLogin;
    JPanel panelLogin;
    JButton btnLogin, btnBack;
    JLabel lUsername, lPassword, lEmail, lLogin;
    JTextField jtUsername, jtEmail;
    JPasswordField jPassword;

    public Login() {
        // object for querry
        Controller control = new Controller();

        fLogin = new JFrame("LogIn");
        fLogin.setSize(550, 700);
        fLogin.setLayout(null);
        fLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        lUsername.setBounds(235, 50, 100, 100);

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
        lPassword.setBounds(235, 230, 100, 100);

        jPassword = new JPasswordField();
        jPassword.setBounds(160, 300, 220, 30);

        // button
        btnLogin = new JButton("LogIn");
        btnLogin.setBounds(200, 370, 120, 50);
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

        // add to Frame
        fLogin.add(panelLogin);
        panelLogin.setVisible(true);
        fLogin.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
        case "Login":
            new UserMenu();
            fLogin.dispose();
            break;

        case "Back":
            new MainMenu();
            fLogin.dispose();
            break;

        default:
            break;
        }
    }
}
