import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.Buffer;

public class Board extends JPanel implements ActionListener, Exit_Menu {

    final int B_WIDTH = 300;
    final int B_HEIGHT = 300;
    final int DOT_SIZE = 10;
    final int ALL_DOTS = 900;
    final int RAND_POS = 26;
    final int DELAY = 200;

    final int x[] = new int[ALL_DOTS];
    final int y[] = new int[ALL_DOTS];

    int bodyPart;
    int apple_x;
    int apple_y;
    int applesEaten;

    boolean leftDirection = false;
    boolean rightDirection = true;
    boolean upDirection = false;
    boolean downDirection = false;
    boolean inGame = true;

    Timer timer;
    Image ball;
    Image apple;
    Image head;

    private String highScore = "";

    //Scene Development.

    public Board() {
        addKeyListener(new TAdapter());
        setBackground(Color.blue);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
        GetHighScore();

    }
    private void loadImages() {


        ImageIcon loadBall = new ImageIcon("Snake/dot.png");
        ball = loadBall.getImage();

        ImageIcon loadApple = new ImageIcon("Snake/dot.png");
        apple = loadApple.getImage();

        ImageIcon loadHead = new ImageIcon("Snake/dot.png");
        head = loadHead.getImage();
    }

    private void gameOver(Graphics g) throws IOException {
        //Score
        String msg = "YOUR SCORE : " + applesEaten;
        Font small = new Font("Helvetica", Font.BOLD, 10);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg,230,15);

        Exit_Menu.super.gameOver_screen();
        CheckScore();

    }

    //The initial location of the snake.

    private void initGame() {

        bodyPart = 3;

        for (int z = 0; z < bodyPart; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            doDrawing(g);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        applesEaten = bodyPart - 3;
        String msg = "YOU DIED : " + applesEaten;
        Font small = new Font("Helvetica", Font.BOLD, 10);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg,230,15);
    }

    private void doDrawing(Graphics g) throws IOException {

        //Images are loaded here and displayed in the scene
        if (inGame) {

            g.drawImage(apple, apple_x, apple_y, this);

            for (int z = 0; z < bodyPart; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

            String msg = "SCORE : " + applesEaten;
            Font small = new Font("Helvetica", Font.BOLD, 10);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg,230,15);

        } else {

            gameOver(g);
        }
        if(highScore.equals(""))
        {
            highScore = this.GetHighScore();
        }
    }

    public void DrawScore(Graphics g)
    {
        g.drawString("HighScore: " + highScore, 0, B_HEIGHT*B_WIDTH+18);
    }


    //Movement and collision

    private void move() {

        //
        for (int z = bodyPart; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {

        for (int z = applesEaten; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    //Apple

    private void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {

            bodyPart++;
            locateApple();
        }
    }

    private void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    //High score record keeping

    private String GetHighScore(){

        //format
        //Navraj:100

        FileReader readFile = null;
        BufferedReader reader = null;

        try {
             readFile = new FileReader("highscore.dat");
             reader = new BufferedReader(readFile);
            return reader.readLine();
        } catch (Exception e) {
            return "Nobody:0";
        }
        finally
        {
            try{
                if (reader != null){
                    reader.close();
                }

            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
    public void CheckScore() throws IOException {
        if(highScore.equals(""))
            return;

        if (applesEaten > Integer.parseInt(highScore.split(":")[1])){
            String name_hs = JOptionPane.showInputDialog("NEW HIGHSCORE!! PLease enter your name:");
            highScore = name_hs + ":" + applesEaten;
            //saving the file
            File score_file = new File("highscore.dat");
            if(!score_file.exists())
            {
                try{
                    score_file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileWriter sc_writerFile = null;
            BufferedWriter writer_File = null;
            try{
                sc_writerFile = new FileWriter(score_file);
                writer_File = new BufferedWriter(sc_writerFile);
                writer_File.write(this.highScore);
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally{
                if (writer_File != null){
                    writer_File.close();
                }
            }
        }
    }

    //Action and Key Movement

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}
