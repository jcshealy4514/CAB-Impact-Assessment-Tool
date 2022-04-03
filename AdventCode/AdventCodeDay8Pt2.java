import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdventCode {
    
    ArrayList<String> fileContents = new ArrayList<String>();
    String[][] numberStorage = new String[8][3];
    String mixedData;
    String encryptedData;
    Map<String,String> positions = new HashMap<String,String>();
    int totalNumber;

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
        FileReader file = new FileReader("C:/Users/Colton/Downloads/day8pt1.txt");
        BufferedReader reader = new BufferedReader(file);

        //reads file and adds the contents to variable fileContents
        String line = null;
        while((line = reader.readLine()) != null){
            //System.out.println(line);
            fileContents.add(line);
        }

        //close reader & file objects
        reader.close();
        file.close();
    }

    public void decrypt(String sensorData){
        positions = new HashMap<String,String>();
        numberStorage = new String[8][3];
        String signal = null;
        int n = 0;
        for(int i = 0; i < 10; i++){
            signal = sensorData.split(" ")[i];
            //System.out.println(signal);
            n = 0;
            //System.out.println(signal.length());
            while(numberStorage[signal.length()][n] != null){
                //System.out.println(numberStorage[signal.length()][n] + " holds position " + n);
                n++;
            }
            //if(signal.length() == 6){System.out.println("Adding " + signal + " to numberStorage[6][" + n + "]");}
            numberStorage[signal.length()][n] = signal;
        }
        /*
        Going in the right direction, need to recalculate what numbers to use to find unique values
        2 characters - 3 characters = top
        if 6 characters does not contain all 2 characters, then top right is missing character = TOP RIGHT
        bottom right - 2 vs 4 characters.
        4 chars - 5 chars + does not contain top = bottom
        2 characters + 6 characters = middle
        5 characters, if top right is present then bottom left is unknown number OR
        5 characters, if bottom right is present then top left is unknown number
        */
        positions.put("Top",this.findPosition("Top"));
        positions.put("Top right",this.findPosition("Top right"));
        positions.put("Bottom right",this.findPosition("Bottom right"));
        positions.put("Middle",this.findPosition("Middle"));
        positions.put("Top left",this.findPosition("Top left"));
        positions.put("Bottom",this.findPosition("Bottom"));
        positions.put("Bottom left",this.findPosition("Bottom left"));

        /*
        System.out.println("Top position is " + positions.get("Top"));
        System.out.println("Top right position is " + positions.get("Top right"));
        System.out.println("Bottom right position is " + positions.get("Bottom right"));
        System.out.println("Middle position is " + positions.get("Middle"));
        System.out.println("Top left position is " + positions.get("Top left"));
        System.out.println("Bottom position is " + positions.get("Bottom"));
        System.out.println("Bottom left position is " + positions.get("Bottom left"));
        */
    }

    public String findPosition(String pos){
        String result;
        switch(pos){

            case "Top":
                char Char3;
                for(int secD = 0; secD < 3; secD++){
                    for(int i = 0; i < 3; i++){
                        boolean match = false;
                        if(numberStorage[3][secD] != null){
                            Char3 = numberStorage[3][secD].charAt(i);
                            //System.out.println("Checking " + Char3);
                            for(int n = 0; n < 2; n++){
                                char Char2 = numberStorage[2][secD].charAt(n);
                                //System.out.println("Comparing " + Char3 + " against " + Char2);
                                if(Character.compare(Char3,Char2) == 0){
                                    //System.out.println("Match!");
                                    match = true;
                                }
                            }
                            if(!match){
                                result = String.valueOf(Char3);
                                return result;
                            }
                        }
                    }
                }
            break;

            case "Top right":
                char Char2;
                for(int secD = 0; secD < 3; secD++){
                    //System.out.println(secD);
                    for(int i = 0; i < 2; i++){
                        boolean match = false;
                        //System.out.println(numberStorage[2][0]);
                        if(numberStorage[2][0] != null){
                            //System.out.println("Checking for the " + secD + " timeth!");
                            Char2 = numberStorage[2][0].charAt(i);
                            //System.out.println("Checking " + Char3);
                            for(int n = 0; n < 6; n++){
                                char Char6 = numberStorage[6][secD].charAt(n);
                                //System.out.println("Comparing " + Char6 + " against " + Char2);
                                if(Character.compare(Char6,Char2) == 0){
                                    //System.out.println("Match!");
                                    match = true;
                                }
                            }
                            if(!match){
                                result = String.valueOf(Char2);
                                return result;
                            }
                        }
                    }
                }
            break;
            case "Bottom right":
                for(int secD = 0; secD < 3; secD++){
                    for(int i = 0; i < 2; i++){
                        if(numberStorage[2][secD] != null){
                            Char2 = numberStorage[2][secD].charAt(i);
                            //System.out.println("Checking char at pos " + i + " which is " + Char2);
                            if(Character.compare(Char2,positions.get("Top right").charAt(0)) == 0){
                            }
                            else{
                                result = String.valueOf(Char2);
                                return result;
                            }
                        }
                    }
                }
            break;
            case "Middle":
                char Char4;
                char Char6;
                char tRChar = positions.get("Top right").toCharArray()[0];
                //System.out.println(tRChar);
                for(int secD = 0; secD < 3; secD++){
                    for(int i = 0; i < 4; i++){
                        boolean match = false;
                        if(numberStorage[4][0] != null){
                            Char4 = numberStorage[4][0].charAt(i);
                            for(int n = 0; n < 6; n++){
                                Char6 = numberStorage[6][secD].charAt(n);
                                if(Character.compare(Char4,tRChar) == 0 || Character.compare(Char4,Char6) == 0){
                                    match = true;
                                }
                            }
                            if(!match){
                                result = String.valueOf(Char4);
                                return result;
                            }
                        }
                    }
                }

            break;
            case "Top left":
                for(int i = 0; i < 4; i++){
                    Char4 = numberStorage[4][0].charAt(i);
                    /*for(String value1 : positions.values()){
                        System.out.println("this map contains value: " + value1);
                    }
                    System.out.println("The above may contain " + Char4);*/
                    if(!positions.containsValue(String.valueOf(Char4))){
                        result = String.valueOf(Char4);
                        return result;
                    }
                }
            break;
            case "Bottom left":
                char Char7;
                for(int i = 0; i < 7; i++){
                    Char7 = numberStorage[7][0].charAt(i);
                    if(!positions.containsValue(String.valueOf(Char7))){
                        result = String.valueOf(Char7);
                        return result;
                    }
                }

            break;
            case "Bottom":
            char Char5;
            char brChar = positions.get("Bottom right").charAt(0);
            boolean match;
            for(int secD = 0; secD < 3; secD++){
                match = false;
                for(int i = 0; i < 5; i++){
                    if(numberStorage[5][secD] != null){
                        Char5 = numberStorage[5][secD].charAt(i);
                        if(Character.compare(Char5, brChar) == 0){
                            match = true;
                        }
                    }
                }
                //System.out.println("Match currently equals " + match);
                if(match){
                    match = false;
                    for(int i = 0; i < 5; i++){
                        Char5 = numberStorage[5][secD].charAt(i);
                        if(!positions.containsValue(String.valueOf(Char5))){
                            result = String.valueOf(Char5);
                            return result;
                        }
                    }
                }
            }
            break;
        }
        return "null";
    }

    public void determineNums(String sensorData){
        String signal;
        String numString = new String();
        for(Map.Entry<String,String> entry:positions.entrySet()){
            System.out.println("Position " + entry.getKey() + " belongs to char " + entry.getValue());
        }
        for(int i = 11; i< 15; i++){
            signal = sensorData.split(" ")[i];
            System.out.println(signal);
            int sL = signal.length();
            if(sL == 4 || sL == 2 || sL == 7 || sL == 3){
                switch(sL){
                    case 2:
                    numString += "1";
                    break;
                    case 3:
                    numString += "7";
                    break;
                    case 4:
                    numString += "4";
                    break;
                    case 7:
                    numString += "8";
                    break;
                }
            }
            else{
                if(sL == 5){
                    if(!signal.contains(positions.get("Bottom right"))){
                        numString += "2";
                    }
                    else if(!signal.contains(positions.get("Top left"))){
                        numString += "3";
                    }
                    else{
                        numString += "5";
                    }
                }
                else if(sL == 6){
                    if(!signal.contains(positions.get("Middle"))){
                        numString += "0";
                    }
                    else if(!signal.contains(positions.get("Top right"))){
                        numString += "6";
                    }
                    else{
                        numString += "9";
                    }
                }
            }
        }
        System.out.println(numString + " == " + Integer.parseInt(numString));
        this.totalNumber += Integer.parseInt(numString);
    }

    public static void main(String[] args){
        try{
            AdventCode sensorCodes = new AdventCode();
            int fileSize = sensorCodes.fileContents.size();
            for(int i = 0; i < fileSize; i++){
                String sensorData = sensorCodes.fileContents.get(i);
                //System.out.println(sensorData);
                sensorCodes.decrypt(sensorData);
                //System.out.println(sensorData);
                sensorCodes.determineNums(sensorData);
            }
            System.out.println(sensorCodes.totalNumber);
        }
        catch(FileNotFoundException err){
            System.out.println("I'm giving up!");
        }
        catch(IOException err){
            System.out.println("I'm giving up!");
        }

    }
}