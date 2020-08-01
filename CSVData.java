import java.time.*;
import java.text.*;
import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;


public class CSVData{

public static int indexOfNth(String base, String toFind, int n) {
  final int lenToFind = toFind.length();
  int baseIndex = 0;
  String baseSuffix = base;
  for (int toFindCounter=0; toFindCounter<=n; toFindCounter++) {
    int currIndex= baseSuffix.indexOf(toFind);
    if (currIndex == -1) {
      return -1;
    }
    else {
      baseIndex += currIndex;
      baseSuffix = baseSuffix.substring(currIndex+lenToFind,baseSuffix.length());
    }
  }
  return baseIndex + n * lenToFind;
}


public static int parseInt(String s) {
  return Integer.parseInt(s);
}


public static String getLine(String filepath, int row){
  try{
    File file = new File(filepath);
    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);
    String line = "";
    int valTo = 0;
    while(valTo != row) {
      line = br.readLine();
      valTo++;
    }
    line = br.readLine();
    return line;
  } catch(Exception e) {
    e.printStackTrace();
  }
  return "";
}


public static int getLineCount(String path){
  try{
    File file = new File(path);
    int lines = 0;

    FileInputStream fis = new FileInputStream(file);
    byte[] buffer = new byte[8 * 1024]; // BUFFER_SIZE = 8 * 1024
    int read;

    while ((read = fis.read(buffer)) != -1) {
        for (int i = 0; i < read; i++) {
            if (buffer[i] == '\n') lines++;
        }
    }
    fis.close();
    return lines;
  }catch (Exception e){
    e.printStackTrace();
  }
  return 0;
}


public static int getLineNum (String fileName) {

  int lines = 0;
  try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      while (reader.readLine() != null) lines++;
  } catch (IOException e) {
      e.printStackTrace();
  }
  return lines; //returns int

}


//TODO
/*
Within this method you need to populate a String array with every single line
 of the file other than the header. You will then return this array. To do 
 this I recommend using the getLine and getNumofLines functions. 
*/
public static String[] allLine(String path){
  int nthLine= getLineNum(path); //nth =12 lines
  String[] myLines = new String[nthLine]; //init array of length nthLine
  System.out.println("This is nthLine: "+ nthLine);

  //push lines into myLines array. start at 1 to skip heading.
  for (int i = 1; i < nthLine -1; i++) {
    String currLine= getLine(path, i);
    System.out.println(currLine);
    myLines[i] = currLine;
    System.out.println();
  }
  return myLines;
  
}

//TODO
/*
Within the csv file there are many different columns that hold values that are numbers. 
I want you to pick one column that holds an integer and find the average of those values.
 It may be helpful to use the allLine(String path) method within this method. 
*/
public static double average(String path) {
  
  String [] arrayAllLines = allLine(path); //get  array of all lines
  System.out.println(arrayAllLines.length-1);
  //get value of age in every index in array eg ["name, gender, age, race, job" , "etc, etc"]
  //get 3rd comma from every array index to get age
  double avgAge=0;
  for(int i = 1; i < arrayAllLines.length-1; i++){
      if (arrayAllLines[i].contains(",")){ //if arrayAllLines index contains ','
        //print the value after second comma
        String str = arrayAllLines[i];
        System.out.println(str);
        String[] myStrings = str.split(",");
        double age = Double.parseDouble(myStrings[2]); //comma 1
        avgAge= avgAge + age/12;
        
      }else{
        System.out.println("could not find commas in index "+ i);
        double noAgeFound = 0;
        return noAgeFound;
      }

} 
System.out.println("Average age is "+ avgAge);
System.out.println();
return avgAge;
}


//TODO
//create your own Method
public static int customCount(String path){
  int nthLine= getLineNum(path); 
  String[] myLine = new String[nthLine]; 
  System.out.println("These minorities feel like they dont belong in CS: ");
  for (int i = 9; i < nthLine -1; i++) {
    String currLine= getLine(path, i);
    System.out.println(currLine);
    myLine[i] = currLine;
    
  }
  return  5; 
}


  public static void main(String[] args) {
    String path = "/Users/chris/Desktop/PROJECT4-2/CSCulture.CSV"; //Testing with CSCulture.CSV
    allLine(path); 
    average(path);
    customCount(path);
  }
  
}
