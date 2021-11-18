package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.User;
import java.awt.Toolkit;
import java.awt.Image;
public class MainMenu implements ActionListener {

    JFrame frame;
    JPanel panel;
    JButton login, register, search;

    public MainMenu() {

        frame = new JFrame("Main Menu");
        frame.setSize(450, 550);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image icon = Toolkit.getDefaultToolkit().getImage("src\\images\\icon-for-social-media-19.jpg");
        frame.setIconImage(icon);

        // Panel
        panel = new JPanel();
        panel.setSize(450, 550);
        panel.setBackground(new Color(135, 206, 235));
        panel.setLayout(null);

        // Button
        login = new JButton("Login");
        login.setBounds(110, 80, 220, 60);
        login.setFont(new Font("Arial", Font.BOLD, 20));

        register = new JButton("Register");
        register.setBounds(110, 200, 220, 60);
        register.setFont(new Font("Arial", Font.BOLD, 20));

        search = new JButton("Search Data");
        search.setBounds(110, 320, 220, 60);
        search.setFont(new Font("Arial", Font.BOLD, 20));

        login.addActionListener(this);
        register.addActionListener(this);
        search.addActionListener(this);

        // Add
        panel.add(login);
        panel.add(register);
        panel.add(search);
        frame.add(panel);

        panel.setVisible(true);
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
        case "Login":
            new Login();
            frame.dispose();
            break;

        case "Register":
            new Register();
            frame.dispose();
            break;

        case "Search Data":
            new Search();
            frame.dispose();
            break;

        default:
            break;
        }
    }

}
