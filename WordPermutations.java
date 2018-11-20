import javax.swing.*;
public class WordPermutations {
    public static void main(String[] args) {
        //char[] chars = "`1234567890-=~!@#$%^&*()_+;:,<.>/?abcdefghijklmnopqrstuvwxyzQWERTYUIOPASDFGHJKLZXCVBNM ".toCharArray();
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        String actual_pwd = JOptionPane.showInputDialog(null, "Enter message to encrypt");

        for (int len = 0; len <= 3; len++) {
            System.out.println("------------------------------------------------------------------------------------");
            brute_force(actual_pwd, chars, len, new char[len], 0);
        }
    }

    public static void brute_force(String pwd, char[] chars, int len, char[] build, int pos) {
        if (pos == len) {
            String word = new String(build);

            System.out.println(word);

            if (word.equals(pwd)) {
                System.out.println("***********************************");
                System.out.println("***********************************");
                System.out.println("***********  Found  ***************");
                System.out.println("***********************************");
                System.out.println("***********************************");
                JOptionPane.showMessageDialog(null, "Found : " + word);
            }
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            build[pos] = chars[i];
            brute_force(pwd,chars, len, build, pos + 1);
        }
    }
}
