package algo;

public class SubSum {
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
    public int largestSubmatrixSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int numRow = matrix.length;
        int numCol = matrix[0].length;
        // compute prefix sum in terms of column
        int[][] prefixRowSum = computePrefix(matrix, numRow, numCol);
        // initialize result
        int result = prefixRowSum[0][0];
        for (int rowTop = 0; rowTop < numRow; rowTop++) {
            for (int rowBottom = rowTop; rowBottom < numRow; rowBottom++) {
                result = Math.max(result, maxSubarray(prefixRowSum, rowTop, rowBottom, numCol));
            }
        }
        return result;
    }

    private int[][] computePrefix(int[][] matrix, int numRow, int numCol) {
        int[][] prefixRowSum = new int[numRow][numCol];
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                if (i == 0) {
                    prefixRowSum[i][j] = matrix[i][j];
                }
                else {
                    prefixRowSum[i][j] = matrix[i][j] + prefixRowSum[i - 1][j];
                }
            }
        }
        return prefixRowSum;
    }

    private int maxSubarray(int[][] prefixRowSum, int rowTop, int rowBottom, int numCol) {
        int[] array = preprocess(prefixRowSum, rowTop, rowBottom, numCol);
        // compute max subarray sum
        int maxSum = array[0];
        int localSum = array[0];
        for (int i = 1; i < array.length; i++) {
            localSum = Math.max(array[i], localSum + array[i]);
            maxSum = Math.max(maxSum, localSum);
        }
        return maxSum;
    }

    private int[] preprocess(int[][] prefixRowSum, int rowTop, int rowBottom, int numCol) {
        int[] array = new int[numCol];
        // compute prefix sum for the row in a column
        for (int j = 0; j < numCol; j++) {
            if (rowTop == 0) {
                array[j] = prefixRowSum[rowBottom][j];
            }
            else {
                array[j] = prefixRowSum[rowBottom][j] - prefixRowSum[rowTop - 1][j];
            }
        }
        return array;
    }

    private void print(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{-4,-6,-2,-3};
        SubSum sol = new SubSum();
        int[] result = sol.largestSubarraySum(array);
        sol.print(result);
        int[][] matrix = {{1,-2,-1,4},{1,-1,1,1},{0,-1,-1,1},{0,0,1,1}};
        System.out.println(sol.largestSubmatrixSum(matrix));
    }
}
