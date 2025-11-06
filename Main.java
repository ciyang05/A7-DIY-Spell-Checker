import java.util.ArrayList;

//Test class for WordValidation
public class Main {
    public static void main(String[] args) {
        WordValidation validator = new WordValidation("words.txt");

        System.out.println("Testing containsWord...");
        testContainsWord(validator);

        System.out.println("\nTesting nearMisses...");
        testNearMisses_Deletion(validator);
        testNearMisses_Insertion(validator);
        testNearMisses_Substitution(validator);
        testNearMisses_Transposition(validator);
        testNearMisses_Split(validator);

        System.out.println("\nAll tests completed.");
        
    }

    //Tests for valid and invalid inputs for containsWord
    private static void testContainsWord(WordValidation validator) {
        String[] validWords = {"book", "find", "kids", "cat", "tell"};
        String[] invalidWords = {"ador", "boook", "", "xyz"};

        //valid words
        for (String word : validWords) {
            System.out.println("Contains '" + word + "'? " + validator.containsWord(word));
        }

        //Invalid words
        for (String word : invalidWords) {
            System.out.println("Contains '" + word + "'? " + validator.containsWord(word));
        }

    }

    //Test for Deletion: Delete one letter from the word
    private static void testNearMisses_Deletion(WordValidation validator) {
        ArrayList<String> suggestions = validator.nearMisses("booook");
        System.out.println("Near misses for 'booook' (deletion): " + suggestions);

    }

    //Test for Insertion: Insert one letter into the word at any point
    private static void testNearMisses_Insertion(WordValidation validator) {
        ArrayList<String> suggestions = validator.nearMisses("fnd");
        System.out.println("Near misses for 'fnd' (insertion): " + suggestions);
    }
    
    //Test for Substitution: Replace one char with another
    private static void testNearMisses_Substitution(WordValidation validator) {
        ArrayList<String> suggestions = validator.nearMisses("fimd");
        System.out.println("Near misses for 'fimd' (substitution): " + suggestions);
    }

    //Test for Transposition: Swap two adjacent characters
    private static void testNearMisses_Transposition(WordValidation validator) {
        ArrayList<String> suggestions = validator.nearMisses("adroe");
        System.out.println("Near misses for 'adroe' (transposition): " + suggestions);
    }

    //Test for Split: Divide the word into two legal words
    private static void testNearMisses_Split(WordValidation validator) {
        ArrayList<String> suggestions = validator.nearMisses("cattell");
        System.out.println("Near misses for 'cattell' (split): " + suggestions);
    }
    
}
