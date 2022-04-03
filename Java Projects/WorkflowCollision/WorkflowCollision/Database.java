package WorkflowCollision;

import java.net.InetAddress;

public class Database {
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
}
