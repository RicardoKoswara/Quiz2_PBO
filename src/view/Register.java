package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import controller.Controller;
import model.User;

public class Register implements ActionListener {
    JFrame fRegister;
    JPanel panelRegister;
    JLabel lRegister;
    JLabel lInfo;
    JLabel lUsername;
    JLabel lEmail;
    JLabel lPassword;
    JLabel lRetype;
    JButton btnRegister , btnBack;
    JTextField jtUsername;
    JTextField jtEmail;
    JPasswordField jPassword;
    JPasswordField jRetype;

    Controller controller = new Controller();
    public Register() {

      

        // frame
        fRegister = new JFrame("Registration");
        fRegister.setSize(550, 650);
        fRegister.setLayout(null);

        fRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // panel
        panelRegister = new JPanel();
        panelRegister.setBackground(new Color(135, 206, 235));
        panelRegister.setSize(550, 650);
        panelRegister.setLayout(null);

        // lable
        lRegister = new JLabel("Hello!");
        lRegister.setFont(new Font("Sans-Serif", Font.BOLD, 45));
        lRegister.setBounds(200, 5, 300, 80);

        lInfo = new JLabel("Please insert your data !");
        lInfo.setFont(new Font("Helvetica", Font.ITALIC, 15));
        lInfo.setBounds(180, 90, 300, 30);

        lUsername = new JLabel("Username :");
        lUsername.setBounds(25, 200, 200, 30);
        lUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));

        lEmail = new JLabel("Email :");
        lEmail.setBounds(25, 250, 200, 30);
        lEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));

        lPassword = new JLabel("Password :");
        lPassword.setBounds(25, 300, 200, 30);
        lPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));

        lRetype = new JLabel("Re-Type Password :");
        lRetype.setBounds(25, 350, 220, 30);
        lRetype.setFont(new Font("Tahoma", Font.PLAIN, 20));

        btnRegister = new JButton("Register");
        btnRegister.setBounds(200, 500, 120, 50);

        btnBack  = new JButton("Back");
        btnBack.setBounds(10,570,90,30);
        // textfield

        jtUsername = new JTextField();
        jtUsername.setBounds(250, 200, 200, 25);

        jtEmail = new JTextField();
        jtEmail.setBounds(250, 250, 200, 25);

        // PasswordFIeld;
        jPassword = new JPasswordField();
        jPassword.setBounds(250, 300, 200, 25);

        jRetype = new JPasswordField();
        jRetype.setBounds(250, 350, 200, 25);

        // action
        btnRegister.addActionListener(this);
        btnBack.addActionListener(this);
        // add to panel
        panelRegister.add(lRegister);
        panelRegister.add(btnRegister);
        panelRegister.add(btnBack);
        panelRegister.add(lInfo);

        panelRegister.add(lUsername);
        panelRegister.add(lEmail);
        panelRegister.add(lPassword);
        panelRegister.add(lRetype);

        panelRegister.add(jtEmail);
        panelRegister.add(jtUsername);
        panelRegister.add(jPassword);
        panelRegister.add(jRetype);

        panelRegister.setVisible(true);
        // add to Frame
        fRegister.add(panelRegister);
        fRegister.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
        case "Register":
            String username = jtUsername.getText();
            String email=  jtEmail.getText();
            String password = jPassword.getText();
            User user = new User(1 ,username , email , password ,1);
            boolean result = controller.insertNewUser(user);
            if(result){
            new UserMenu();
            fRegister.dispose();
            }
            break;

        case "Back":
            new MainMenu();
            fRegister.dispose();
            break;

        default:
            break;
        }
    }
}
