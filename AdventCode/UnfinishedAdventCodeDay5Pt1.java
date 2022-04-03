import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AdventCode {
    
    int startX;
    int startY;
    int endX;
    int endY;
    Integer[][][] pathTracking = new Integer[999][999][2];
    ArrayList<String> fileContents = new ArrayList<String>();


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
        FileReader file = new FileReader("C:/Users/Colton/Downloads/day5pt1.txt");
        BufferedReader reader = new BufferedReader(file);

        //reads file and adds the contents to variable fileContents
        String line;
        while((line = reader.readLine()) != null){
            line = line.replace(" -> ",",");
            fileContents.add(line);
            //System.out.println(line);
        }

        //close reader & file objects
        reader.close();
        file.close();
    }

    public void mapCoordinates(String coordinates){
        //Assign coordinates from current row on file to the below variables
        this.startX = Integer.parseInt(coordinates.split(",")[0]);
        this.startY = Integer.parseInt(coordinates.split(",")[1]);
        this.endX = Integer.parseInt(coordinates.split(",")[2]);
        this.endY = Integer.parseInt(coordinates.split(",")[3]);
        /*
        System.out.println(coordinates);
        System.out.println(this.startX);
        System.out.println(this.endX);
        */
        //Four different workstreams, two to calculate path between X coordinates if increasing or decreasing, and
        //two to calculate Y coordinates the same way.
        //If statement checks that these are horizontal and vertical lines only, if so it adds it pathTracking[][][] variable.
        if(this.startX < this.endX && this.startY == this.endY){
            //System.out.println(this.startX + " is less than " + this.endX + " and " + this.startY + " is equal to " + this.endY);
            while(this.startX <= this.endX){
                if(this.pathTracking[startX][startY][1] == null){
                    this.pathTracking[startX][startY][1] = 1;
                    //System.out.println(this.startX + " will soon be equal to " + this.endX);
                    this.startX++;
                }
                else{
                    this.pathTracking[startX][startY][1]++;
                    //System.out.println(this.startX + " will soon be equal to " + this.endX);
                    this.startX++;
                }
                 
            }
        }
        else if(this.startX > this.endX && this.startY == this.endY){
            while(this.startX >= this.endX){
                if(this.pathTracking[startX][startY][1] == null){
                    this.pathTracking[startX][startY][1] = 1;
                    this.startX--;
                }
                else{
                    this.pathTracking[startX][startY][1]++;
                    this.startX--;
                }
                 
            }
        }
        else if(this.startY < this.endY && this.startX == this.endX){
            while(this.startY <= this.endY){
                if(this.pathTracking[startX][startY][1] == null){
                    this.pathTracking[startX][startY][1] = 1;
                    this.startY++;
                }
                else{
                    this.pathTracking[startX][startY][1]++;
                    this.startY++;
                }
                 
            }
        }
        else if(this.startY > this.endY && this.startX == this.endX){
            while(this.startY >= this.endY){
                if(this.pathTracking[startX][startY][1] == null){
                    this.pathTracking[startX][startY][1] = 1;
                    this.startY--;
                }
                else{
                    this.pathTracking[startX][startY][1]++;
                    this.startY--;
                }
                 
            }
        }
        else if(this.startY == this.endY || this.startX == this.endX){
            if(this.pathTracking[startX][startY][1] == null){
                this.pathTracking[startX][startY][1] = 1;
                System.out.println("who knew?");
                System.out.println(startX + endX + startY + startX);
            }
            else{
                this.pathTracking[startX][startY][1]++;
                System.out.println("who knew?");
                System.out.println(startX + endX + startY + startX);
            }
        }
        else{//System.out.println(coordinates);
        }
    }

    //final method called from Main method
    //reads through all 999 X and 999 Y coordinates in 3 dimensional variable pathTracking[][][] > 1
    public void calculateDanger(){
        int numOfDangerousSpots = 0;
        for(int x = 0; x < 999; x++){
            for(int y = 0; y < 999; y++){
                if(this.pathTracking[x][y][1] != null){
                    //System.out.println("Checking " + x + " and checking " + y);
                    if(this.pathTracking[x][y][1] > 1){
                        numOfDangerousSpots++;
                    }
                }
                else{}
            }
        }
        System.out.println(numOfDangerousSpots);
        //OUTCOME comes out as 7792, but it's incorrect.
    }


    public static void main(String[] args){
        try{
            AdventCode ventTracker = new AdventCode();

            //For each coordinate in puzzle inputs, calculate instances each coordinate is crossed with method: mapCoordinates
            for(int i = 0; i < ventTracker.fileContents.size(); i++){
                String coordinates = ventTracker.fileContents.get(i);
                ventTracker.mapCoordinates(coordinates);
            }

            //Once all coordinates are calculated and added to pathTracking[][][] variable, the calculateDanger method will
            //read through that variable and determine how many coordinates are crossed more than once.
            ventTracker.calculateDanger();
        }
        catch(FileNotFoundException err){
            System.out.println("I'm giving up!");
        }
        catch(IOException err){
            System.out.println("I'm giving up!");
        }

    }
}