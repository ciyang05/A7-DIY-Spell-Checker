import java.util.ArrayList;
import java.util.Set;
public class Main {

    public static void main(String[] args) {
        WordValidation validator = new WordValidation("words.txt");

        testContainsWord(validator);
        testNearMisses_Deletion(validator);
        testNearMisses_Insertion(validator);
        testNearMisses_Substitution(validator);
        testNearMisses_Transposition(validator);
        testNearMisses_Split(validator);
        
    }

    //Tests fr valid and invalis inputs for containsWord
    private static void testContainsWord(WordValidation validator) {

        //valid words
        assert validator.containsWord("book");
        assert validator.containsWord("find");
        assert validator.containsWord("kids");
        assert validator.containsWord("cat");
        assert validator.containsWord("tell");

        //Invalid words
        assert !validator.containsWord("ador");
        assert !validator.containsWord("boook");
        assert !validator.containsWord("");
        assert !validator.containsWord("xyz"); 

    }

    private static void testNearMisses_Deletion(WordValidation validator) {
        ArrayList<String> suggestions = validator.nearMisses("booook");
        assert suggestions.contains("book") : "Deletion test failed for 'booook'";
    }
    
}
