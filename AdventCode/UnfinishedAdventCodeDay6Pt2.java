import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class AdventCode {
    
    ArrayList<Integer> fileContents = new ArrayList<Integer>();
    BigInteger[] duplicationTime = new BigInteger[9];
    final BigInteger singleVal = new BigInteger("1");


    public AdventCode() throws FileNotFoundException, IOException{
        try{
            //Method to pull in the puzzle input as variable fileContents
            this.buildArrays();
            for(int i = 0; i < 9; i++){
                this.duplicationTime[i] = new BigInteger("0");
            }
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
        FileReader file = new FileReader("C:/Users/Colton/Downloads/day6pt1.txt");
        BufferedReader reader = new BufferedReader(file);

        //reads file and adds the contents to variable fileContents
        for(String num : reader.readLine().split(",")){
            fileContents.add(Integer.parseInt(num));
        }

        //close reader & file objects
        reader.close();
        file.close();
    }

    public void progressDay(){
        int populationSize = this.fileContents.size();
        BigInteger newFish = this.duplicationTime[0];
        BigInteger refreshedFish = this.duplicationTime[0];
        for(int lampFish = 0; lampFish < populationSize; lampFish++){
            int duplicate = this.fileContents.get(lampFish);
            if(duplicate == 0){
                this.fileContents.set(lampFish, 6);
                newFish = newFish.add(singleVal);
            }
            else{
                this.fileContents.set(lampFish,(duplicate - 1));
            }
            for(int daysLeft = 0; daysLeft < 8; daysLeft++){
                this.duplicationTime[daysLeft] = this.duplicationTime[(daysLeft)+1];
            }
            this.duplicationTime[8] = newFish;
            this.duplicationTime[6] = this.duplicationTime[6].add(refreshedFish);
        }
        //System.out.println("We have " + newFish + " new fish!");
        //System.out.println("today we added " + newFish + " fish!");
        //System.out.println(this.duplicationTime[6] + " should now equal " + this.duplicationTime[7]);
    }


    public static void main(String[] args){
        try{
            AdventCode lampFishGrowth = new AdventCode();
            for(int days = 0; days < 256; days++){
                //System.out.println("We are on day " + days + " and we are up to " + lampFishGrowth.fileContents.size() + " lanternfish");
                lampFishGrowth.progressDay();
            }
            BigInteger totalFish = new BigInteger("300");
            for(int lanternFish = 0; lanternFish < 9; lanternFish++){
                //System.out.println(lampFishGrowth.duplicationTime[lanternFish]);
                totalFish = totalFish.add(lampFishGrowth.duplicationTime[lanternFish]);              
            }
            System.out.println("WATCH OUT!!!      " + totalFish);
        }
        catch(FileNotFoundException err){
            System.out.println("I'm giving up!");
        }
        catch(IOException err){
            System.out.println("I'm giving up!");
        }

    }
}