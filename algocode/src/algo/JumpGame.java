package algo;

public class JumpGame {
    public int minJump(int[] array) {
        int n = array.length;
        // dp[i]: minimum number of jumps from i to the one after the end of array
        /*
              1 3 2 0  2
          dp  3 2 2 -1 1 0
        */
        int[] dp = new int[n + 1];
        // initialization
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            int numSteps = array[i];
            int minJump = Integer.MAX_VALUE;
            for (int j = 1; j <= numSteps && i + j <= n; j++) {
                if (dp[i + j] != -1) {
                    minJump = Math.min(minJump, dp[i + j]);
                }
            }
            dp[i] = (minJump != Integer.MAX_VALUE) ? 1 + minJump : -1;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        JumpGame sol = new JumpGame();
        int[] array = {1,3,2,0,2};
        System.out.println(sol.minJump(array));
    }
}
