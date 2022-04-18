package WorkflowCollision;

import WorkflowCollision.Object.Application;

import java.io.*;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Iterator;
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

    public void saveObject(Object object){
        System.out.println("started saveObject method");
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
        //For this to work, I probably want to make a new Application Constructor that is private and takes String[] as the argument. Then make a method to make all
        //args in the array as their respective props
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
}
