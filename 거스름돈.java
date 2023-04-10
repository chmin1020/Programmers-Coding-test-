import java.util.Arrays;

class Solution {
    public int solution(int n, int[] money) {
        long[] dp = new long[n + 1];
        Arrays.fill(dp, 0);

        //동전 별로 계산
        dp[0] = 1;
        for(int value: money){
            for(int price = value; price <= n; price++){
                dp[price] += dp[price - value]; //현재 값 - 동전 가치 가짓수를 그대로 가져올 수 있음
                dp[price] %= 1000000007;
            }
        }

        return (int)dp[n];
    }
}
