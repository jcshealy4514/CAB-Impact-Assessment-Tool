import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AdventCode {
    
    ArrayList<Integer> fileContents = new ArrayList<Integer>();
    double sum;

    public AdventCode() throws FileNotFoundException, IOException{
        try{
            //Method to pull in the puzzle input as variable fileContents
            this.buildArrays();
            this.sum = 0;
        }
        catch(FileNotFoundException err){
            System.out.println("I'm giving up!");
        }
        catch(IOException err){
            System.out.println("I'm giving up!");
        }

    }

    public void buildArrays() throws FileNotFoundException, IOException {
        //Pulls in file
        FileReader file = new FileReader("C:/Users/Colton/Downloads/day7pt1.txt");
        BufferedReader reader = new BufferedReader(file);

        //reads file and adds the contents to variable fileContents
        for(String num : reader.readLine().split(",")){
            fileContents.add(Integer.parseInt(num));
        }

        //close reader & file objects
        reader.close();
        file.close();
    }

    public static void main(String[] args){
        try{
            AdventCode crabShips = new AdventCode();
            crabShips.fileContents.sort(Comparator.naturalOrder());
            for(int i = 0; i < crabShips.fileContents.size(); i++){
                crabShips.sum += crabShips.fileContents.get(i);
            }
            double n = crabShips.sum;
            System.out.println("Mean is " + crabShips.sum / crabShips.fileContents.size());
            System.out.println("Median is " + crabShips.fileContents.get((crabShips.fileContents.size() / 2)));
            System.out.println("??? " + (crabShips.fileContents.get(crabShips.fileContents.size() / 2)) / crabShips.sum);
            
        }
        catch(FileNotFoundException err){
            System.out.println("I'm giving up!");
        }
        catch(IOException err){
            System.out.println("I'm giving up!");
        }

    }
}