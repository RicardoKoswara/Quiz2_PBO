package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Controller;
import model.CategoryUser;
import model.User;

import javax.swing.JCheckBox;
import java.awt.event.ItemListener;


public class Register implements ActionListener , ItemListener {
    JFrame fRegister;
    JPanel panelRegister;
    JLabel lRegister;
    JLabel lInfo;
    JLabel lUsername;
    JLabel lEmail;
    JLabel lPassword, lCategory;

    JButton btnRegister, btnBack;
    JTextField jtUsername;
    JTextField jtEmail;
    JPasswordField jPassword;

    JComboBox<String> cbCategory;
    JCheckBox showPassword;

    Controller controller = new Controller();
    ArrayList<CategoryUser> categories = controller.selectCategoryUser();

    public Register() {

        // frame
        fRegister = new JFrame("Registration");
        fRegister.setSize(550, 650);
        fRegister.setLayout(null);
        fRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image icon = Toolkit.getDefaultToolkit().getImage("src\\images\\icon-for-social-media-19.jpg");
        fRegister.setIconImage(icon);

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

        lCategory = new JLabel("Category :");
        lCategory.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lCategory.setBounds(lPassword.getX(), lPassword.getY() + 80, 200, 30);

        // textfield
        jtUsername = new JTextField();
        jtUsername.setBounds(250, 200, 200, 25);

        jtEmail = new JTextField();
        jtEmail.setBounds(250, 250, 200, 25);

        // PasswordFIeld;
        jPassword = new JPasswordField();
        jPassword.setBounds(250, 300, 200, 25);

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

        // combo Box
        String[] categoryList = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            categoryList[i] = categories.get(i).getCategoryName();
        }
        cbCategory = new JComboBox<>(categoryList);
        cbCategory.setBounds(250, lCategory.getY(), 200, 40);

        // button
        btnRegister = new JButton("Register");
        btnRegister.setBounds(200, cbCategory.getY()+cbCategory.getHeight()+50, 120, 50);

        btnBack = new JButton("Back");
        btnBack.setBounds(10, 570, 90, 30);

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

        panelRegister.add(jtEmail);
        panelRegister.add(jtUsername);
        panelRegister.add(jPassword);
        panelRegister.add(showPassword);
        
        panelRegister.add(cbCategory);
        panelRegister.add(lCategory);

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
            String email = jtEmail.getText();
            String password = String.valueOf(jPassword.getPassword());
            String category = cbCategory.getSelectedItem().toString();
           
            // Check kalo ada kosong atau password kurang dari 8char
            if (email.equals("") || username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill all field !");
            } else if (password.length() < 8) {
                JOptionPane.showMessageDialog(null, "password requires at least 8character");
            } else {
                int idCategory = 0;
                for (int i = 0; i < categories.size(); i++) {
                    if (categories.get(i).getCategoryName().equals(category)) {
                        idCategory = categories.get(i).getIdCategory();
                    }
                }
                User user = new User(username, email, password, idCategory);
                boolean result = controller.insertNewUser(user);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Register success" , "SUCCESS",JOptionPane.INFORMATION_MESSAGE);
                    fRegister.dispose();
                    new MainMenu();
                } else {
                    JOptionPane.showMessageDialog(null, "Register Failed", "WARNING!", JOptionPane.WARNING_MESSAGE);
                }
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

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        
    }
}
