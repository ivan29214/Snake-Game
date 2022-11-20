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
     class SignUp extends JFrame{
        JFrame f;
        JTextField t1,t2;
        JButton b1;
        JLabel j1,j2,j3,j4;

        SignUp(){
            setLayout(null);
            j1 =new JLabel("Signup");
            j1.setFont(new Font("Times New Roman", Font.BOLD,30));
            j1.setForeground(Color.BLUE);
            j1.setBounds(130,10,300,30);
            add(j1);
            j3 =new JLabel("Username");
            j3.setBounds(40,60,90,30);
            add(j3);
            t1 = new JTextField(100);
            j4 =new JLabel("Password");
            j4.setBounds(40,100,90,30);
            add(j4);
            t2=new JPasswordField(60);
            b1=new JButton("Submit");


            t1.setBounds(100,60,120,30);
            t2.setBounds(100,100,120,30);
            b1.setBounds(120,140,80,30);

            b1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    try {
                        FileWriter fw = new FileWriter("login.txt",true);
                        fw.write(t1.getText()+"\t"+t2.getText()+"\n");
                        fw.close();
                        JOptionPane.showMessageDialog(f,"Registration Completed");
                        dispose();

                    }catch(Exception e) {}


                }

            });
            add(t1);
            add(t2);
            add(b1);
        }
    }


    class Signin extends JFrame {
        JFrame f;

        JTextField t1, t2;
        JButton b1,b2;
        JLabel l1, l2,l3,l4;
        Signin(){
            setLayout(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            l1 =new JLabel("Login");
            l1.setFont(new Font("Times New Roman", Font.BOLD,30));
            l1.setForeground(Color.BLUE);
            l1.setBounds(130,10,300,30);
            add(l1);

            l3 =new JLabel("Username");
            l3.setBounds(40,60,90,30);
            add(l3);
            t1=new JTextField(60);
            l4 =new JLabel("Password");
            l4.setBounds(40,100,90,30);
            add(l4);
            t2=new JPasswordField(60);
            b1=new JButton("SignIn");
            b2=new JButton("SignUp");

            t1.setBounds(100,60,120,30);
            t2.setBounds(100,100,120,30);
            b1.setBounds(120,140,80,30);
            b2.setBounds(120,170,80,30);

            l2 =new JLabel("");
            l2.setBounds(250,80,300,30);
            add(l2);

            add(t1);
            add(t2);
            add(b1);
            add(b2);



            b1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    boolean matched =false;
                    String uname =t1.getText().toString();
                    String pwd =t2.getText().toString();
                    try {
                        FileReader fr = new FileReader("login.txt");
                        BufferedReader br= new BufferedReader(fr);
                        String line;
                        while((line=br.readLine())!=null) {
                            if(line.equals(uname+"\t"+pwd)) {
                                matched=true;
                                break;
                            }
                        }
                        fr.close();
                    }catch(Exception e) {}

                    if(matched) {

                        JOptionPane.showMessageDialog(f,"Hello, Welcome to Snake Game");
                    }


                    else {
                        l2.setText("Invalid username /password");
                        l2.setForeground(Color.RED);
                    }
                }
            });




            b2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    SignUp s = new SignUp();
                    s.setVisible(true);
                    s.setBounds(200,200,500,300);
                }
            });

        }}
    class login extends JFrame{

            Signin l=new Signin();
            l.setBounds(400,200,500,300);
            l.setVisible(true);


    }


    void rules() throws FileNotFoundException,IOException{
        BufferedReader br = new BufferedReader(new FileReader("/Users/solar/Documents/Rules.txt"));
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
