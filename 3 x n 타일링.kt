import java.util.Arrays;

class Solution {
    private static final int limit = 1000000007;
    private static final long[] dp = new long[5001];

    private long getCount(int num){
        if(num % 2 == 1)
            return 0;

        if(dp[num] == limit)
            dp[num] = ((getCount(num - 2) * 4) % limit  - getCount(num - 1) % limit + limit) % limit;
        return dp[num];
    }

    public int solution(int n) {
        Arrays.fill(dp, limit);
        dp[2] = 3;
        dp[4] = 11;

        return (int)getCount(n);
    }
}
