import java.util.*;

class Pos{
    int x, y, d;
    Pos(int x, int y, int d){
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

class Solution {
    int[] dirX = {1, -1, 0, 0};
    int[] dirY = {0, 0, 1, -1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        Queue<Pos> qu = new LinkedList<Pos>();
        
        for(int i = 0; i < places.length; i++){
            boolean no = false;
            for(int j = 0; j < 5; j++){
                for(int k = 0; k < 5; k++){
                    if(places[i][j].charAt(k) == 'P'){
                        boolean[][] visited = new boolean[places[i].length][5];
                        Pos start = new Pos(j, k, 0);
                        
                        qu.clear();
                        qu.offer(new Pos(j, k, 0));
                        visited[j][k] = true;
                        while(!qu.isEmpty()){
                            Pos cur = qu.poll();
                           if((cur.x != start.x || cur.y != start.y) 
                               && places[i][cur.x].charAt(cur.y) == 'P' 
                               && cur.d <= 2){
                                no = true;
                                break;
                            }
                            
                            for(int n = 0; n < 4; n++){
                                int nx = cur.x + dirX[n];
                                int ny = cur.y + dirY[n];
                                
                                if(nx < 0 || ny < 0 || nx >= 5 || ny >= 5)
                                    continue;
                                if(visited[nx][ny])
                                    continue;
                                if(places[i][nx].charAt(ny) == 'X')
                                    continue;
                                
                                qu.offer(new Pos(nx, ny, cur.d + 1));
                                visited[nx][ny] = true;
                            }    
                        }
                    }
                    if(no) break;
                }
                if(no) break;
            }    
            if(no)
                answer[i] = 0;
            else
                answer[i] = 1;
        }
        return answer;
    }
}
