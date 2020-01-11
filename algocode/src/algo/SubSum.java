package algo;

/**
 * "Sub sum problem" - contiguous 1D/2D array sum
 * Question 1: Largest subarray sum (find a contiguous portion of a 1D array with max sum)
 * Question 2: Largest submatrix sum (find a rectangle/square portion of a 2D array with max sum)
 */
public class SubSum {
    /**
     * Question 1:
     * Largest subarray sum (find a contiguous portion of a 1D array with max sum)
     * Output the left/right boundaries
     *
     * Time: O(n), Space: O(1)
     * @param nums
     * @return
     */
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
            // modify localLeft when we choose num[i] as the beginning of subarray
            if (sum == nums[i]) {
                localLeft = i;
                // note: localRight is always i
            }
            // modify global boundaries only when local sum exceeds global sum
            if (sum > largest) {
                largest = sum;
                globalLeft = localLeft;
                globalRight = i;
            }
        }
        return new int[]{largest, globalLeft, globalRight};
    }

    /**
     * Question 2:
     * Compute max submatrix sum
     * The sum matrix must be a square or rectangle
     *
     * Method 1: compute prefix sum beforehand, then look up
     * Time: O(n^3), Space: O(n^2)
     * @param matrix
     * @return
     */
    public int largestSubmatrixSumI(int[][] matrix) {
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
                int[] array = preprocess(prefixRowSum, rowTop, rowBottom, numCol);
                result = Math.max(result, maxSubarray(array));
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

    // a way to "look up" the col sum given rowTop and rowBottom
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

    /**
     * Question 2:
     * Method 2: compute prefix sum on the go
     * Time: O(n^3), Space: O(n)
     * @param matrix
     * @return
     */
    public int largestSubmatrixSumII(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int numRow = matrix.length;
        int numCol = matrix[0].length;

        // initialize result
        int result = Integer.MIN_VALUE;
        for (int rowTop = 0; rowTop < numRow; rowTop++) {
            int[] prefix = new int[numCol];
            for (int rowBottom = rowTop; rowBottom < numRow; rowBottom++) {
                // compute prefix sum on the go
                computePrefix(prefix, matrix[rowBottom]);
                // compute maxsubarray
                result = Math.max(result, maxSubarray(prefix));
            }
        }
        return result;
    }

    private void computePrefix(int[] prefix, int[] cur) {
        for (int j = 0; j < cur.length; j++) {
            prefix[j] += cur[j];
        }
    }

    private int maxSubarray(int[] array) {
        int maxSum = array[0];
        int localSum = array[0];
        for (int i = 1; i < array.length; i++) {
            localSum = Math.max(array[i], localSum + array[i]);
            maxSum = Math.max(maxSum, localSum);
        }
        return maxSum;
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
        System.out.println(sol.largestSubmatrixSumI(matrix));
        System.out.println(sol.largestSubmatrixSumII(matrix));
    }
}
