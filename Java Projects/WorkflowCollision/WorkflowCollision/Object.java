package WorkflowCollision;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class Object {
//NOTE - OBJECT CLASS DEFINES THE BASIC SET OF DATA ALL TYPES OF OBJECTS SHOULD HAVE IN THE DB
//THERE IS A NESTED CLASS CALLED APPLICATION WITHIN THIS FILE THAT REPRESENTS A APPLICATION OBJECT(no way!)
    HashMap<String, String> attributes = new HashMap<>();
    String name;
    String createdBy;
    final LocalDate dateCreated = LocalDate.now();
    LocalDate lastModified;

    public Object(String objectName) {
        this.attributes.put("name", objectName);
        this.attributes.put("dateCreated", dateCreated.toString());
        this.attributes.put("lastModified", dateCreated.toString());
        this.attributes.put("createdBy", System.getProperty("user.name"));
    }

    //second constructor when calling the getObject method from the Database class
    public Object(String[] args){
        this.attributes.put("name", args[2]);
        this.attributes.put("dateCreated", args[0]);
        this.attributes.put("lastModified", args[4]);
        this.attributes.put("createdBy", args[1]);
    }

    public static class Application extends Object {

        public Application(String objectName,String description) {
            super(objectName);
            this.attributes.put("description",description);
            this.attributes.put("objectType","Application");
        }

        public Application(String[] args){
            super(args);
            this.attributes.put("description",args[3]);
            this.attributes.put("objectType",args[5]);
        }
    }

    public static void main(String[] args) throws IOException {
        //Create Application Object
        Application app = new Application("CustomApp2","No description.");

        //Add Application Object to DB (temp CSV file)
        Database con = new Database();
        //con.saveObject(app);

        //Get Application Objects
        //List<Application> appList = con.getObjects();
        //for(Application app : appList) {
        //    System.out.println(app.getAttributes());
        //}


        //Generate UI
        UI ui = UI.main(null);
        //Display new app defined above
        //ui.addApplication(ui,app);
    }

    public String getName() {
        //System.out.println("name: " + this.attributes.get("name"));
        return this.attributes.get("name");
    }

    public String getAttribute(String attribute) {
        try {
            //System.out.println(attribute + ": " + (this.attributes.get(attribute)));
            return this.attributes.get(attribute);
        } catch (Exception e) {
            return e.toString();
        }
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }
}