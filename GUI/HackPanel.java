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

    public void loadUsers(){
        BufferedReader Reader;
        try {
            Reader = new BufferedReader(new FileReader("../Passwords.csv"));
            System.out.println("Now opening the file");
            String line = Reader.readLine();
            while (line != null) {
                System.out.println(line);
                String[] userinfo = line.split(",");
                System.out.println(userinfo[0]);
                strings.add(userinfo[0]);
                line = Reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HackPanel(int width, int height) {
        loadUsers();
        int i;
        bf_Button = new JButton("Brute Force");
        rh_Button = new JButton("Rainbow Hash");
        dic_Button = new JButton("Dictionary Attack");
        //String[] usersArr = new String[strings.size()];
        //usersArr = strings.toArray(usersArr);
        System.out.println(strings.toString());
        user_ComboBox = new JComboBox(default_user_list);
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
