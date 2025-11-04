import java.util.ArrayList;
import java.util.HashSet;
import java.io.*;
import java.util.Scanner;

public class WordValidation {

    /**
     * constructor that creates a hashset based off of word file
     * @param args
     * @return nothing
     */
    public WordValidation(String[] args) {
        String filename = (args.length > 0) ? args[0] : "words.txt";
        Scanner file = null;
    try {
        file = new Scanner(new File(filename));
    } catch (FileNotFoundException e) {
        System.out.println("Cannot locate file.");
        // exits the system, flagging that program ended bcs of an error/exception
        System.exit(-1);
    }
    // create list to store parsed file's words
    ArrayList<String> parsedFile = new ArrayList<>();
    while (file.hasNextLine()) {
        String line = file.nextLine();
        // splits the word/sentence up based on apparent white space
        // adds the word to array called fields 
        String[] fields = line.split("\\s+");
        // goes to specific index, stores the word, then prints it
        String word = fields[0];
        // adds word to parasedFile list
        parsedFile.add(word);
        System.out.println("This is the word: " + word);
    }
    file.close();

    // creates empty hashset
    HashSet<String> hs = new HashSet<>();
    // loops through each string in the parsedFile list and adds it to hashset
    for (String element : parsedFile) {
        hs.add(element);
    }
    // for testing purposes
    System.out.println("This is the hashset after parsing the file: " + hs);
    }





    /**
     *  @param query the word to check
     *  @return true if the query word is in the dictionary.
     */
    public boolean containsWord(String query) {

    }
  
    /**
     *  @param query the word to check
     *  @return a list of all valid words that are one edit away from the query
     */
    public ArrayList<String> nearMisses(String query) {
        ArrayList<String> suggestions = new ArrayList<>();
        // Inserting one Letter
        for (int i = 0; i<= query.length(); i++){
        for (char c ='a'; c <='z'; c++ ){
            String newWord = query.substring(0, i) + c + query.substring(i);
            if (containsWord(newWord)){
            suggestions.add(newWord);
            }

        }
        }
        //Removing letters
        for (int i = 0; i < query.length(); i++){
            StringBuilder sb = new StringBuilder(query);
            String newWord = sb.deleteCharAt(i);
            if (containsWord(newWord)){
            suggestions.add(newWord);
            }
        }

        return suggestions;
    }


}