import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Snake extends JFrame {
    public Snake() {
        initUI();
    }

    private void initUI() {
        add(new Board());
        setResizable(true);
        pack();
        setTitle("Snake");
        setLocationRelativeTo(null);

    }

    void High_score() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("highscore.dat"));
        ArrayList<Character> a = new ArrayList<Character>();
        for (int i = 0; i < 8; i++) {
            a.add((char) br.read()); }
        StringBuffer strng = new StringBuffer();

        for(char i : a){
            strng.append(i);}

        JOptionPane.showMessageDialog(null,strng);
        br.close();}
}
