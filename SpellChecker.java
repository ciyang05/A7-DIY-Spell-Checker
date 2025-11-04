import java.util.ArrayList;

public class SpellChecker implements SpellingOperations{
    /**
   *  @param query the word to check
   *  @return true if the query word is in the dictionary.
   */
  public boolean containsWord(String query);
  
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
       String newWord = sb.deleteCharAt(i).toString();
       if (containsWord(newWord)){
       suggestions.add(newWord);
       }
   }


   //substituting letters
   for (int i = 0; i< query.length(); i++){
     for (char c = 'a'; c <= 'z'; c++){
       StringBuilder sb = new StringBuilder(query);
       String letter = String.valueOf(c);
       String newWord = sb.replace(i, i+1, letter).toString();
       if (containsWord(newWord)){
       suggestions.add(newWord);
       }
     }
   }


   //transpositions
   for (int i = 0; i < query.length() - 1; i++){
       StringBuilder sb = new StringBuilder(query);
       char char1 = query.charAt(i);
       char char2 = query.charAt(i+1);
       sb.setCharAt(i, char2);
       sb.setCharAt(i+1, char1);
       String newWord = sb.toString();
       if (containsWord(newWord)){
         suggestions.add(newWord);
       }
    
   }


   return suggestions;
 }


    
}