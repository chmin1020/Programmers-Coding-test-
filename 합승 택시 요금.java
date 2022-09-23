class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) { 
        int[][] dis = new int[n + 1][n + 1];
        int INF = n * 100000 + 1;
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i == j)
                    dis[i][j] = 0;
                else
                    dis[i][j] = INF;
            }
        }
        
        for(int[] info: fares){
            dis[info[0]][info[1]] = info[2];
            dis[info[1]][info[0]] = info[2];
        }
        
        for(int k = 1; k <= n ; k++)
            for(int j = 1; j <= n; j++)
                for(int i = 1; i <= n; i++)
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
        
        int answer = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++)
            answer = Math.min(answer, dis[s][i] + dis[i][a] + dis[i][b]);
        return answer;
    }
}
