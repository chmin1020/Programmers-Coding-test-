import java.util.*;

class Solution {
    public int[] solution(String[] grid) {
        int hlen = grid.length, wlen = grid[0].length();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        int[] dirX = {-1, 1, 0, 0};
        int[] dirY = {0, 0, -1, 1};
        
        int[] left = {2, 3, 1, 0};
        int[] right = {3, 2, 0, 1};
        char[][] map = new char[hlen][wlen];
        boolean[][][] visited = new boolean[hlen][wlen][4];
        
        for(int i = 0; i < hlen; i++)
            map[i] = grid[i].toCharArray();
    
        int sx, sy, cx, cy, cur, ans = 1;
        for(int i = 0; i < hlen; i++){
            for(int j = 0; j < wlen; j++){
                sx = i;
                sy = j; 
                
                for(int k = 0; k < 4; k++){
                    if(visited[i][j][k]) continue;
                    ans = 1;
                    cur = k;
                    cx = i;
                    cy = j;
                    visited[cx][cy][cur] = true;
                    while(true){
                        cx += dirX[cur];
                        cy += dirY[cur];
                        
                        if(cx < 0) cx = hlen - 1;
                        if(cx >= hlen) cx = 0;
                        if(cy < 0) cy = wlen - 1;
                        if(cy >= wlen) cy = 0;
                        
                        if(map[cx][cy] == 'L')
                            cur = left[cur];
                        if(map[cx][cy] == 'R')
                            cur = right[cur];
                        
                        if(visited[cx][cy][cur]){
                            if(cx != sx || cy != sy || cur != k)
                                ans = -1;
                            break;
                        }
                        visited[cx][cy][cur] = true;
                        ans++;
                    }
                    if(ans != -1)
                        tmp.add(ans);   
                }            
            }
        }
        
        int[] answer = new int[tmp.size()];
        for(int i = 0; i < tmp.size(); i++)
            answer[i] = tmp.get(i);
        Arrays.sort(answer);
        return answer;
    }
}
