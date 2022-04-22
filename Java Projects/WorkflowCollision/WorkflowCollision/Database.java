package WorkflowCollision;

import WorkflowCollision.Object.Application;

import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Database {
//NOTE- THIS REPRESENTS THE CONNECTION TO THE DATABASE/SQL SERVER I WILL USE TO STORE DATA
//I DONT HAVE A SQL SERVER THOUGH SO FOR THE TIME BEING I USED A CSV FILE(no way!)
    String hostName = "laptop-dkpa9ai8";
    String ipAddress = "192.168.1.173";
    String csv = "C:/Users/Colton/Downloads/tempDatabase.csv";
    int timeOut = 3000; //3 seconds

    public Database(){

    }

    public static void main(String[] args){
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

    public void saveObject(Object object, Boolean force){
        //Check for duplicate before anything else
        String appName = object.getName();
        Boolean isDuplicate = isDuplicate(appName);
        if(isDuplicate && force){
            removeObject(appName);
        }
        else if(isDuplicate){
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

    public void saveObject(Object object){
        //Check for duplicate before anything else
        Boolean force = false;
        String appName = object.getName();
        if(isDuplicate(appName)){
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

    public Application getObject(String appName){
        String line = null;
        String[] lineData;
        String[] data = new String[0];
        Application application;
        try {
            FileReader file = new FileReader(csv);
            BufferedReader reader = new BufferedReader(file);
            while ((line = reader.readLine()) != null) {
                lineData = line.split(",");
                if(lineData[2].equals(appName)){
                    data = lineData;
                }
            }
            reader.close();
            file.close();
            if(data.length > 0){
                application = new Application(data);
                return application;
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List getObjects(){
        //Consider adding a filter in the future - for optional parameters we will want to overload it with a method with zero args
        String line = null;
        String[] lineData;
        Application application;
        List applications = new ArrayList<>();
        try {
            FileReader file = new FileReader(csv);
            BufferedReader reader = new BufferedReader(file);
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                application = new Application(line.split(","));
                applications.add(application);
            }
            reader.close();
            file.close();
            return applications;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeObject(String appName){
        //This feels super inefficient- Java why do you challenge me so
        //Also instead of calling the exact index of a column, I probably want to check column names dynamically so people can add fields on this(like future me!)
        String endOfLine = System.getProperty("line.separator");
        String line = null;
        String[] lineData;
        String[] writeData;
        //List<Object> appList = new ArrayList<>();
        List<String> appList = new ArrayList<>();
        Object object;

        try {
            FileReader file = new FileReader(csv);
            BufferedReader reader = new BufferedReader(file);
            while ((line = reader.readLine()) != null) {
                lineData = line.split(",");
                if(!lineData[2].equals(appName)){
                    line = line + endOfLine;
                    appList.add(line);
                    //object = new Application(lineData[2],lineData[3]);
                    //appList.add(object);
                }
            }
            file.close();
            reader.close();

            FileWriter fileWriter = new FileWriter(csv);
            for(String writeLine : appList){
                fileWriter.append(writeLine);
            }
            fileWriter.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Boolean isDuplicate(String appName){
        if(this.getObject(appName) != null){
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }
    }
}
