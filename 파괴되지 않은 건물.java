import java.util.Arrays;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        int[][] culMap = new int[n + 1][m + 1]; 
        int sc = skill.length;
        int answer =  n * m;
        
        for(int i = 0; i < n + 1; i++)
            Arrays.fill(culMap[i], 0);
        
        for(int i = 0; i < sc; i++){
            int impact = skill[i][5];
            
            if(skill[i][0] == 1)
                impact = -impact;
            
            culMap[skill[i][1]][skill[i][2]] += impact;
            culMap[skill[i][3] + 1][skill[i][2]] += -impact;
            culMap[skill[i][1]][skill[i][4] + 1] += -impact;
            culMap[skill[i][3] + 1][skill[i][4] + 1] += impact; 
        }
        
        for(int i = 0; i < n; i++)
            for(int j = 1; j < m; j++)
                culMap[i][j] += culMap[i][j - 1];
        
        for(int i = 0; i < m; i++)
            for(int j = 1; j < n; j++)
                culMap[j][i] += culMap[j - 1][i];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                board[i][j] += culMap[i][j];
                if(board[i][j] <= 0)
                    answer--;
            }
        }        
        
        return answer;
    }
}
