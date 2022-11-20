import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public interface Exit_Menu {

    public default void gameOver_screen() {

        //Retry frame
        JFrame frame = new JFrame("The Snake Game");

        frame.setSize(300, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setLayout(null);
        //Button for retry
        JButton button1 = new JButton("Retry");
        button1.setBounds(110,70,70,50);
        frame.add(button1);

        JButton button2 = new JButton("High\tScores");
        button2.setBounds(100,130,90,50);
        frame.add(button2);
        button2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Snake ex = new Snake();
                try {
                    ex.High_score();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }});
        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    JFrame ex = new Snake();
                    ex.setVisible(true);});
            }


        });


    }}

abstract class test implements Exit_Menu {
    @Override
    public void gameOver_screen() {
        Exit_Menu.super.gameOver_screen();
    }

}