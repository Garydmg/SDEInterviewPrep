package algo;
import java.util.*;

public class LongestSubstring {
    public int longestSubstringWithoutRepeatingChars(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        // initialization
        // set contains distinct characters in [slow, fast] window
        int slow = 0;
        int fast = 0;
        int longest = 1;
        Set<Character> set = new HashSet<>();
        set.add(input.charAt(0));

        // since we look ahead, watch out for boundary
        while (fast < input.length() - 1) {
            // if new character cannot be found in hashset
            if (!set.contains(input.charAt(fast + 1))) {
                fast++;
                set.add(input.charAt(fast));
                // fast - slow + 1 because of [slow, fast]
                longest = Math.max(longest, fast - slow + 1);
            }
            else {
                set.remove(input.charAt(slow++));
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        LongestSubstring sol = new LongestSubstring();
        System.out.println(sol.longestSubstringWithoutRepeatingChars("bcdfbd"));
    }
}
