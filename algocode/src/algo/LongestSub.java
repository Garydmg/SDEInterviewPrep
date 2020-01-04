package algo;
import java.util.*;

public class LongestSub {
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

    /**
     * Given an array of integers that contain only 0s and 1s
     * and a positive integer k, flip at most k 0s to 1s
     * return the longest subarray length that contains only integer 1
     * after flipping.
     *
     * This question is equivalent to finding the largest subarray
     * that contains at most k 0s
     * @param nums
     * @param k
     * @return
     */
    public int logestConsecutiveOnes(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 0;
        int longest = 0;
        int count = 0; // count # of zeroes

        while (fast < nums.length) {
            // expand window if nums[fast] == 1 regardless
            if (nums[fast] == 1) {
                longest = Math.max(longest, fast - slow + 1);
                fast++;
            }
            // nums[fast] == 0 covered
            // if number of # in a window < k, move fast, add to count
            else if (count < k) {
                count++;
                longest = Math.max(longest, fast - slow + 1);
                fast++;
            }
            // shrink window when count == k
            else if (count == k) {
                if (nums[slow] == 0) {
                    count--;
                }
                slow++;
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        LongestSub sol = new LongestSub();
        System.out.println(sol.longestSubstringWithoutRepeatingChars("bcdfbd"));
    }
}
