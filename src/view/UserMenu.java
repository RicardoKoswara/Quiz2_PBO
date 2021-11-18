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

public class UserMenu implements ActionListener , ItemListener{
    JFrame frame;
    JPanel panel;
    JLabel lLogin, lEmail, lName, lPassword, lCategory;
    JTextField tfEmail, tfName;
    JPasswordField pfPassword;
    JCheckBox showPassword;
    
    JButton btnSave, btnDelete, btnBack;
    JComboBox<String> cbCategory;
    Controller controller = new Controller();
    ArrayList<CategoryUser> categories = controller.selectCategoryUser();
    User user;


    public UserMenu(User user) {

        // Set JFrame
        frame = new JFrame("User Menu");
        frame.setSize(900, 550);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\images\\icon-for-social-media-19.jpg");
        frame.setIconImage(icon);

        // Set JPanel
        panel = new JPanel();
        panel.setBackground(new Color(135, 206, 235));
        panel.setSize(900, 550);
        panel.setLayout(null);

        // Set components
        // Set JLabel
        lLogin = new JLabel("Profile Menu");
        lLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
        lLogin.setBounds(85, 20, 450, 40);

        lEmail = new JLabel("Email :");
        lEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
        lEmail.setBounds(85, 120, 200, 20);

        lName = new JLabel("Name :");
        lName.setFont(new Font("Tahoma", Font.BOLD, 20));
        lName.setBounds(85, 220, 200, 20);

        lPassword = new JLabel("Password :");
        lPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
        lPassword.setBounds(85, 320, 200, 20);

        lCategory = new JLabel("Category :");
        lCategory.setFont(new Font("Tahoma", Font.BOLD, 20));
        lCategory.setBounds(480, 120, 200, 30);

        // Set JTextField
        tfEmail = new JTextField();
        tfEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfEmail.setBounds(200, 110, 250, 40);
        tfEmail.setText(user.getEmail());

        tfName = new JTextField();
        tfName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfName.setBounds(200, 210, 250, 40);
        tfName.setText(user.getName());

        // Set JPasswordField
        pfPassword = new JPasswordField();
        pfPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pfPassword.setBounds(200, 310, 250, 40);
        pfPassword.setText(user.getPassword());
        
              // button for showing password
              showPassword = new JCheckBox("Show Password ");
              showPassword.setBounds(pfPassword.getX(), pfPassword.getY() + 50, 150, 20);
              showPassword.setBackground(new Color(135, 206, 235));
              char passwordDefault= pfPassword.getEchoChar();
              showPassword.addItemListener(new ItemListener() {
                  public void itemStateChanged(ItemEvent e) {
                      if (e.getStateChange() == ItemEvent.SELECTED) {
                          pfPassword.setEchoChar((char) 0);
                      } else {
                          pfPassword.setEchoChar(passwordDefault);
                      }
                  }
              });
  

        // Set JButton
        btnSave = new JButton("Save");

        btnSave.setBounds(lCategory.getX(), tfName.getY(), 150, 40);
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 20));

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(btnSave.getX()+btnSave.getWidth()+10, tfName.getY(), 150, 40);
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 20));

        btnBack = new JButton("Back");
        btnBack.setBounds(lLogin.getX(), pfPassword.getY()+pfPassword.getHeight()+100, 150, 40);
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));

        btnSave.addActionListener(this);
        btnDelete.addActionListener(this);
        btnBack.addActionListener(this);

        // Set JComboBox
        String[] categoryList = new String[categories.size()];
        String categorySelected = "";
        for (int i = 0; i < categories.size(); i++) {
            categoryList[i] = categories.get(i).getCategoryName();
            if (categories.get(i).getIdCategory() == user.getIdCategory()) {
                categorySelected = categories.get(i).getCategoryName();
            }
        }
        cbCategory = new JComboBox<>(categoryList);
        cbCategory.setBounds(600, 110, 200, 40);
        cbCategory.setSelectedItem(categorySelected);
        ;

        // Adding components
        panel.add(lLogin);
        panel.add(lEmail);
        panel.add(lName);
        panel.add(lPassword);
        panel.add(showPassword);
        panel.add(lCategory);
        panel.add(tfEmail);
        panel.add(tfName);
        panel.add(pfPassword);
        panel.add(cbCategory);
        panel.add(btnSave);
        panel.add(btnDelete);
        panel.add(btnBack);
        frame.setContentPane(panel);

        // Set vicibility
        panel.setVisible(true);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
        case "Save":

            // Get value
            String email = tfEmail.getText();
            String name = tfName.getText();
            String password = String.valueOf(pfPassword.getPassword());
            String category = cbCategory.getSelectedItem().toString();

            // Checking value
            if (email.equals("") || name.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill all field !");
            } else if (password.length() < 8) {
                JOptionPane.showMessageDialog(null, "password requires at least 8 character");
            } else {
                int idCategory = 0;
                for (int i = 0; i < categories.size(); i++) {
                    if (categories.get(i).getCategoryName().equals(category)) {
                        idCategory = categories.get(i).getIdCategory();
                    }
                }

                // Update user data
                User updatedUser = new User(name, email, password, idCategory);
                boolean success = controller.updateUser(user.getId(), updatedUser);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Update success");
                } else {
                    JOptionPane.showMessageDialog(null, "Update failed");
                }
            }
            frame.dispose();
            new MainMenu();
            break;

        case "Delete":

            int result = JOptionPane.showConfirmDialog(null, "Are you sure want to delete this account ?",
                    "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                boolean success = controller.deleteUser(user.getId());
                if (success) {
                    JOptionPane.showMessageDialog(null, "Delete success");
                } else {
                    JOptionPane.showMessageDialog(null, "Delete failed");
                }
                new MainMenu();
                frame.dispose();

            }
            break;

        case "Back":
            new MainMenu();
            frame.dispose();
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
