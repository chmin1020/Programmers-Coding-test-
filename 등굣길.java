import java.util.*;

class Solution {
    public static final int limit = 1000000007;
    public int solution(int m, int n, int[][] puddles) {
        long[][] map = new long[m + 1][n + 1];
        
        for(int i = 0; i <= m; i++)
            Arrays.fill(map[i], 0);
        
        for(int i = 0; i < puddles.length; i++)
            map[puddles[i][0]][puddles[i][1]] = -1;
        map[1][1] = 1;
        
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(map[i][j] == -1) continue;
                if(i - 1 > 0 && map[i - 1][j] != -1){
                    map[i][j] += map[i - 1][j];
                    map[i][j] %= limit;
                }
                if(j - 1 > 0 && map[i][j - 1] != -1){
                    map[i][j] += map[i][j - 1];
                    map[i][j] %= limit;
                }
                map[i][j] %= limit;
            }
        }
        return (int)map[m][n];
    }
}
