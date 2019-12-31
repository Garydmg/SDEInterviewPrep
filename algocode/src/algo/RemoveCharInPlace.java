package algo;
import java.util.*;

public class RemoveCharInPlace {
    String removeCharI(String input, String target) {
        if (input == null || target == null) {
            return "";
        }

        // convert input string to character array to make it mutable
        char[] array = input.toCharArray();

        // make a set to deduplicate target
        Set<Character> set = toSet(target);

        // [0, slow) characters that are processed and should be kept
        // [0, fast) characters that are processed
        int slow = 0;
        for (int fast = 0; fast < array.length; ++fast) {
            // if set does not contain array[fast], copy to array[slow], move both
            if (!set.contains(array[fast])) {
                array[slow++] = array[fast];
            }
            // if set does contain array[fast], move fast
        }
        // offset: where it starts, count: number of chars
        return new String(array, 0, slow);
    }
    Set<Character> toSet(String target) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < target.length(); ++i) {
            set.add(target.charAt(i));
        }
        return set;
    }

    public static void main(String[] args) {
        RemoveCharInPlace sol = new RemoveCharInPlace();
        String input = "abcdae";
        String target ="ab";
        System.out.println(sol.removeCharI(input, target));
    }
}
