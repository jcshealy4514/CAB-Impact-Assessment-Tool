import java.awt.Color;
import javax.swing.JFrame;


public class Pong extends JFrame {

    private final static int PLAYING_FIELD_WIDTH = 700, PLAYING_FIELD_HEIGHT = 700;
    //private pongPanel panel;

    public Pong(){
        setSize(PLAYING_FIELD_WIDTH,PLAYING_FIELD_HEIGHT);
        setTitle("Pong Time");
        setBackground(Color.BLACK);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new Ball());
        //panel = new pongPanel(this);
        //add(panel);
    }

    /*
    public pongPanel getPanel(){
        return panel;
    }
    */

    public static void main(String args[]){
        new Pong();
    }
    
}