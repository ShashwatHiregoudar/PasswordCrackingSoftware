import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * HackPanel
 */
public class HackPanel extends JPanel {
    private JButton bf_Button;
    private JButton rh_Button;
    private JButton dic_Button;
    private JTextArea name_text_area;
    private JLabel name_label;

    public HackPanel(int width, int height) {
        bf_Button = new JButton("Brute Force");
        rh_Button = new JButton("Rainbow Hash");
        dic_Button = new JButton("Dictionary Attack");
        name_label = new JLabel("Nameeeeeee");
        //loadUsers();

        name_text_area = new JTextArea();
        add(bf_Button);
        add(rh_Button);
        add(dic_Button);
        add(name_text_area);
        add(name_label);
        // adjust size and set layout
        setPreferredSize(new Dimension(width, height));
        setLayout(null);
    
        name_text_area.setBounds((width / 2) , height / 9, 140, 20);
        name_label.setBounds((width / 2) - 70, 3 * (height / 9), 140, 40);

        bf_Button.setBounds((width / 2) - 70, 3 * (height / 9), 140, 40);
        bf_Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bruteForceAttack();
                e.toString();
            }
        });
        rh_Button.setBounds((width / 2) - 70, 5 * (height / 9), 140, 40);
        dic_Button.setBounds((width / 2) - 80, 7 * (height / 9), 160, 40);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sample try");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new HackPanel(400, 300));
        frame.pack();
        frame.setVisible(true);
    }

    public String hash(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashInBytes = md.digest(s.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        String hashedPassword = sb.toString();
        return hashedPassword;
    }

    void bruteForceAttack() {

    }

}
