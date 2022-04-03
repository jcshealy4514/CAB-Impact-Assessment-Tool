package WorkflowCollision;

import java.time.LocalDate;
import java.util.HashMap;

public class Object {

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
        }
    }

    public static void main(String[] args) {
        Object object = new Application("Test Application","This is a test application used for testing");
        System.out.println(object.getAttributes());
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