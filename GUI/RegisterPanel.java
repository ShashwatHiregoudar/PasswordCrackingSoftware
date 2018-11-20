import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterPanel extends JPanel {
    private JTextArea name_textArea;
    private JLabel name_label;
    private JLabel pwd_label;
    private JPasswordField pwd_field;
    private JButton save_button;

    public void logPasswords() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashInBytes = md.digest(pwd_field.getText().getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            String hashedPassword = sb.toString();
            System.out.println("After Hash : " + hashedPassword);
            FileWriter out = new FileWriter("Passwords.csv", true);
            out.append(name_textArea.getText()+","+hashedPassword+"\n");
            out.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public RegisterPanel(int width, int height) {
        // construct components
        name_textArea = new JTextArea(5, 5);
        name_label = new JLabel("Name :");
        pwd_label = new JLabel("Password :");
        pwd_field = new JPasswordField(5);
        save_button = new JButton("Save");

        // adjust size and set layout
        setPreferredSize(new Dimension(width, height));
        setLayout(null);

        // add components
        add(name_textArea);
        add(name_label);
        add(pwd_label);
        add(pwd_field);
        add(save_button);
        // save_button.addMouseListener(mListener);
        save_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked : " + pwd_field.getText());
                logPasswords();
            }
        });
        // set component bounds (only needed by Absolute Positioning)
        name_textArea.setBounds(200, 35, 180, 30);
        name_label.setBounds(125, 40, 100, 25);
        pwd_label.setBounds(95, 80, 100, 25);
        pwd_field.setBounds(200, 80, 175, 30);
        save_button.setBounds(width / 2 - 50, 145, 100, 25);
    }
/*
    public static void main(String[] args) {
        JFrame frame = new JFrame("MyPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MyPanel());
        frame.pack();
        frame.setVisible(true);
    }*/
}
