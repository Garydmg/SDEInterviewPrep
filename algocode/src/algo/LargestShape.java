package algo;

public class LargestShape {
    /**
     * 0 1 0 0
     * 1 1 1 1
     * 0 1 0 0
     * 0 1 0 0
     * The largest cross has size 2 (from center)
     * @param matrix
     * @return
     */
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

    /**
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 1 0 1 0
     * 1 1 1 1 1
     * 1 1 1 0 0
     * The largest sub-square surrounded by 1 is 3
     * @param matrix
     * @return
     */
    public int largestSquareSurroundedByOne(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        // build memo
        int[][] rightLeft = buildRightLeft(matrix, rowNum, colNum);
        int[][] bottomUp = buildBottomTop(matrix, rowNum, colNum);
        int largestSize = Integer.MIN_VALUE;
        // loop through the matrix
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                // check top and left edges, with matrix[i][j] as vertex
                int longestSide = Math.min(rightLeft[i][j], bottomUp[i][j]);
                // check bottom and right edges
                for (int k = longestSide; k >= 1; k--) {
                    if (bottomUp[i][j + k - 1] >= k && rightLeft[i + k - 1][j] >= k) {
                        largestSize = Math.max(largestSize, k);
                    }
                }
            }
        }
        return (largestSize == Integer.MIN_VALUE) ? 0 : largestSize;
    }

    /**
     * Determine the largest square surrounded by a bunch of matches (each match is either horizontal or vertical), return the length of the largest square.
     * The input is a matrix of points. Each point has one of the following values:
     * 0 - there is no match to its right or bottom.
     * 1 - there is a match to its right.
     * 2 - there is a match to its bottom.
     * 3 - there is a match to its right, and a match to its bottom.
     * @param matrix
     * @return
     */
    public int largestSquareOfMatches(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int numRow = matrix.length;
        int numCol = matrix[0].length;
        int[][] right = new int[numRow + 1][numCol + 1];
        int[][] bottom = new int[numRow + 1][numCol + 1];
        int result = 0;

        for (int i = numRow - 1; i >= 0; i--) {
            for (int j = numCol - 1; j >= 0; j--) {
                // build matrices
                // 0b01
                if (hasRight(matrix[i][j])) {
                    right[i][j] = right[i][j + 1] + 1;
                }
                // 0b10
                if (hasBottom(matrix[i][j])) {
                    bottom[i][j] = bottom[i + 1][j] + 1;
                }
                // 0b11
                // calculate largest square
                if (hasBoth(matrix[i][j])) {
                    // start from the largest value
                    for (int maxLength = Math.min(right[i][j], bottom[i][j]); maxLength >= 1; maxLength--) {
                        // do not need to minus one because each coordinate represents a vertex
                        // e.g. length = 2, 3 vertices
                        if (bottom[i][j + maxLength] >= maxLength && right[i + maxLength][j] >= maxLength) {
                            result = Math.max(result, maxLength);
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }
    // "has right" is also the subcategory of "has-both"
    // both: 0b11
    // right: 0b01
    // bottom: 0b10
    private boolean hasRight(int value) {
        // only need to check the last bit
        return (value & 0b1) != 0;
    }
    private boolean hasBottom(int value) {
        // second least significant bit is the same between bottom and both
        return (value & 0b10) != 0;
    }
    private boolean hasBoth(int value) {
        return value == 0b11;
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
        int[][] input = {{3,3,2},{3,3,2},{1,1,0}};
        System.out.println(sol.largestSquareOfMatches(input));
    }

}
