import java.util.Arrays;

class Solution {
    public int solution(int sticker[]) {
        int answer = 0, len = sticker.length;
        if(len == 1)
            answer = sticker[0];
        else if(len == 2)
            answer = Math.max(sticker[0], sticker[1]);
        else{
            int[] dp = new int[len];
            Arrays.fill(dp, 0);
            dp[0] = sticker[0];
            dp[1] = Math.max(sticker[0], sticker[1]);
            for(int i = 2; i < len - 1; i++)
                dp[i] = Math.max(dp[i - 2] + sticker[i], dp[i - 1]);
            answer = Math.max(answer, dp[len - 2]);

            Arrays.fill(dp, 0);
            dp[len - 1] = sticker[len - 1];
            dp[len - 2] = Math.max(sticker[len - 1], sticker[len - 2]);
            for(int i = len - 3; i > 0; i--)
               dp[i] = Math.max(dp[i + 2] + sticker[i], dp[i + 1]); 
            answer = Math.max(answer, dp[1]);            
        }
        
        return answer;
    }
}
