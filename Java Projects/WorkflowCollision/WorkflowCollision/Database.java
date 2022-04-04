package WorkflowCollision;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.Map;
import WorkflowCollision.Object.Application;

public class Database {
//NOTE- THIS REPRESENTS THE CONNECTION TO THE DATABASE/SQL SERVER I WILL USE TO STORE DATA
//I DONT HAVE A SQL SERVER THOUGH SO FOR THE TIME BEING I USED A CSV FILE(no way!)
    String hostName = "laptop-dkpa9ai8";
    String ipAddress = "192.168.1.173";
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
        String endOfLine = System.getProperty("line.separator");
        String csv = "c:\\users\\Colton\\downloads\\tempDatabase.csv";
        Map.Entry<String,String> attribute;
        try (Writer writer = new FileWriter(csv)) {
            Iterator iterator = object.attributes.entrySet().iterator();
            while(iterator.hasNext()){
                attribute = (Map.Entry)iterator.next();
                writer.append(attribute.getValue());
                if(iterator.hasNext()) {
                    writer.append(',');
                }
            }
            writer.append(endOfLine);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public void saveObject(Application object){
        String endOfLine = System.getProperty("line.separator");
        String csv = "c:\\users\\Colton\\downloads\\tempDatabase.csv";
        Map.Entry<String,String> attribute;
        try (Writer writer = new FileWriter(csv)) {
            Iterator iterator = object.attributes.entrySet().iterator();
            while(iterator.hasNext()){
                attribute = (Map.Entry)iterator.next();
                writer.append(attribute.getValue());
                if(iterator.hasNext()) {
                    writer.append(',');
                }
            }
            writer.append(endOfLine);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
