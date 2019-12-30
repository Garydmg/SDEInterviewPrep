package algo;
import java.util.*;

public class MinimumUniqueArraySum {
    public static int getMinimumUniqueSum(List<Integer> arr) {
        if (arr.size() == 1) {
            return arr.get(0);
        }
        Collections.sort(arr);
        int sum = arr.get(0);
        for (int i = 1; i < arr.size(); ++i) {
            if (arr.get(i) <= arr.get(i - 1)) {
                arr.set(i, arr.get(i - 1) + 1);
            }
            sum += arr.get(i);
        }

        return sum;
    }

    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>();
        array.add(3);
        array.add(2);
        array.add(1);
        array.add(2);
        array.add(7);
        MinimumUniqueArraySum sol = new MinimumUniqueArraySum();
        System.out.println(sol.getMinimumUniqueSum(array));
    }
}
