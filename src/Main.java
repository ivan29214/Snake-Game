import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Snake_UI implements ActionListener {
    public static void main(String[] args) {
        createWindow();
    }

    private static void createWindow() {
        JFrame frame = new JFrame("The Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(frame);
        frame.setBackground(Color.BLACK);
        frame.setLayout(new BasicScrollBarUI());
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);

        Main snake = new Main();

        JButton button1 = new JButton("Login");
        JButton button2 = new JButton("SignUp");
        JButton button3 = new JButton("Play");
        JButton button4 = new JButton("Rules");

        button1.setBounds(150,60,100,50);
        button2.setBounds(150,120,100,50);
        button3.setBounds(150,180,100,50);
        button4.setBounds(150,240,100,50);

        button1.setLocation(150, 60);

        button1.setBackground(Color.red);button1.setForeground(Color.green);
        button2.setBackground(Color.red);button2.setForeground(Color.green);
        button3.setBackground(Color.red);button3.setForeground(Color.red);
        button4.setBackground(Color.red);button4.setForeground(Color.blue);

        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);

        button1.addActionListener(snake);
        button2.addActionListener(snake);
        button3.addActionListener(snake);
        button4.addActionListener(snake);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.charAt(0) == 'L') {
            login();
        }else if (command.charAt(0) == 'S') {
            signup();
        }else if (command.charAt(0) == 'P') {
            EventQueue.invokeLater(() -> {
                JFrame ex = new Snake();
                ex.setVisible(true);
            });
        }else {
            try {
                rules();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
class Snake_UI{
    String signup(){
        String a = JOptionPane.showInputDialog("Enter your name", null);
        return a;

    }

    String login(){
        String a = JOptionPane.showInputDialog("Enter the values", null);
        return a;
    }

    void rules() throws FileNotFoundException,IOException{
        BufferedReader br = new BufferedReader(new FileReader("/Users/guranshvir/Documents/VS CODE/Snake/Rules.txt"));
        ArrayList<Character> a = new ArrayList<Character>();
        for (int i = 0; i < 116; i++) {
            a.add((char) br.read());
        }
        StringBuffer strng = new StringBuffer();

        for(char i : a){
            strng.append(i);
        }

        JOptionPane.showMessageDialog(null,strng);
        br.close();

    }
}