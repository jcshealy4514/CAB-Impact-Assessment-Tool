import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class AdventCode {
    
    List<String> fileContents = new ArrayList<String>();
    List<Integer> binaryPrescence = new ArrayList<Integer>(Collections.nCopies(12, 0));
    String gamma = new String();
    char search = '1';
    char opposeSearch = '0';

    public AdventCode() throws FileNotFoundException, IOException{
        try{
            fileContents = buildArray();
            //List<Integer> binaryPrescence;
        }
        catch(FileNotFoundException err){
            System.out.println("I'm giving the up!");
        }
        catch(IOException err){
            System.out.println("I'm giving the up!");
        }

    }

    public List<String> buildArray() throws FileNotFoundException, IOException {
        FileReader file = new FileReader("C:/Users/Colton/Downloads/day3pt1.txt");
        BufferedReader reader = new BufferedReader(file);
        String line = null;
        //Integer nextLine = null;
        List<String> diagnosticNums = new ArrayList<String>();
        while((line = reader.readLine()) != null){
            //nextLine = Integer.parseInt(line);
            //System.out.println(nextLine);
            diagnosticNums.add(line);
        }
        reader.close();
        file.close();
        return diagnosticNums;
    }

    public void trackInstances(Integer index){
        Integer currentNum = binaryPrescence.get(index);
        //System.out.println("Current number is " + currentNum + " at index " + index);
        this.binaryPrescence.set(index,(currentNum + 1));
        //System.out.println("Updated num is " + currentNum++);
    }

    public void calculateGamma(){
        this.gamma = new String();
        this.binaryPrescence = new ArrayList<Integer>(Collections.nCopies(12, 0));
        for(int i = 0; i < this.fileContents.size(); i++){
            for(Integer n = 0; n < 12; n++){
                String num = this.fileContents.get(i);
                if(num.charAt(n) == search){
                    this.trackInstances(n);
                }
            }
        }
        this.calcWinningNum();
    }

    public void calcWinningNum(){  
        for(int num:this.binaryPrescence){
            if(num > this.fileContents.size()/2){
                gamma+=search;
            }
            else if(num < this.fileContents.size()/2){
                gamma+=opposeSearch;
            }
            else{
                gamma+=search;
            }
        }
    }

    public void kickOffCalc(){
        for(Integer n = 0; n < 12; n++){
            this.calculateGamma();
            //System.out.println(this.gamma);
            //System.out.println(this.fileContents.size());
            char winningNum = this.gamma.charAt(n);
            for(int i = 0; i < this.fileContents.size(); i++){
                String row = this.fileContents.get(i);
                if((this.fileContents.size() != 1) & (row.charAt(n) == winningNum)){
                    //System.out.println("We matched! " + row.charAt(n) + " is not equal to " + winningNum);
                    this.fileContents.remove(i);
                    i--;
                }
            }
            
        }
    }

    public void setSearch(int num){
        //System.out.println(search);
        this.search = (char) (48 + num);
        if(search == '1'){
            this.opposeSearch = '0';
        }
        else{
            this.opposeSearch = '1';
        }
        //System.out.println(search);
    }

    public static void main(String[] args){
        try{
            AdventCode report = new AdventCode();
            //System.out.println(report.fileContents.size());
            //report.calculateGamma();
            report.setSearch(1);
            report.kickOffCalc();
            System.out.println(report.fileContents.get(0));
            System.out.println(report.fileContents.size());
            report.fileContents = report.buildArray();         
            System.out.println(report.fileContents.size());
            //report.setSearch(0);
            //report.kickOffCalc();
            System.out.println(report.fileContents.get(0));
            //System.out.println(report.gamma);
            //System.out.println(report.search);
            //System.out.println(report.fileContents.get(1));
        }
        catch(FileNotFoundException err){
            System.out.println("I'm giving the up!");
        }
        catch(IOException err){
            System.out.println("I'm giving the up!");
        }

    }
}

//If you switch line 90
//(row.charAt(n) == winningNum)){
//to
//(row.charAt(n) != winningNum)){
//you will get the two numbers needed to multiply to get the answer.
//I misunderstood the ask of the question and this is the easiest switch to most common to least common number
//May add a switch later!