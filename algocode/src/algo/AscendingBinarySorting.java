package algo;

import java.util.*;

public class AscendingBinarySorting {
    public static List<Integer> rearrange(List<Integer> elements) {
        Collections.sort(elements, new MyComparator());
        elements = deduplication(elements);
        return elements;
    }
    private static List<Integer> deduplication(List<Integer> elements) {
        if (elements.size() <= 1) {
            return elements;
        }
        // slow means the elements that are processed and must be kept [0, slow)
        int slow = 1;
        // fast means the elements that are being processed
        int fast = 1;
        while (fast < elements.size()){
            if (elements.get(fast) != elements.get(fast - 1)) {
                elements.set(slow++, elements.get(fast++));
            }
            else {
                fast++;
            }
        }
        int numElementsKept = slow;
        while (elements.size() > numElementsKept) {
            // remove last element
            elements.remove(elements.size() - 1);
        }
        return elements;
    }
    static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer left, Integer right) {
            int num1Left = numberOfOnes(left);
            int num1Right = numberOfOnes(right);
            // if there is a tie, smaller number has higher priority
            if (num1Left == num1Right) {
                return (left < right) ? -1 : 1;
            }
            // no tie, fewer ones have higher priority
            return (num1Left < num1Right) ? -1 : 1;
        }
    }
    private static int numberOfOnes(Integer num) {
        int count = 0;
        // loop until the number becomes 0
        while (num != 0) {
            // if the least significant bit is 1, add to count
            count += (num & 1);
            // move to the right by 1 bit, adding 0s to the left
            num = num >>> 1;
        }
        return count;
    }

    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>();
        array.add(5);
        array.add(5);
        array.add(3);
        array.add(7);
        array.add(10);
        array.add(14);
        AscendingBinarySorting sol = new AscendingBinarySorting();
        System.out.println(sol.rearrange(array));
    }
}
