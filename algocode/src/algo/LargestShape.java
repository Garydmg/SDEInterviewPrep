package algo;

public class LargestShape {
    public int largestCrossOfOnes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int numRow = matrix.length;
        int numCol = matrix[0].length;
        int[][] leftRight = buildLeftRight(matrix, numRow, numCol);
        int[][] rightLeft = buildRightLeft(matrix, numRow, numCol);
        int[][] topBottom = buildTopBottom(matrix, numRow, numCol);
        int[][] bottomTop = buildBottomTop(matrix, numRow, numCol);

        int maxSize = Integer.MIN_VALUE;

        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                int size = Math.min(leftRight[i][j], Math.min(rightLeft[i][j], Math.min(topBottom[i][j], bottomTop[i][j])));
                maxSize = Math.max(maxSize, size);
            }
        }
        return maxSize;
    }
    private int[][] buildLeftRight(int[][] matrix, int numRow, int numCol) {
        int[][] leftRight = new int[numRow][numCol];
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                if (j == 0) {
                    leftRight[i][j] = matrix[i][j];
                } else {
                    leftRight[i][j] = (matrix[i][j] == 0) ? 0 : leftRight[i][j - 1] + 1;
                }
            }
        }
        System.out.println("leftRight");
        print(leftRight, numRow, numCol);
        return leftRight;
    }
    private int[][] buildRightLeft(int[][] matrix, int numRow, int numCol) {
        int[][] rightLeft = new int[numRow][numCol];
        for (int i = 0; i < numRow; i++) {
            for (int j = numCol - 1; j >= 0; j--) {
                if (j == numCol - 1) {
                    rightLeft[i][j] = matrix[i][j];
                }
                else {
                    rightLeft[i][j] = (matrix[i][j] == 0) ? 0 : rightLeft[i][j + 1] + 1;
                }
            }
        }
        System.out.println("rightLeft");
        print(rightLeft, numRow, numCol);
        return rightLeft;
    }
    private int[][] buildTopBottom(int[][] matrix, int numRow, int numCol) {
        int[][] topBottom = new int[numRow][numCol];
        for (int j = 0; j < numCol; j++) {
            for (int i = 0; i < numRow; i++) {
                if (i == 0) {
                    topBottom[i][j] = matrix[i][j];
                }
                else {
                    topBottom[i][j] = (matrix[i][j] == 0) ? 0 : topBottom[i - 1][j] + 1;
                }
            }
        }
        System.out.println("topBottom");
        print(topBottom, numRow, numCol);
        return topBottom;
    }
    private int[][] buildBottomTop(int[][] matrix, int numRow, int numCol) {
        int[][] bottomTop = new int[numRow][numCol];
        for (int j = 0; j < numCol; j++) {
            for (int i = numRow - 1; i >= 0; i--) {
                if (i == numRow - 1) {
                    bottomTop[i][j] = matrix[i][j];
                }
                else {
                    bottomTop[i][j] = (matrix[i][j] == 0) ? 0 : bottomTop[i + 1][j] + 1;
                }
            }
        }
        System.out.println("bottomTop");
        print(bottomTop, numRow, numCol);
        return bottomTop;
    }

    private void print(int[][] nums, int numRow, int numCol) {
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        LargestShape sol = new LargestShape();
//        int[][] input = {{0,1,0,0},{1,1,1,1},{0,1,0,0},{0,1,0,0}};
        int[][] input = {{0,1,1,1,1},{1,0,1,1,1},{0,0,0,1,1}};
        System.out.println(sol.largestCrossOfOnes(input));
    }

}
