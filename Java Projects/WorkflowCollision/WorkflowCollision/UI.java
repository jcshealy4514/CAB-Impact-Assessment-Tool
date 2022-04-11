package WorkflowCollision;
import javafx.scene.layout.Border;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI implements ActionListener {
//NOTE- THIS REPRESENTS THE FRONT END THAT USERS WILL WORK WITH, I CREATED SOME BASIC CLASSES I EXPECT TO USE LATER
//I KNOW VERY LITTLE ABOUT SWING SO A LOT OF WHAT YOU SEE IS UNPOLISHED AND IS MY HALFASSED ATTEMPT AT LEARNING IT(no way!)
//TO CHANGE - I think the structure of this will not work, I'm planning to remove any nested classes and instead create methods for each component I want to create.
    JFrame frame = new JFrame();
    JPanel contentPane = new JPanel();

    public UI(){
        this.frame.setBounds(100,100,450,350);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public class Dialog{

        JOptionPane optionPane = new JOptionPane();

        public Dialog(){
            this.optionPane.toString(); //Filler
        }
    }

    public class MenuBar extends UI{
        //menu bar at top of UI
        //menubar
        static JMenuBar menuBar;
        //JMenu
        static JMenu fileMenu, fileSubMenu;
        //Menu items
        static JMenuItem saveOp, exportOp, settingOp, app1, app2;
        //Create a label
        static JLabel menuFrameLabel;

        public static UI buildMenuBar(UI ui){

            menuBar = new JMenuBar();
            fileMenu = new JMenu("File");
            fileSubMenu = new JMenu("Applications");
            saveOp = new JMenuItem("Save");
            exportOp = new JMenuItem("Export");
            settingOp = new JMenuItem("Settings");
            app1 = new JMenuItem("WorkDay");
            app2 = new JMenuItem("EADB");
            menuFrameLabel = new JLabel("no task ");

            saveOp.addActionListener(ui);
            exportOp.addActionListener(ui);
            settingOp.addActionListener(ui);
            app1.addActionListener(ui);
            app2.addActionListener(ui);

            fileMenu.add(saveOp);
            fileMenu.add(exportOp);
            fileMenu.add(settingOp);
            fileSubMenu.add(app1);
            fileSubMenu.add(app2);
            fileMenu.add(fileSubMenu);
            menuBar.add(fileMenu);

            ui.frame.setJMenuBar(menuBar);
            ui.frame.add(menuFrameLabel);


            return ui;
        }
    }

    public static void main(String[] args){

        UI ui = new UI();
        MenuBar menuBar = new MenuBar(); //I have decided that the MenuBar should not be a nested class. I should probably just create a method for each
        //component I want to build.
    }
}
