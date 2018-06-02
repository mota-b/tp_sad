package ToolBox;

import Schema.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
    /**
     * Attribute
     */

    /**
     * Constructor
     */

    /**
     * Methodes
     */
    // Get data from file
    public static ArrayList<Data> getDataSet(String fileLocation, String fileName){
        ArrayList<Data> data = new ArrayList<>();

        /**
         * Comunity Block modified by Mokhtar Benhatchi ==> origin link: https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
         */

        String row = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileLocation+fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read row && add to the data
            while((row = bufferedReader.readLine()) != null) {

                String[] tuple = row.split(" ");
                data.add( new Data(tuple));
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(IOException ex) {
            System.out.println("Error reading file '"
                    + fileName + "'");
            // Alternative Output
            // ex.printStackTrace();
        }

        return data;
    }
}
