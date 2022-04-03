import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AdventCode {
    
    Integer[][] heatMap = new Integer[100][100];
    int totalThreat;
    ArrayList<String> numChecked = new ArrayList<String>();

    public AdventCode() throws FileNotFoundException, IOException{
        try{
            //Method to pull in the puzzle input as variable fileContents
            this.buildArrays();
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
        FileReader file = new FileReader("C:/Users/Colton/Downloads/day9pt1.txt");
        BufferedReader reader = new BufferedReader(file);

        //reads file and adds the contents to variable fileContents
        String line = null;
        int i = 0;
        int n = 0;
        while((line = reader.readLine()) != null){
            for(char charAtIndex:line.toCharArray()){
                //System.out.println(charAtIndex);
                this.heatMap[i][n] = Character.getNumericValue(charAtIndex);
                n++;
            }
            i++;
            n = 0;
        }
        //System.out.print(this.heatMap[1][95]);
        //System.out.print(this.heatMap[1][96]);

        //close reader & file objects
        reader.close();
        file.close();
    }

    public void checkThreat(){
        boolean greaterThreat;
        for(int i = 0; i < 100; i++){
            for(int n = 0; n < 100; n++){
                greaterThreat = false;
                //Check leftside
                if(n > 0){
                    if(this.heatMap[i][n] >= this.heatMap[i][n-1]){
                        greaterThreat = true;
                        //System.out.println("Comparing " + this.heatMap[i][n] + " vs " + this.heatMap[i][n-1] + " at position " + i + "/" + n);
                    }
                }
                //Check rightside
                if(n < 99){
                    if(this.heatMap[i][n] >= this.heatMap[i][n+1]){
                        greaterThreat = true;
                        //System.out.println("Comparing " + this.heatMap[i][n] + " vs " + this.heatMap[i][n-1] + " at position " + i + "/" + n);
                    }
                }
                //Check overhead
                if(i > 0){
                    if(this.heatMap[i][n] >= this.heatMap[i-1][n]){
                        greaterThreat = true;
                        //System.out.println("Comparing " + this.heatMap[i][n] + " vs " + this.heatMap[i][n-1] + " at position " + i + "/" + n);
                    }
                }
                //Check below
                if(i < 99){
                    if(this.heatMap[i][n] >= this.heatMap[i+1][n]){
                        greaterThreat = true;
                        //System.out.println("Comparing " + this.heatMap[i][n] + " vs " + this.heatMap[i][n-1] + " at position " + i + "/" + n);
                    }
                }
                if(!greaterThreat){
                    System.out.println("Risk level is " + (this.heatMap[i][n] + 1) + " at " + i + "/" + n);
                    this.totalThreat += (this.heatMap[i][n] + 1);
                    this.calculateBasin(i,n);
                }
            }
        }
    }

    public void calculateBasin(int i, int n){
        this.numChecked = new ArrayList<String>();
        this.numChecked.add(i + "," + n);
        boolean numsNotChecked = false;
        
        while(!numsNotChecked){
            //Check Above
        }
        /*
        System.out.println(numChecked.get(0));
        System.out.println(numChecked.get(0).split(",")[0]);
        System.out.println(numChecked.get(0).split(",")[1]);
        */
    }
   

    public static void main(String[] args){
        try{
            AdventCode sensor = new AdventCode();
            sensor.checkThreat();
            System.out.println(sensor.totalThreat);
        }
        catch(FileNotFoundException err){
            System.out.println("I'm giving up!");
        }
        catch(IOException err){
            System.out.println("I'm giving up!");
        }

    }
}