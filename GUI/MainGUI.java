package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JTextField name_textArea;
	private JTextField pwd_field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		sl_contentPane.putConstraint(SpringLayout.NORTH, tabbedPane, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, tabbedPane, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, tabbedPane, 361, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, tabbedPane, 584, SpringLayout.WEST, contentPane);
		contentPane.add(tabbedPane);

		JPanel hackPanel = new JPanel();
		tabbedPane.addTab("Hack", null, hackPanel, null);
		hackPanel.setLayout(null);

		JTextField whometohack = new JTextField();
		whometohack.setBounds(193, 51, 168, 30);
		hackPanel.add(whometohack);

		JButton btnBruteForce = new JButton("Brute Force");
		btnBruteForce.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("clicked Brute Force");
				bf(getInfo(whometohack.getText()));
			}
		});
		btnBruteForce.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBruteForce.setBounds(193, 91, 168, 30);
		hackPanel.add(btnBruteForce);

		JButton btnDictionaryAttack = new JButton("Dictionary Attack");
		btnDictionaryAttack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("clicked Dictionary Attack");
				dic(whometohack.getText());
			}
		});
		btnDictionaryAttack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDictionaryAttack.setBounds(193, 152, 168, 30);
		hackPanel.add(btnDictionaryAttack);

		JButton btnRainbowHash = new JButton("Rainbow Hash");
		btnRainbowHash.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("clicked Rainbow Hash");
			}
		});
		btnRainbowHash.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRainbowHash.setBounds(193, 213, 168, 30);
		hackPanel.add(btnRainbowHash);

		JPanel registerPanel = new JPanel();
		tabbedPane.addTab("Register", null, registerPanel, null);
		SpringLayout sl_registerPanel = new SpringLayout();
		registerPanel.setLayout(sl_registerPanel);

		JLabel usernameLabel = new JLabel("Username");
		sl_registerPanel.putConstraint(SpringLayout.NORTH, usernameLabel, 65, SpringLayout.NORTH, registerPanel);
		sl_registerPanel.putConstraint(SpringLayout.WEST, usernameLabel, 133, SpringLayout.WEST, registerPanel);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerPanel.add(usernameLabel);

		name_textArea = new JTextField();
		sl_registerPanel.putConstraint(SpringLayout.NORTH, name_textArea, 62, SpringLayout.NORTH, registerPanel);
		sl_registerPanel.putConstraint(SpringLayout.WEST, name_textArea, 279, SpringLayout.WEST, registerPanel);
		name_textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		name_textArea.setColumns(10);
		registerPanel.add(name_textArea);

		JLabel passwordLabel = new JLabel("Password");
		sl_registerPanel.putConstraint(SpringLayout.NORTH, passwordLabel, 133, SpringLayout.NORTH, registerPanel);
		sl_registerPanel.putConstraint(SpringLayout.WEST, passwordLabel, 133, SpringLayout.WEST, registerPanel);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerPanel.add(passwordLabel);

		pwd_field = new JTextField();
		sl_registerPanel.putConstraint(SpringLayout.NORTH, pwd_field, -3, SpringLayout.NORTH, passwordLabel);
		sl_registerPanel.putConstraint(SpringLayout.WEST, pwd_field, 0, SpringLayout.WEST, name_textArea);
		sl_registerPanel.putConstraint(SpringLayout.EAST, pwd_field, 0, SpringLayout.EAST, name_textArea);
		pwd_field.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerPanel.add(pwd_field);

		JButton button = new JButton("Register");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				logPasswords(pwd_field.getText());
			}
		});
		sl_registerPanel.putConstraint(SpringLayout.NORTH, button, 69, SpringLayout.SOUTH, pwd_field);
		sl_registerPanel.putConstraint(SpringLayout.WEST, button, 240, SpringLayout.WEST, registerPanel);
		button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerPanel.add(button);
	}

	public static void dic(String name) {
		BufferedReader Reader;
		String[] userinfo = getInfo(name);
		try {
			Reader = new BufferedReader(new FileReader("dictionary.txt"));
			String line = Reader.readLine();
			while (line != null) {
				if (hash(line).equalsIgnoreCase(userinfo[1])) {
					JOptionPane.showMessageDialog(null, "The password is : " + line);
				}
				line = Reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String hash(String s) {
		String hashedPassword;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashInBytes = md.digest(s.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (byte b : hashInBytes) {
				sb.append(String.format("%02x", b));
			}
			hashedPassword = sb.toString();
			return hashedPassword;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "aaaaaaa";
	}

	public void bf(String[] info) {
		System.out.println("In BF Method");
		// char[] chars =
		// "`1234567890-=~!@#$%^&*()_+;:,<.>/?abcdefghijklmnopqrstuvwxyzQWERTYUIOPASDFGHJKLZXCVBNM
		// ".toCharArray();
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		for (int len = 0; len <= 4; len++) {
			System.out.println("------------------------------------------------------------------------------------");
			brute_force(info[1], chars, len, new char[len], 0);
		}
	}

	public static void brute_force(String pwd, char[] chars, int len, char[] build, int pos) {
		if (pos == len) {
			String word = new String(build);
			//System.out.println(word);
			if (hash(word).equals(pwd)) {
				JOptionPane.showMessageDialog(null, "The Password is : " + word);
			}
			return;
		}
		for (int i = 0; i < chars.length; i++) {
			build[pos] = chars[i];
			brute_force(pwd, chars, len, build, pos + 1);
		}
	}

	public static String[] getInfo(String name) {
		System.out.println("The name to check is : " + name);
		BufferedReader Reader;
		try {
			Reader = new BufferedReader(new FileReader("Passwords.csv"));
			System.out.println("Now opening the file");
			String line = Reader.readLine();
			while (line != null) {
				//System.out.println(line);
				String[] userinfo = line.split(",");
				if (userinfo[0].equalsIgnoreCase(name)) {
					System.out.println("Found : " + line);
					return userinfo;
				}
				line = Reader.readLine();
			}
			Reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] defaultreturn = { "haha", "wrong password" };
		return defaultreturn;
	}

	public void logPasswords(String pwd) {
		try {
			String hashedPassword = hash(pwd);
			System.out.println("User "+name_textArea.getText()+" stored in database with hashed password as"+ hashedPassword);
			FileWriter out = new FileWriter("Passwords.csv", true);
			out.append(name_textArea.getText() + "," + hashedPassword + "\n");
			out.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
