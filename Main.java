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
        assert validator.containsWord("hello");
        assert validator.containsWord("Hello");
        assert validator.containsWord("HELLO");
        assert validator.containsWord("cat");
        assert validator.containsWord("lattice");

    }
    
}
