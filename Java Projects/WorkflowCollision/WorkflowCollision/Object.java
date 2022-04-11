package WorkflowCollision;

import java.time.LocalDate;
import java.util.HashMap;

public class Object {
//NOTE - OBJECT CLASS DEFINES THE BASIC SET OF DATA ALL TYPES OF OBJECTS SHOULD HAVE IN THE DB
//THERE IS A NESTED CLASS CALLED APPLICATION WITHIN THIS FILE THAT REPRESENTS A APPLICATION OBJECT(no way!)
    HashMap<String, String> attributes = new HashMap<>();
    final LocalDate dateCreated = LocalDate.now();

    public Object(String objectName) {
        this.attributes.put("name", objectName);
        this.attributes.put("dateCreated", dateCreated.toString());
        this.attributes.put("lastModified", dateCreated.toString());
        this.attributes.put("createdBy", System.getProperty("user.name"));
    }

    public static class Application extends Object {

        public Application(String objectName,String description) {
            super(objectName);
            this.attributes.put("description",description);
            this.attributes.put("objectType","Application");
        }
    }

    public static void main(String[] args) {
        Object object = new Application("OnBase","EMR Solution used for storing documentation.");
        Database con = new Database();
        con.saveObject(object);
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