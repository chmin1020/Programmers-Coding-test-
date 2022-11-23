import java.lang.Math;
import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {        
        // 필요한 최대 알고력, 코딩력 확인
        int alp_max = alp, cop_max = cop;
        for(int[] problem: problems){
            alp_max = Math.max(alp_max, problem[0]);
            cop_max = Math.max(cop_max, problem[1]);
        }
        
        //dp 배열
        int[][] dp = new int[alp_max + 1][cop_max + 1];
        
        for(int[] row: dp)
            Arrays.fill(row, 1000000000);
        
        //dp 배열 채우기
        dp[alp][cop] = 0;
        for(int i = alp; i <= alp_max; i++){
            for(int j = cop; j <= cop_max; j++){
                //기본 공부
                if(i != alp_max)
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                if(j != cop_max)
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                
                //문제 풀이 체크
                for(int[] problem: problems){
                    //지금 이 문제 못 풀면 넘어가기
                    if(i < problem[0] || j < problem[1])
                        continue;
                    
                    int nextAlp = i + problem[2];
                    int nextCop = j + problem[3];
                    
                    //현재 이 문제를 풀어서 갈 수 있는 상태를 갱신
                    int goalA = Math.min(alp_max, nextAlp);
                    int goalC = Math.min(cop_max, nextCop);
                    
                    for(int m = i; m <= goalA; m++)
                        for(int n = j; n <= goalC; n++)
                            dp[m][n] = Math.min(dp[m][n], dp[i][j] + problem[4]);
                }
            }
        }
        
        return dp[alp_max][cop_max];
    }
}
