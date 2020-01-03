package algo;
import java.util.*;

public class Anagrams {
    /**
     * Find all occurrences of anagrams of a given string shortStr in longStr
     * Requirement: time complexity must be O(longStr.length())
     * @param longStr
     * @param shortStr
     * @return
     */
    public List<Integer> allAnagrams(String longStr, String shortStr) {
        // initialize and edge cases
        List<Integer> result = new ArrayList<>();
        if (longStr.length() == 0 || shortStr.length() > longStr.length()) {
            return result;
        }
        // make a hash map to count frequency
        Map<Character, Integer> map = toMap(shortStr);

        // when matches == map.size(), all characters match
        int matches = 0;

        for (int i = 0; i < longStr.length(); ++i) {
            // handle new character (entering the window)
            char newChar = longStr.charAt(i);
            Integer count = map.get(newChar);
            if (count != null) {
                // if there is a match, minus count by 1
                map.put(newChar, count - 1);
                // if the original count = 1 -> 0, one more match
                if (count == 1) {
                    matches++;
                }
            }
            // handle char that needs to be thrown out of window
            if (i >= shortStr.length()) {
                char oldChar = longStr.charAt(i - shortStr.length());
                count = map.get(oldChar);
                if (count != null) {
                    map.put(oldChar, count + 1);
                    // 0 -> 1: one more unmatch
                    if (count == 0) {
                        matches--;
                    }
                }
            }
            if (matches == map.size()) {
                result.add(i - shortStr.length() + 1);
            }
        }
        return result;
    }

    private Map<Character, Integer> toMap(String input) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length(); ++i) {
            char letter = input.charAt(i);
            Integer frequency = map.get(letter);
            map.put(letter, frequency == null ? 1 : frequency + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        Anagrams sol = new Anagrams();
        System.out.println(sol.allAnagrams("abcabc", "ab"));
    }


}
