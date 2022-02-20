package dhl;

public class Question1 {
    /*
     * Complete the 'ways' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER total
     *  2. INTEGER k
     */
    public static int ways(int total, int k) {
        int[] dp = new int[total + 1];
        dp[0] = 1;
        for (int row = 1; row < k + 1; row++) {
            for (int col = 1; col < total + 1; col++) {
                if (col >= row)
                    dp[col] = dp[col] + dp[col - row];
            }
        }
        return (dp[total]);
    }

    public static void main(String[] args) {
        int result = ways(8, 2);
        System.out.println(result);
    }
}