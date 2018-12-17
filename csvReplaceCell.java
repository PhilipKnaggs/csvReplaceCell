//
/**
 * replace cell in csv file
 * 
 * @author Philip Knaggs
 * @version 16.12.2018
 */
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList; 
 
public class csvReplaceCell {

public static void main(String[] args) {

    if(args.length < 5){
        //test 1 check for number of params
        System.out.println("Usage: java changeCSV fileName fileOutName lineNumber columnName newText");
        return;
    }
    String fileName = args[0];
    String fileOutName = args[1];
    int lineInFile = 0;
    String columnName = args[3];
    String newText = args[4];
    try {
        lineInFile = Integer.parseInt(args[2]);
    }
    catch (NumberFormatException e)    {
      System.out.println("Invalid number for line: " + e.getMessage());
    }
    //test 2 correct params
    /*
    System.out.println("0: " + fileName);
    System.out.println("1: " + fileOutName);
    System.out.println("2: " + lineInFile);
    System.out.println("3: " + columnName);
    System.out.println("4: " + newText); 
    */
 
    String line = new String();
    ArrayList<String> lines = new ArrayList<String>();
    String separator = ",";
    int lineNo = 0;
    int colNo = -1;
    int headerLength = 0;
    
    try{BufferedReader br = new BufferedReader(new FileReader(fileName));

        while ((line = br.readLine()) != null) {       
            if (lineNo == 0) {                 
                String[] header = line.split(separator);
                headerLength = header.length;
                for (int i=0;i<header.length;i++) {
                    System.out.println("colname: " + header[i].trim());
                    if (header[i].trim().equals(columnName)) {
                        colNo = i;
                        System.out.println("Header: " + columnName + " , colNo=" + i);
                    // test 5 correct column
                    //System.out.println("column: " + lineNo);   
                    break;
                    }
                }
            }
            //test 4 reading lines
            //System.out.println(line);
            if (lineNo == lineInFile && lineInFile>0) {
                String[] csv = line.split(separator);
                String newLine= new String();
                for (int i=0;i<headerLength;i++) {
                    if (i == colNo) {
                        colNo = i;
                        //test 8 got correct cell
                        //System.out.println("Found: " + csv[i] );
                        csv[i] = newText;
                    }
                    if (i>0) { newLine = newLine + ", ";
                    }
                    newLine = newLine + csv[i];
                }
                line = newLine;
            }
            lines.add(line);
            lineNo++;
        }
        br.close();        
    }
    // test 3 picking up file 
    catch (IOException e) {
	e.printStackTrace ();
    }
    //test 9 correctly written, including overwritten
    try{BufferedWriter bw = new BufferedWriter (new FileWriter (fileOutName));
         for(int i=0; i<lines.size(); i++){
             if(lines.get(i) != null){
                 if (i>0) {
                     bw.write("\n");
                    }
                 bw.write(lines.get(i));    
                }
          }
          bw.close();  
        }
    catch (IOException e) {
	e.printStackTrace ();
    }
    }
}


