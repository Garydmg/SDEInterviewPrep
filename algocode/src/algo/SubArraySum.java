package algo;

public class SubArraySum {
    public int[] largestSubarraySum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int localLeft = 0;
        int globalLeft = 0;
        int globalRight = 0;
        int largest = nums[0];
        int sum = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            sum = Math.max(nums[i] + sum, nums[i]);
            if (sum == nums[i]) {
                localLeft = i;
            }
            if (sum > largest) {
                largest = sum;
                globalLeft = localLeft;
                globalRight = i;
            }
        }
        return new int[]{largest, globalLeft, globalRight};
    }

    private void print(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{-4,-6,-2,-3};
        SubArraySum sol = new SubArraySum();
        int[] result = sol.largestSubarraySum(array);
        sol.print(result);
    }
}
