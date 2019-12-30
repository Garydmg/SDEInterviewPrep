package algo;
import java.util.*;

public class EvenOddOperations {
    public static long getMaximumScore(List<Integer> integerArray) {
        int size = integerArray.size();
        if (size == 0) {
            return 0;
        }
        Map<String, Long> memo = new HashMap<>();
        long[] preSum = new long[size];
        preSum[0] = integerArray.get(0);
        for (int i = 1; i < size; ++i) {
            preSum[i] = integerArray.get(i) + preSum[i - 1];
        }

        return dfs(integerArray, memo, preSum,0, size - 1, true);
    }
    private static long dfs(List<Integer> array, Map<String, Long> memo, long[] preSum, int start, int end, boolean isOdd) {
        String key = Integer.toString(start) + Integer.toString(end) + Boolean.toString(isOdd);
        // memo lookup if value exists
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        // base case
        if (start == end) {
            long value = isOdd ? array.get(start) : -array.get(start);
            memo.put(key, value);
        }
        else {
            long rangeSum = 0;
            if (start == 0) {
                rangeSum = preSum[end];
            }
            else {
                rangeSum = preSum[end] - preSum[start - 1];
            }
            long value = 0;
            if (isOdd) {
                value = rangeSum + Math.max(dfs(array, memo, preSum, start + 1, end, !isOdd), dfs(array, memo, preSum, start, end - 1, !isOdd));
            }
            else {
                value = -rangeSum + Math.max(dfs(array, memo, preSum, start + 1, end, !isOdd), dfs(array, memo, preSum, start, end - 1, !isOdd));
            }
            memo.put(key, value);
        }
        return memo.get(key);
    }

    public static void main(String[] args) {
        EvenOddOperations sol = new EvenOddOperations();
        List<Integer> array = new ArrayList<>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(2);
        array.add(6);
        System.out.println(sol.getMaximumScore(array));
    }
}
