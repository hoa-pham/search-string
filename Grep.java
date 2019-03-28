import java.io.*;
import java.util.*; 

public class Grep {

    public static void main(String[] args) {

        String option = args[0];
        String str = "";
        String path;
        boolean isSensitive = false;
        if (option.equals("-i")) {
            isSensitive = true;
            str = args[1];
            path = args[2];
        } else {
            str = args[0];
            path = args[1];
        }

        File folder = new File(path);
        Grep listFiles = new Grep();
        if (!folder.exists()) {
            System.out.println("error");
        } else {
            listFiles.listAllFiles(folder,str, isSensitive);            
        }
     }
    public static void grep(File file, String searchString, Boolean caseSensitive) {
        try {
                Scanner in = new Scanner(new FileReader(file));
                int lineNumber = 0;
                while(in.hasNextLine()) {
                    lineNumber += 1;
                    String line = in.nextLine();
                    if(caseSensitive) {
                        String lowerCaseSearchString = searchString.toLowerCase();
                        String lowerCaseLine = line.toLowerCase();
                        if(lowerCaseLine.contains(lowerCaseSearchString)) {
                            System.out.println(file.getPath() + ":" + lineNumber + ":" + line);
                        }
                    }
                    else {
                        if(line.contains(searchString)) {
                            System.out.println(file.getPath() + ":" + lineNumber + ":" + line);
                        }   
                    }

                }
            }catch(IOException e) {
                e.printStackTrace();      
            }
    }
     // Uses listFiles method  
     public void listAllFiles(File folder, String searchString, boolean caseSensitive){
         File[] fileNames = folder.listFiles();
         for(File file : fileNames){
             // if directory call the same method again
             if(file.isDirectory()){
                 listAllFiles(file, searchString, caseSensitive);
             }else{
                grep(file, searchString, caseSensitive);      
             }
         }
     }
 }