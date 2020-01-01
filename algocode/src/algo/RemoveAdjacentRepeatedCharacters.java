package algo;
import java.util.*;

public class RemoveAdjacentRepeatedCharacters {
    /**
     * "aabbbbazw" -> "abazw"
     * @param input
     * @return
     */
    String stringDedupI(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        char[] array = input.toCharArray();
        int slow = 1;
        for (int fast = 1; fast < array.length; ++fast) {
            // compare new char being processed to the char that should be kept
            if (array[slow - 1] != array[fast]) {
                array[slow++] = array[fast];
            }
        }
        return new String(array, 0, slow);
    }

    /**
     * "aabccdc" -> "bccdc" -> "bdc"
     * Similar questions: LC1047, LC1209
     * @param input
     * @return
     */
    String stringDedupIV(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        char[] array = input.toCharArray();
        int slow = 1;
        int fast = 1;
        while (fast < array.length) {
            if (slow == 0 || array[fast] != array[slow - 1]) {
                array[slow++] = array[fast++];
            }
            else {
                while (fast < array.length && array[fast] == array[slow - 1]) {
                    fast++;
                }
                slow--;
            }
        }
        return new String(array, 0, slow);
    }

    public static void main(String[] args) {
        String input = "aabbbbazw";
        RemoveAdjacentRepeatedCharacters sol = new RemoveAdjacentRepeatedCharacters();
        System.out.println(sol.stringDedupI(input));
        System.out.println(sol.stringDedupIV(input));
    }
}
