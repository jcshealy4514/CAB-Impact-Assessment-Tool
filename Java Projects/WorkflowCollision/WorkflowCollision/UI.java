package WorkflowCollision;
import WorkflowCollision.Object.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UI implements ActionListener {
//NOTE- THIS REPRESENTS THE FRONT END THAT USERS WILL WORK WITH, I CREATED SOME BASIC CLASSES I EXPECT TO USE LATER
//I KNOW VERY LITTLE ABOUT SWING SO A LOT OF WHAT YOU SEE IS UNPOLISHED AND IS MY HALFASSED ATTEMPT AT LEARNING IT(no way!)
//TO CHANGE - I think the structure of this will not work, I'm planning to remove any nested classes and instead create methods for each component I want to create.
    //4-18 next step is to update the UI after adding a object + create a object through the UI

    String name;
    JFrame frame = new JFrame();
    JPanel contentPane = new JPanel();

    public UI(){
        //default Constructor
    }

    public UI(String componentName){
        this.name = componentName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static class MenuBar extends UI{
        //menu bar at top of UI
        //menubar
        static JMenuBar menuBar;
        //JMenu
        static JMenu fileMenu, fileSubMenu;
        //Menu items
        static JMenuItem saveOp, exportOp, settingOp, app;
        //Create a label
        static JLabel menuFrameLabel;

        public MenuBar(){
            super();
        }

        public static void buildMenuBar(UI ui){
            Database con = new Database();
            List<Application> appList = con.getObjects();

            menuBar = new JMenuBar();
            fileMenu = new JMenu("File");
            fileSubMenu = new JMenu("Applications");
            saveOp = new JMenuItem("Save");
            exportOp = new JMenuItem("Export");
            settingOp = new JMenuItem("Settings");
            //app1 = new JMenuItem("WorkDay");
            //app2 = new JMenuItem("EADB");
            menuFrameLabel = new JLabel("no task ");

            saveOp.addActionListener(ui);
            exportOp.addActionListener(ui);
            settingOp.addActionListener(ui);
            //app1.addActionListener(ui);
            //app2.addActionListener(ui);

            fileMenu.add(saveOp);
            fileMenu.add(exportOp);
            fileMenu.add(settingOp);
            //fileSubMenu.add(app1);
            //fileSubMenu.add(app2);
            fileMenu.add(fileSubMenu);
            menuBar.add(fileMenu);
            for(Application appName : appList){
                app = new JMenuItem(appName.getName());
                app.addActionListener(ui);
                fileSubMenu.add(app);
            }

            ui.frame.setJMenuBar(menuBar);
            ui.frame.add(menuFrameLabel);
        }
    }

    public static class Dialog extends UI{

        JOptionPane optionPane = new JOptionPane();

        public Dialog(){
            this.optionPane.toString(); //Filler
        }
    }

    public static void main(String[] args){

        UI ui = new UI("Main UI");
        ui.frame.setBounds(100,100,450,350);
        ui.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UI.MenuBar.buildMenuBar(ui);
        //I have decided that the MenuBar should not be a nested class. I should probably just create a method for each
        //component I want to build.  //UPDATE - But this kinda works though?
        ui.frame.setVisible(true); //Last Action
    }

    public static void addApplication(UI ui, Application application){
        String appName = application.getAttribute("name");
        JMenuItem newApp = new JMenuItem(appName);

        ui.frame.getJMenuBar().getMenu(0).getItem(3).add(newApp); //Surely there is a way to make this look better?
    }
}
