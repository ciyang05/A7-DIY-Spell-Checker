import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SpellChecker {

    static void reportNotFound(String word, List<String> suggestions){
       System.out.println("Not found: " + word);

       if (suggestions != null && suggestions.isEmpty()){
           StringBuilder sb = new StringBuilder( " Suggestions: ");
           System.out.println(" Suggestions: ");
           for (String s: suggestions){
               sb.append(s).append(" ");


           }
           System.out.println(sb.toString());
       }
   }


   static void reportCorrect(String word){
       System.out.println("" + word + "' is spelled correctly.");
   }


   static List getSuggestions(String word, WordValidation validator){
       final int MAX_SUGGESTIONS = 10;
       List<String> allSuggestions = validator.suggestions(word);


       if (allSuggestions == null || allSuggestions.isEmpty()){
           return new ArrayList<>();
       }


       List<String> limitedSuggestions = new ArrayList<>();
       for(int i = 0; i < allSuggestions.size() && i < MAX_SUGGESTIONS; i++){
           limitedSuggestions.add(allSuggestions.get(i));
       }
   }


   static String normalize(String raw){
       if (raw == null){
           return "";
       }
       String word = raw.toLowerCase();
       word = word.replaceAll("[^a-z]", "");
       word = word.trim();
       return word;
   }


   void handleStdinMode(Scanner in, WordValidation validator){
       Set<String> seenMisspellings = new LinkedHashSet<>();
       while(in.hasNext()){
           String rawToken = in.next();
           String word = normalize(rawToken); //cleans up the word (lowercase, remove punctuation)
          
           if(word.isEmpty()){
               continue; // skip empty tokens
           }
          
           if (validator.containsWord(word)){
               continue; // it's a correct word
           }


           if (seenMisspellings.contains(word)){
               continue; //tedalready repor
           } else {
               seenMisspellings.add(word); // mark it as seen
               List<String> suggestions = getSuggestions(word, validator);
               reportNotFound(word, suggestions);
           }
       }
      
   }


   private static void handleArgsMode(String[] args, WordValidation validator){
       for(String raw : args){
           String word = normalize(raw);


           if(word.isEmpty()){
               continue;
           }


           if(validator.containsWord(word)){
               reportCorrect(word);
           } else {
               List<String> suggestions = getSuggestions(word, validator);
               reportNotFound(word, suggestions);
           }
       }

   }
}