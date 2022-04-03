import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventCode {
    
    final int floorLevel = 0;
    int depth = 0;
    int distance = 0;
    List<String> commands = new ArrayList<String>();

    public AdventCode() throws FileNotFoundException, IOException{
        try{
            depth = 0;
            distance = 0;
            commands = buildArray();
        }
        catch(FileNotFoundException err){
            System.out.println("I'm giving the up!");
        }
        catch(IOException err){
            System.out.println("I'm giving the up!");
        }

    }

    public List<String> buildArray() throws FileNotFoundException, IOException {
        FileReader file = new FileReader("C:/Users/Colton/Downloads/Day2.csv");
        BufferedReader reader = new BufferedReader(file);
        String line = null;
        //Integer nextLine = null;
        List<String> listOfMoves = new ArrayList<String>();
        while((line = reader.readLine()) != null){
            //nextLine = Integer.parseInt(line);
            //System.out.println(nextLine);
            listOfMoves.add(line);
        }
        reader.close();
        file.close();
        return listOfMoves;
    }

    /*
    public int getSum(int index){
        return this.listOfMeasurements.get(index) + this.listOfMeasurements.get(index + 1) + this.listOfMeasurements.get(index + 2);
    }
    */

    public void moveForward(String x){
        distance += Integer.parseInt(x);
    }

    public void changeDepth(String direction, String distance){
        if(direction.equals("up")){
            depth -= Integer.parseInt(distance);
        }
        if(direction.equals("down")){
            depth += Integer.parseInt(distance);
        }
    }

    public static void main(String[] args){
        try{
            AdventCode results = new AdventCode();
            for(int i = 0; i < results.commands.size(); i++){
                String[] movements = (results.commands.get(i)).split(" ");
                if(movements[0].equals("forward")){
                    results.moveForward(movements[1]);
                    System.out.println("x pos is now " + results.distance);
                }
                else{
                    results.changeDepth(movements[0], movements[1]);
                    System.out.println("y pos is now " + results.depth);
                }
                //System.out.println(movements[0]);
            }
            int TotalDistance = results.distance * results.depth;
            System.out.println("total distance moved is " + TotalDistance);
        }
        catch(FileNotFoundException err){
            System.out.println("I'm giving the up!");
        }
        catch(IOException err){
            System.out.println("I'm giving the up!");
        }

    }
}