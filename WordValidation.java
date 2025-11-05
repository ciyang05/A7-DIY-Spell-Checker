import java.util.ArrayList;
import java.util.HashSet;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordValidation {

    // empty hashset to contain words in the words.txt file
    HashSet<String> dictionary = new HashSet<>();

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
    // create temp list to store parsed file's words
    ArrayList<String> tempFile = new ArrayList<>();
    while (file.hasNextLine()) {
        String line = file.nextLine();
        // splits the word/sentence up based on apparent white space
        // adds the word to array called fields 
        String[] fields = line.split("\\s+");
        // goes to specific index, stores the word, then prints it
        String word = fields[0];
        // adds word to parasedFile list
        tempFile.add(word);
        System.out.println("This is the word before getting rid of any puncutation: " + word);
    }
    file.close();

    // creates final list containing parsed words w/no punctuation and converts word to lowercase
    ArrayList<String> parsedFile = new ArrayList<>();
    // the pattern that will be compared to words in the file
    // ^ indicates start of string, [a-zA-Z0-9] indicates pne or more char that are letters or digits, $ indicates end of string
    String regex = "^[a-zA-Z0-9]+$";

    // compile the pattern 
    Pattern pattern = Pattern.compile(regex);

    // iterates tempFile, turns every word in the list to lowercase, match it to the pattern
    // if the word fits the pattern, add it to the final parsedFile list 
    for (String word : tempFile) {
        word.toLowerCase();
        Matcher matcher = pattern.matcher(word);

        if (matcher.matches()) {
            parsedFile.add(word);
        }
    }


    // loops through each string in the parsedFile list and adds it to hashset
    for (String element : parsedFile) {
        dictionary.add(element);
    }
    // for testing purposes
    System.out.println("This is the hashset after parsing the file: " + dictionary);
    }


    /**
     *  @param query the word to check
     *  @return true if the query word is in the dictionary.
     */
    public boolean containsWord(String query) {
        // checks if dictionary hashset contains the string
        // if so, return true
        if (dictionary.contains(query)) {
            return true;
        // if it doesn't, return false
        } else {
            return false;
        }

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