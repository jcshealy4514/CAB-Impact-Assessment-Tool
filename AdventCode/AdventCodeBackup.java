import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventCode {
    
    final int floorLevel = 0;
    int depthIncreases = 0;
    List<Integer> listOfMeasurements = new ArrayList<Integer>();

    public AdventCode() throws FileNotFoundException, IOException{
        try{
            depthIncreases = 0;
            listOfMeasurements = buildArray();
        }
        catch(FileNotFoundException err){
            System.out.println("I'm giving the up!");
        }
        catch(IOException err){
            System.out.println("I'm giving the up!");
        }

    }

    public List<Integer> buildArray() throws FileNotFoundException, IOException {
        FileReader file = new FileReader("C:/Users/Colton/Downloads/Book.csv");
        BufferedReader reader = new BufferedReader(file);
        String line = null;
        Integer nextLine = null;
        List<Integer> listOfMeasurements = new ArrayList<Integer>();
        while((line = reader.readLine()) != null){
            nextLine = Integer.parseInt(line);
            //System.out.println(nextLine);
            listOfMeasurements.add(nextLine);
        }
        reader.close();
        file.close();
        return listOfMeasurements;
    }

    public int getSum(int index){
        return this.listOfMeasurements.get(index) + this.listOfMeasurements.get(index + 1) + this.listOfMeasurements.get(index + 2);
    }
    

    public static void main(String[] args){
        try{
            AdventCode results = new AdventCode();
            int currentMeasurement = results.getSum(0);
            for(int i = 1; i <= results.listOfMeasurements.size() - 3; i++){
                int nextMeasurement = results.getSum(i);
                if(currentMeasurement < nextMeasurement){
                    System.out.println(nextMeasurement + "[Increased]");
                    results.depthIncreases++;
                }
                else if(currentMeasurement > nextMeasurement){
                    System.out.println(nextMeasurement + "[Decreased]");
                }
                else{
                    System.out.println(nextMeasurement + "[No Change]");
                }
                currentMeasurement = nextMeasurement;
            }
            System.out.println(results.depthIncreases);
        }
        catch(FileNotFoundException err){
            System.out.println("I'm giving the up!");
        }
        catch(IOException err){
            System.out.println("I'm giving the up!");
        }

    }
}