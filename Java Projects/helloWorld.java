import java.util.Scanner;

public class helloWorld {

    public static void main(String args[]){

        String UserInput;
        String PauseScript;
        System.out.println("Hello World!");
        Scanner scanner = new Scanner(System.in);
        UserInput = scanner.nextLine();
        System.out.println(UserInput);
        PauseScript = scanner.nextLine();
        scanner.close();

    }

}
