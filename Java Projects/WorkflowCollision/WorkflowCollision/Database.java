package WorkflowCollision;

import WorkflowCollision.Object.Application;

import java.io.*;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.Map;

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
        System.out.println("started saveObject method");
        String endOfLine = System.getProperty("line.separator");
        String csv = "C:/Users/Colton/Downloads/tempDatabase.csv";
        Map.Entry<String,String> attribute;
        FileWriter writer = null;
        BufferedWriter buffWriter = null;
        PrintWriter printWriter = null;
        try {
            //I know there is a simpler solution but this works and I intend to use an actual DB later!
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
}
