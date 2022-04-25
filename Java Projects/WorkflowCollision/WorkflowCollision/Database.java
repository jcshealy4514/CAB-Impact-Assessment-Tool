package WorkflowCollision;

import WorkflowCollision.Object.Application;

import java.io.*;
import java.net.InetAddress;
import java.util.*;

public class Database {
//NOTE- THIS REPRESENTS THE CONNECTION TO THE DATABASE/SQL SERVER I WILL USE TO STORE DATA
//I DONT HAVE A SQL SERVER THOUGH SO FOR THE TIME BEING I USED A CSV FILE(no way!)
    String hostName = "laptop-dkpa9ai8";
    String ipAddress = "192.168.1.173";
    String csv = (System.getenv("USERPROFILE")) + "/Downloads/tempDatabase.csv";
    int timeOut = 3000; //3 seconds

    public Database(){

    }

    public static void main(String[] args) throws IOException {
        Database myDatabase = new Database();
        if(myDatabase.isReachable().equals("true")){
            System.out.println("Connection successful!");
        }
        else{
            System.out.println("Connection failed!");
        }
        Application app = myDatabase.getObject("OnBase");
        System.out.println(app.getAttributes());
    }

    public String isReachable(){
        try {
            boolean status = InetAddress.getByName(this.hostName).isReachable(timeOut);
            return Boolean.toString(status);
        }
        catch(Exception e){
            return "Unknown error checking connection";
        }
    }

    public void saveObject(Object object, Boolean force) throws IOException {
        //Check for duplicate before anything else
        String appName = object.getName();
        Boolean objectExists = objectExists(appName);
        if(objectExists && force){
            removeObject(appName);
        }
        else if(objectExists){
            return;
        }

        //Continue to save object
        String endOfLine = System.getProperty("line.separator");
        Map.Entry<String,String> attribute;
        FileWriter writer = null;
        BufferedWriter buffWriter = null;
        PrintWriter printWriter = null;
        try {
            //I know there is a simpler solution but this works and I intend to use an actual DB later! NEED TO ADD DUPE CHECKING
            writer = new FileWriter(csv,true);
            buffWriter = new BufferedWriter(writer);
            printWriter = new PrintWriter(buffWriter);
            Iterator iterator = object.attributes.entrySet().iterator();
            while(iterator.hasNext()){
                attribute = (Map.Entry)iterator.next();
                printWriter.print(attribute.getValue());
                if(iterator.hasNext()) {
                    printWriter.print(',');
                }
            }
            printWriter.print(endOfLine);
            printWriter.close();
        }
        catch(Exception e){

        }
        finally{
            try {
                writer.close();
            }
            catch(IOException e){

            }
            try {
                buffWriter.close();
            }
            catch(IOException e){

            }
            printWriter.close();
        }
    }

    public void saveObject(Object object) throws IOException {
        //default force to false
        Boolean force = false;
        saveObject(object, force);
    }

    public Application getObject(String appName) throws IOException {
        String line = null;
        String[] lineData;
        String[] data = new String[0];
        Application application;
        BufferedReader reader = readData();
        while ((line = reader.readLine()) != null) {
            lineData = line.split(",");
            if(lineData[2].equals(appName)){
                data = lineData;
            }
        }
        reader.close();
        if(data.length > 0){
            application = new Application(data);
            return application;
        }
        return null;
    }

    public List<Application> getObjects() throws IOException {
        //Consider adding a filter in the future - for optional parameters we will want to overload it with a method with zero args
        String line = null;
        String[] lineData;
        Application application;
        List<Application> applications = new ArrayList<Application>();
        BufferedReader reader = readData();
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            application = new Application(line.split(","));
            applications.add(application);
        }
        reader.close();
        return applications;
    }

    public void removeObject(String appName) throws IOException {
        //This feels super inefficient- Java why do you challenge me so
        //Also instead of calling the exact index of a column, I probably want to check column names dynamically so people can add fields on this(like future me!)
        String endOfLine = System.getProperty("line.separator");
        String line = null;
        String[] lineData;
        String[] writeData;
        List<String> appList = new ArrayList<>();
        Object object;

        BufferedReader reader = readData();
        while ((line = reader.readLine()) != null) {
            lineData = line.split(",");
            if(!lineData[2].equals(appName)){
                line = line + endOfLine;
                appList.add(line);
            }
        }
        reader.close();

        FileWriter fileWriter = new FileWriter(csv);
        for(String writeLine : appList){
            fileWriter.append(writeLine);
        }
        fileWriter.close();
    }

    public Boolean objectExists(String appName) throws IOException {
        if(this.getObject(appName) != null){
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }
    }

    public String[] getColumns() throws IOException {
        String[] columnData = null;
        String line;
        BufferedReader reader = readData();
        if ((line = reader.readLine()) != null) {
            columnData = line.split(",");
        }
        reader.close();
        return columnData;
    }

    public String[][] getAppsAs2DArray() throws IOException {
        String[][] array = new String[10][10];
        String[] rowData = new String[0];
        String line;
        int row = 0;
        int column = 0;
        BufferedReader reader = readData();
        while ((line = reader.readLine()) != null) {
            rowData = line.split(",");
            for(String data : rowData) {
                array[row][column] = data;
                column++;
            }
            column = 0;
            row++;
        }
        reader.close();
        return array;
    }

    public int getObjectCount() throws IOException {
        int size = 0;
        String line;
        BufferedReader reader = readData();
        while ((line = reader.readLine()) != null) {
            size++;
        }
        reader.close();
        return size;
    }

    public BufferedReader readData() throws FileNotFoundException, IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reader;
    }
}
