import java.util.Arrays;

class Solution {
    private static final long INF = 1500000000;
    private static final long LIMIT = 1000000007;
    long[] dp = new long[60001];
    public int solution(int n) {
        Arrays.fill(dp, INF);
        dp[0] = dp[1] = 1;
        return Long.valueOf(fibonacci(n)).intValue();
    }

    private long fibonacci(int n){
        if(dp[n] != INF) return dp[n];
        else dp[n] = (fibonacci(n - 1) + fibonacci(n - 2)) % LIMIT;
        return dp[n];
    }
}
