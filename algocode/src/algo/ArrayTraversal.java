package algo;
import java.util.ArrayList;
import java.util.List;

public class ArrayTraversal {
    /**
     * Traverse array spirally using recursion
     * @param matrix
     * @return
     */
    public List<Integer> spiral(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> result = new ArrayList<>();
        helper(matrix, result, 0, 0, m, n);
        return result;
    }
    private void helper(int[][] matrix, List<Integer> result, int rowOffset, int colOffset, int rowSize, int colSize) {
        // base case
        if (rowSize <= 1 || colSize <= 1) {
            // have one row left
            if (rowSize == 1 && colSize >= 1) {
                for (int i = 0; i < colSize; ++i) {
                    result.add(matrix[rowOffset][colOffset + i]);
                }
            }
            // have one column left
            else if (rowSize >= 1 && colSize == 1) {
                for (int i = 0; i < rowSize; ++i) {
                    result.add(matrix[rowOffset + i][colOffset]);
                }
            }
            // have nothing left
            return;
        }
        // top
        for (int i = 0; i < colSize - 1; ++i) {
            result.add(matrix[rowOffset][colOffset + i]);
        }
        // right
        for (int i = 0; i < rowSize - 1; ++i) {
            result.add(matrix[rowOffset + i][colOffset + colSize - 1]);
        }
        // bottom
        for (int i = colSize - 1; i >= 1; --i) {
            result.add(matrix[rowOffset + rowSize - 1][colOffset + i]);
        }
        // left
        for (int i = rowSize - 1; i >= 1; --i) {
            result.add(matrix[rowOffset + i][colOffset]);
        }
        helper(matrix, result, rowOffset + 1, colOffset + 1, rowSize - 2, colSize - 2);
    }

    /**
     * TODO
     * Print array diagonally in the same direction
     * @param matrix
     */
    public void diagonal(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int numRow = matrix.length;
        int numCol = matrix[0].length;
        int numDiagonal = numRow + numCol - 1;

    }
    public static void main(String[] args) {
        ArrayTraversal sol = new ArrayTraversal();
        int[][] input = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        System.out.println(sol.spiral(input));
    }
}
