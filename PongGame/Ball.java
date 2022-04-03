import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

public class Ball extends JPanel {

    private final int BALL_DIAMETER = 50;
    private final Color BALL_COLOR = Color.gray;
    int x = 300;
    int y = 300;


    public Ball(){
        int diameter = BALL_DIAMETER;
        Color color = BALL_COLOR;
        //int x = 300;
        //int y = 300;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(BALL_COLOR);
        g.drawOval(x,y,30,30); //draws circle
        g.fillOval(x,y,30,30); //adds color to circle
        g.setColor(Color.black);
    }

    public static void main(String[] args){
        Ball ball = new Ball();
        JPanel ballFrame = new JPanel();
        ball.x = 200;
        ball.y = 200;
        ballFrame.setSize(30, 30);
        ballFrame.setLocation(350, 350);
        ballFrame.setVisible(true);
        ball.repaint();
    }

    /*
    public void paint(Graphics g){
        g.drawOval(50,50,50,50);
        g.setColor(Color.blue);
        g.fillOval(50,50,50,50);

    }
    */

}