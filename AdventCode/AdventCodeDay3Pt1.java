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

    public void trackDiagnostic(Integer index){
        Integer currentNum = binaryPrescence.get(index);
        //System.out.println("Current number is " + currentNum + " at index " + index);
        this.binaryPrescence.set(index,(currentNum + 1));
        //System.out.println("Updated num is " + currentNum++);
    }

    public void buildGamma(){  
        for(int num:this.binaryPrescence){
            if(num > 500){
                gamma+=1;
            }
            else{
                gamma+=0;
            }
        }
    }

    public static void main(String[] args){
        try{
            AdventCode report = new AdventCode();
            //System.out.println(report.fileContents.size());
            for(int i = 0; i < report.fileContents.size(); i++){
                for(Integer n = 0; n < 12; n++){
                    String num = report.fileContents.get(i);
                    if(num.charAt(n) == '1'){
                        report.trackDiagnostic(n);
                    }
                }
            }
            report.buildGamma();
            System.out.println(report.gamma);
        }
        catch(FileNotFoundException err){
            System.out.println("I'm giving the up!");
        }
        catch(IOException err){
            System.out.println("I'm giving the up!");
        }

    }
}