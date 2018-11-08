import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

/**
 * HackPanel
 */
public class HackPanel extends JPanel {
    // Frame f;
    private JButton bf_Button;
    private JButton rh_Button;
    private JButton dic_Button;
    private JComboBox user_ComboBox;
    String[] default_user_list = { "Shashwat", "Maven", "Sheetal" };
    ArrayList strings = new ArrayList<String>();
    String[] usersArr;

    public void loadUsers() {
        BufferedReader Reader;
        try {
            Reader = new BufferedReader(new FileReader("Passwords.csv"));
            System.out.println("Now opening the file");
            String line = Reader.readLine();
            while (line != null) {
                String[] userinfo = line.split(",");
                strings.add(userinfo[0]);
                line = Reader.readLine();
            }
            int i = 0;
            String[] usersArr = new String[strings.size()];
            for (Object var : strings) {
                usersArr[i] = var.toString();
                i = i + 1;
            }
            this.usersArr = usersArr;
            Reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HackPanel(int width, int height) {
        bf_Button = new JButton("Brute Force");
        rh_Button = new JButton("Rainbow Hash");
        dic_Button = new JButton("Dictionary Attack");

        loadUsers();

        user_ComboBox = new JComboBox(usersArr);
        add(bf_Button);
        add(rh_Button);
        add(dic_Button);
        add(user_ComboBox);

        user_ComboBox.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Entered");
                loadUsers();
            }
        });

        // adjust size and set layout
        setPreferredSize(new Dimension(width, height));
        setLayout(null);

        user_ComboBox.setBounds((width / 2) - 70, height / 9, 140, 40);
        bf_Button.setBounds((width / 2) - 70, 3 * (height / 9), 140, 40);
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

}
