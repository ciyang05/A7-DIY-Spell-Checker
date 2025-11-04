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

    private static void testContainsWord(WordValidation validator) {
        
    }
    
}
