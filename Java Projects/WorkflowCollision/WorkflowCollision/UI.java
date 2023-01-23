package WorkflowCollision;
import WorkflowCollision.Object.Application;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class UI implements ActionListener {
//NOTE- THIS REPRESENTS THE FRONT END THAT USERS WILL WORK WITH, I CREATED SOME BASIC CLASSES I EXPECT TO USE LATER
//TO CHANGE - I think the structure of this will not work, I'm planning to remove any nested classes and instead create methods for each component I want to create.
    //4-18 next step is to update the UI after adding a object + create a object through the UI
        //4-25 added a table and cleaned up a lot of stuff, still need to be able to create objects through the UI directly

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

        public static void buildMenuBar(UI ui,List appList){

            menuBar = new JMenuBar();
            fileMenu = new JMenu("File");
            fileSubMenu = new JMenu("Applications");
            saveOp = new JMenuItem("Save");
            exportOp = new JMenuItem("Export");
            settingOp = new JMenuItem("Settings");
            menuFrameLabel = new JLabel("no task ");

            saveOp.addActionListener(ui);
            exportOp.addActionListener(ui);
            settingOp.addActionListener(ui);

            fileMenu.add(saveOp);
            fileMenu.add(exportOp);
            fileMenu.add(settingOp);
            fileMenu.add(fileSubMenu);
            menuBar.add(fileMenu);
            for(Application appName : (List<Application>) appList){
                app = new JMenuItem(appName.getName());
                app.addActionListener(ui);
                fileSubMenu.add(app);
            }

            ui.frame.setJMenuBar(menuBar);
            ui.frame.add(menuFrameLabel);
        }
    }

    public static class Table extends UI{

        public Table(){
            super();
        }

        public static void buildAppTable(UI ui, List appList, String[] columnNames, String[][] appData){
            JTable appTable = new JTable(appData,columnNames);
            JScrollPane scrollPane = new JScrollPane(appTable);
            appTable.setFillsViewportHeight(true);
            ui.frame.add(appTable);
        }
    }

    public static UI main(String[] args) throws IOException {

        Database con = new Database();
        List<Application> appList = con.getObjects();
        String[] columnNames = con.getColumns();
        String[][] appData = con.getAppsAs2DArray();
        UI ui = new UI("Main UI");
        ui.frame.setBounds(100,100,450,350);
        ui.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UI.MenuBar.buildMenuBar(ui,appList);
        UI.Table.buildAppTable(ui,appList,columnNames,appData);
        ui.frame.setVisible(true); //Last Action
        return ui;
    }

    public static void addApplication(UI ui, Application application){
        String appName = application.getAttribute("name");
        JMenuItem newApp = new JMenuItem(appName);

        ui.frame.getJMenuBar().getMenu(0).getItem(3).add(newApp); //Surely there is a way to make this look better? any suggestions?
    }
}
