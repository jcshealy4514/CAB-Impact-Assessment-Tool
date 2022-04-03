import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventCode {
    
    List<String> bingoTables = new ArrayList<String>();
    String[][][] bingoTable = new String[5][5][2];
    List<Integer> calledNumbers = new ArrayList<Integer>();
    List<Integer> callOrder = new ArrayList<Integer>();
    boolean gameOver = false;
    int boardsRead = 0;
    int boardsWon = 0;
    int boardNumber = 0;
    boolean[] boardTracking = new boolean[99];


    public AdventCode() throws FileNotFoundException, IOException{
        try{
            this.buildArrays();
            //List<Integer> binaryPrescence;
        }
        catch(FileNotFoundException err){
            System.out.println("I'm giving the up!");
        }
        catch(IOException err){
            System.out.println("I'm giving the up!");
        }

    }

    public void buildArrays() throws FileNotFoundException, IOException {
        FileReader file = new FileReader("C:/Users/Colton/Downloads/day4pt1pt1.txt");
        BufferedReader reader = new BufferedReader(file);

        //build callOrder for bingo
        String[] outputNums = reader.readLine().split(",");
        int length = outputNums.length;
        for(int i = 0; i < length; i++){
            this.callOrder.add(Integer.parseInt(outputNums[i]));
        }
        reader.close();
        file.close();

        //build actual bingo tables
        file = new FileReader("C:/Users/Colton/Downloads/day4pt1pt2.txt");
        reader = new BufferedReader(file);
        String line = null;
        while((line = reader.readLine()) != null){
            bingoTables.add(line);
            //if(!line.isEmpty()){
        }

        reader.close();
        file.close();
    }

    public void callNextNumber(int index){
        //System.out.println("I show up a lot at the start!");
        this.calledNumbers = new ArrayList<Integer>();
        for(int i = 0; i <= index; i++){
            this.calledNumbers.add(this.callOrder.get(i));
        }
        //System.out.println("Check!");
        if(this.calledNumbers.size() > 4){
            this.checkForWinner();
        }
    }

    public void checkForWinner(){
        int row = 0;
        this.boardNumber = 0;
        for(String line: this.bingoTables){
            //System.out.println("Check!");
            if(!this.gameOver && (boardsWon < 99)){
                int column = 0;
                if(!line.isEmpty()){
                    line = line.replaceFirst("^\\s*", "");
                    String[] nums = line.split("\\s+");
                    for(String num:nums){
                        //System.out.println(num);
                        //System.out.println(row + " " + column + " - " + num);
                        this.bingoTable[row][column][0] = num;
                        column++;
                    }
                }
                else{
                    row = -1;
                    //System.out.println("Check!");
                    this.findMatches();
                    this.boardNumber++;
                    if((!this.gameOver) && (boardsWon < 99)){
                        this.bingoTable = new String[5][5][2];
                    }
                    boardsRead++;
                    //System.out.println(row);
                }
                row++;
            }
        }
        boardNumber = 0;
        //System.out.println(boardsWon);
        boardsRead = 0;
    }

    public void findMatches(){
        for(Integer calledNum:this.calledNumbers){
            int row = 0;
            int column = 0;
            //System.out.println("Check!");
            while(row < 5){
                //System.out.println("Check!");
                while(column < 5){
                    String num = this.bingoTable[row][column][0];
                    num = num.replaceFirst("^\\s*", "");
                    Integer parsedInt = Integer.parseInt(num);
                    //System.out.println("Bingo Number is " + parsedInt);
                    //System.out.println("CompNum is " + calledNum);
                    if(parsedInt == calledNum){
                        //System.out.println("match! " + parsedInt + " equals " + calledNum);
                        this.bingoTable[row][column][1] = "true";
                    }
                    //System.out.println("Check!");
                    column++;
                }
                column = 0;
                row++;
            }
        }
        this.bingoTime();
    }

    public void bingoTime(){
        int row = 0;
        int column = 0;
        int winningCombo = 0;
        boolean boardWon = false;
        //System.out.println("break here?");
        //CHECK ROWS
        while((row < 5) && (!this.gameOver) && (!boardWon) && (!this.boardTracking[boardNumber])){
            while(column < 5){
                if(this.bingoTable[row][column][1] == "true"){
                    winningCombo++;
                }
                column++;
            }
            if(winningCombo == 5){
                //System.out.println("Winner winner, chicken dinner on a row");
                this.boardsWon++;
                boardWon = true;
                this.boardTracking[boardNumber] = true;
                System.out.println(boardsWon);
                System.out.println(this.boardTracking[boardNumber]);
                //this.gameOver = true;
            }
            column = 0;
            winningCombo = 0;
            row++;
        }
        //System.out.println("break here?");
        //CHECK COLUMNS
        while((column < 5) && (!this.gameOver) && (!boardWon) && (!this.boardTracking[boardNumber])){
            while(row < 5){
                if(this.bingoTable[row][column][1] == "true"){
                    winningCombo++;
                }
                row++;
            }
            if(winningCombo == 5){
                //System.out.println("Winner winner, chicken dinner on a column!");
                this.boardsWon++;
                boardWon = true;
                this.boardTracking[boardNumber] = true;
                System.out.println(boardsWon);
                System.out.println(this.boardTracking[boardNumber]);
                //this.gameOver = true;
            }
            winningCombo = 0;
            row = 0;
            column++;
        }
        //System.out.println("break here?");
        //CHECK DIAGONALS
        /*
        if(!this.gameOver){
            for(int i = 0; i < 5;i++){
                //System.out.println(i);
                if(this.bingoTable[i][i][1] == "true"){
                    winningCombo++;
                }
            }
            if(winningCombo == 5){
                System.out.println("Winner winner, chicken dinner on diag!");
                this.gameOver = true;
            }
        }

        if(!this.gameOver){
            int dColumn = 4;
            for(int dRow = 0; dRow < 5; dRow++){
                //System.out.println(dRow);
                //System.out.println(dColumn);
                if(this.bingoTable[dRow][dColumn][1] == "true"){
                    winningCombo++;
                }
                dColumn--;
            }
            if(winningCombo == 5){
                System.out.println("Winner winner, chicken dinner on reverse diag!");
                this.gameOver = true;
            }
        }
        */
        //System.out.println("No Break!");
    }


    public static void main(String[] args){
        try{
            AdventCode bingo = new AdventCode();
            for(int i = 0; i < bingo.callOrder.size(); i++){
                if(!bingo.gameOver && bingo.boardsWon < 99){
                    bingo.callNextNumber(i);
                }
            }
            //Check Last Called Number
            int lastNumIndex = bingo.calledNumbers.size();
            int lastNumberCalled = bingo.calledNumbers.get(lastNumIndex - 1);
            System.out.println(lastNumberCalled);
            //Add together all numbers in Bingo Table
            int totalNum = 0;
            //int i = 0;
            for(int row = 0; row < 5; row++){
                for(int column = 0; column < 5; column++){
                    //System.out.println(row + " " + column);
                    //i++;
                    //System.out.println(i);
                    if(bingo.bingoTable[row][column][1] != "true"){
                        //System.out.print(bingo.bingoTable[row][column][0]);
                        totalNum += Integer.parseInt(bingo.bingoTable[row][column][0]);
                    }
                    //System.out.println("");
                }
            }
            System.out.println(totalNum);
            //Multiply the two!
            System.out.println((lastNumberCalled * totalNum));
        }
        catch(FileNotFoundException err){
            System.out.println("I'm giving the up!");
        }
        catch(IOException err){
            System.out.println("I'm giving the up!");
        }

    }
}