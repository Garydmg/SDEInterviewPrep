package algo;
import java.util.*;

public class StringReplace {
    public String replace(String input, String source, String target) {
        char[] array = input.toCharArray();
        if (source.length() >= target.length()) {
            return replaceSmaller(array, source, target);
        }
        return replaceLarger(array, source, target);
    }
    private String replaceSmaller(char[] input, String source, String target) {
        int slow = 0;
        int fast = 0;
        while (fast < input.length) {
            if (fast <= input.length - source.length() && isSubstring(fast, input, source)) {
                copyTarget(slow, input, target);
                slow += target.length();
                fast += source.length();
            }
            else {
                input[slow++] = input[fast++];
            }
        }
        return new String(input, 0, slow);
    }
    private String replaceLarger(char[] input, String source, String target) {
        List<Integer> matchIdxEnd = findMatchIdxEnd(input, source);
        int numMatches = matchIdxEnd.size();
        char[] result = new char[input.length + numMatches * (target.length() - source.length())];
        int slow = result.length - 1;
        int fast = input.length - 1;
        int i = matchIdxEnd.size() - 1;

        while (fast >= 0) {
            // if we find a match (by end idx)
            if (i >= 0 && matchIdxEnd.get(i) == fast) {
                copyTarget(slow - target.length() + 1, result, target);
                i--;
                slow -= target.length();
                fast -= source.length();
            }
            else {
                result[slow--] = input[fast--];
            }
        }
        int length = result.length - slow - 1;
        return new String(result, slow + 1, length);
    }
    private List<Integer> findMatchIdxEnd(char[] input, String source) {
        List<Integer> matchIdxEnd = new ArrayList<>();
        for (int i = 0; i <= input.length - source.length(); i++) {
            if (isSubstring(i, input, source)) {
                matchIdxEnd.add(i + source.length() - 1);
            }
        }
        return matchIdxEnd;
    }
    private void copyTarget(int idx, char[] input, String target) {
        for (int i = 0; i < target.length(); ++i) {
            input[idx++] = target.charAt(i);
        }
    }
    private boolean isSubstring(int idx, char[] input, String source) {
        for (int i = 0; i < source.length(); ++i) {
            if (input[idx + i] != source.charAt(i)) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        StringReplace sol = new StringReplace();
        System.out.println(sol.replace("abcdabcddd", "abc", "xyz"));
    }
}
