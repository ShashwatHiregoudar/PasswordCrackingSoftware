import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MainGUI
 */
public class MainGUI {
    JFrame frame;
    MainGUI() {
        frame = new JFrame("Password Cracking tool");
        frame.setSize(400, 250);
        Dimension frmdim = frame.getSize();
        RegisterPanel rpanel = new RegisterPanel(frmdim.width,frmdim.height);
        HackPanel hpanel = new HackPanel(frmdim.width, frmdim.height);
        JTabbedPane tp = new JTabbedPane();
        tp.add("Quick Register", rpanel);
        tp.add("Hack", hpanel);
        frame.add(tp);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
