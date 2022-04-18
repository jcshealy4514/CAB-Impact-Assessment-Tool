package WorkflowCollision;

import java.time.LocalDate;
import java.util.HashMap;

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

    public static void main(String[] args) {
        //Create Application Object
        Object object = new Application("OnBase","EMR Solution used for storing documentation.");

        //Add Application Object to DB (temp CSV file)
        Database con = new Database();
        con.saveObject(object);

        //Generate UI
        UI.main(null);
    }

    public String getName() {
        System.out.println("name: " + this.attributes.get("name"));
        return this.attributes.get("name");
    }

    public String getAttribute(String attribute) {
        try {
            System.out.println(attribute + ": " + (this.attributes.get(attribute)));
            return attribute + ": " + (this.attributes.get(attribute));
        } catch (Exception e) {
            return e.toString();
        }
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }
}