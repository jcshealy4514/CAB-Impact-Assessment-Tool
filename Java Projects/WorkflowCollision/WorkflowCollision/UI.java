package WorkflowCollision;
import javax.swing.*;

public class UI {

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    public UI(){
        JButton okButton = new JButton("Ok");
        this.frame.setBounds(100,100,450,350);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().add(okButton);
        okButton.setBounds(400,700,300,60); //Not sure how to adjust this button yet
        this.frame.setVisible(true);
    }

    public class Dialog{

        JOptionPane optionPane = new JOptionPane();

        public Dialog(){
            this.optionPane.toString(); //Filler
        }
    }

    public static void main(String[] args){

        UI box = new UI();
    }
}
