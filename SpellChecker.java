import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

/**
 * Interacts with user
 * Checks words read in as arguments and checks all words in a file
 */
public class SpellChecker {

   /**
    * @param word the word to check
    * @param suggestions , list of spelling suggestions
    */
   static void reportNotFound(String word, Set<String> suggestions){
      System.out.println("Not found: " + word);


      if (suggestions != null && !suggestions.isEmpty()){
          StringBuilder sb = new StringBuilder( " Suggestions: ");
          System.out.println(" Suggestions: ");
          for (String s: suggestions){
              sb.append(s).append(" ");
          }
          System.out.println(sb.toString());
      }
  }


   /**
    * @param word the word to check
    */
    static void reportCorrect(String word) {
       System.out.println("" + word + "' is spelled correctly.");
   }

   /**
    * @param word the word to check
    * @param validator , WordValidation object
    * @return limitedSuggestions, suggested spellings
    */
   static Set<String> getSuggestions(String word, WordValidation wordValidation){
       final int MAX_SUGGESTIONS = 10;
       List<String> allSuggestions = wordValidation.nearMisses(word);

       if (allSuggestions == null || allSuggestions.isEmpty()){
           return new HashSet<>();
       }

       Set<String> limitedSuggestions = new HashSet<>();
       for(int i = 0; i < allSuggestions.size() && i < MAX_SUGGESTIONS; i++){
           limitedSuggestions.add(allSuggestions.get(i));
       }
       return limitedSuggestions;
   }


   /**
    * @param raw , raw input word
    * @return normalized word
    */
   static String normalize(String raw){
       if (raw == null){
           return "";
       }
       String s = raw.toLowerCase(Locale.ROOT);
       s = s.replaceAll("[^a-z]", "");
       return s;
   }


   /**
    * @param in , scanner object
    * @param validator , WordValidation object
    */
   public static void handleStdinMode(Scanner in, WordValidation validator){
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
               continue; //tedalready report
           } else {
               seenMisspellings.add(word); // mark it as seen
               Set<String> suggestions = getSuggestions(word, validator);
               reportNotFound(word, suggestions);
           }
       }
      
   }


   /**
    * @param args an argument
    * @param validator , WordValidation object
    */
   private static void handleArgsMode(String[] args, WordValidation validator){
       for(String raw : args){
           String word = normalize(raw);

           if(word.isEmpty()){
               continue;
           }

           if(validator.containsWord(word)){
               reportCorrect(word);
           } else {
               Set<String> suggestions = getSuggestions(word, validator);
               reportNotFound(word, suggestions);
           }
       }

   }

   /**
    * Main method to run the class
    * @param args an argument
    */
   public static void main(String[] args) {
       WordValidation validator = new WordValidation("words.txt");
  
       if (args.length > 0) {
           handleArgsMode(args, validator); 
       } else {
           Scanner in = new Scanner(System.in);
           handleStdinMode(in, validator);
           in.close();
       }
   }
}
