package a5;

import java.util.ArrayList;

/**
 *  Operations that should be supported by a spelling dictionary
 */
public interface SpellingOperations {
  /**
   *  @param query the word to check
   *  @return true if the query word is in the dictionary.
   */
  public boolean containsWord(String query);
  
  /**
   *  @param query the word to check
   *  @return a list of all valid words that are one edit away from the query
   */
  public ArrayList<String> nearMisses(String query){
  ArrayList<String> suggestions = new ArrayList<>();
    // Inserting one Letter
    for (int i = 0; i<= query.lenght(); i++){
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

  //
  return suggestions;

  }

}
 

  
