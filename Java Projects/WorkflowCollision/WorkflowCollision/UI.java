package WorkflowCollision;
import javax.swing.*;

public class UI {
//NOTE- THIS REPRESENTS THE FRONT END THAT USERS WILL WORK WITH, I CREATED SOME BASIC CLASSES I EXPECT TO USE LATER
//I KNOW VERY LITTLE ABOUT SWING SO A LOT OF WHAT YOU SEE IS VERY UNPOLISHED AND IS HONESTLY GARBLED QUASI HUMAN LOGIC(no way!)
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
