package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.CategoryUser;
import model.User;

public class Search implements ActionListener {
    JFrame frame;
    JPanel panel;
    JLabel lSearch;
    JButton btnSearch, btnBack;
    JComboBox<String> cbCategory;

    Controller controller = new Controller();
    ArrayList<CategoryUser> categories = controller.selectCategoryUser();
    JTable dataTable;
    DefaultTableModel model;
    JScrollPane scrollpane;

    public Search() {
        // Set JFrame
        frame = new JFrame("Search Menu");
        frame.setSize(900, 650);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\images\\icon-for-social-media-19.jpg");
        frame.setIconImage(icon);
 
        // Set JPanel
        panel = new JPanel();
        panel.setBackground(new Color(135, 206, 235));
        panel.setSize(900, 650);
        panel.setLayout(null);

        // Set components
        // Set JLabel
        lSearch = new JLabel("Search Menu");
        lSearch.setFont(new Font("Tahoma", Font.BOLD, 30));
        lSearch.setBounds(85, 20, 450, 40);

        // Set JComboBox
        String[] categoryList = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            categoryList[i] = categories.get(i).getCategoryName();
        }
        cbCategory = new JComboBox<>(categoryList);
        cbCategory.setBounds(lSearch.getX(), lSearch.getY()+lSearch.getHeight()+10, 200, 40);

        // Set JButton
        btnSearch = new JButton("Search");
        btnSearch.setBounds(300, lSearch.getY()+lSearch.getHeight()+10, 120, 40);
        btnSearch.setFont(new Font("Arial", Font.BOLD, 20));

        btnBack = new JButton("Back");
        btnBack.setBounds(lSearch.getX()-50, 550, 100, 30);
        btnBack.setFont(new Font("Arial", Font.BOLD, 15));

        btnSearch.addActionListener(this);
        btnBack.addActionListener(this);

        // Set JTable

        dataTable = new JTable();

        dataTable.setBounds(cbCategory.getX(), btnSearch.getY()+btnSearch.getHeight()+20, 715, 350);

        // Set JScrollPane
        scrollpane = new JScrollPane(dataTable);
        scrollpane.setBounds(85, 120, 715, 350);

        // Adding components
        panel.add(lSearch);
        panel.add(cbCategory);
        panel.add(btnSearch);
        panel.add(btnBack);
        panel.add(scrollpane);
        frame.setContentPane(panel);

        // Set vicibility
        scrollpane.setVisible(false);
        panel.setVisible(true);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
        case "Search":
            // Get value
            String category = cbCategory.getSelectedItem().toString();
            ArrayList<User> users = controller.selectUserByCategory(category);

            // Set value
            String[] columnData = { "id_user", "name", "email", "password", "id_category" };
            model = new DefaultTableModel(columnData, 0);

            for (int i = 0; i < users.size(); i++) {
                String[] newModel = new String[5];
                newModel[0] = String.valueOf(users.get(i).getId());
                newModel[1] = users.get(i).getName();
                newModel[2] = users.get(i).getEmail();
                newModel[3] = users.get(i).getPassword();
                newModel[4] = String.valueOf(users.get(i).getIdCategory());
                model.addRow(newModel);
            }
            dataTable.setModel(model);
            scrollpane.setVisible(true);
            break;
        case "Back":
            new MainMenu();
            frame.dispose();
            break;
        default:
            break;
        }
    }

}
