import java.util.Arrays;

class Solution {
    int solution(int[][] land) {
        //dp 배열 채우기
        int[][] dp = new int[land.length][land[0].length];
        System.arraycopy(land[0], 0, dp[0], 0, land[0].length);

        for(int i = 1; i < land.length; i++){
            Arrays.fill(dp[i], 0);
            for(int j = 0; j < land[0].length; j++){
                for(int k = 0; k < land[0].length; k++){
                    if(j == k) continue; //같은 열 x
                    if(dp[i][j] < dp[i - 1][k] + land[i][j])
                        dp[i][j] = dp[i - 1][k] + land[i][j];
                }
            }
        }

        //최댓값 구하기
        int answer = 0;
        for(int i = 0; i < dp[0].length; i++)
            if(answer < dp[dp.length - 1][i])
                answer = dp[dp.length - 1][i];

        return answer;
    }
}
