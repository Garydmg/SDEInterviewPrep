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
     * Use two pointers to simulate stack
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


    String stringDedupIVStack(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] array = input.toCharArray();

        Deque<Character> stack = new ArrayDeque<>();
        int i = 0;
        while (i < array.length) {
            if (!stack.isEmpty() && array[i] == stack.peekFirst()) {
                // move i until it is different from top of stack
                while (i < array.length && array[i] == stack.peekFirst()) {
                    i++;
                }
                stack.pollFirst();
            }
            else {
                stack.offerFirst(array[i++]);
            }
        }
        i = 0;
        while (!stack.isEmpty()) {
            array[i++] = stack.pollLast();
        }

        return new String(array, 0, i);
    }

    public static void main(String[] args) {
        String input = "aabbbbazw";
        RemoveAdjacentRepeatedCharacters sol = new RemoveAdjacentRepeatedCharacters();
        System.out.println(sol.stringDedupI(input));
        System.out.println(sol.stringDedupIV(input));
    }
}
