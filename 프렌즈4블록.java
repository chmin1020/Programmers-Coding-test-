import java.util.*;

class Pos{
    int x, y;
    Pos(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0, turn = 0;
        ArrayList<Pos> store = new ArrayList<Pos>();
        Queue<Pos> blank = new LinkedList<Pos>();
        
        char[][] pan = new char[m][n];
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                pan[i][j] = board[i].charAt(j);
        
        while(true){
            turn = 0;
            store.clear();
            
            //find
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(i + 1  < m && j + 1 < n){
                        if(pan[i][j] == ' ') continue;
                        if(pan[i][j] == pan[i + 1][j] &&
                          pan[i][j] == pan[i][j + 1] &&
                          pan[i][j] == pan[i + 1][j + 1]){
                            store.add(new Pos(i, j));
                            turn++;
                        }
                    }
                }
            }
            
            if(turn == 0) break;
            
            //delete
            for(int i = 0; i < store.size(); i++){
                Pos cur = store.get(i);
                for(int a = cur.x; a < cur.x + 2; a++){
                    for(int b = cur.y; b < cur.y + 2; b++){
                        if(pan[a][b] != ' ')
                            answer++;
                        pan[a][b] = ' ';
                    }
                }
            }
            
            //fill
            for(int i = 0; i < n; i++){
                blank.clear();
                for(int j = m - 1; j >= 0; j--){
                    if(pan[j][i] == ' '){
                        blank.add(new Pos(j, i));   
                    }
                    else{
                        if(!blank.isEmpty()){
                            char tp = pan[j][i];
                            blank.add(new Pos(j, i));
                            Pos cur = blank.poll();
                            pan[cur.x][cur.y] = tp;
                            pan[j][i] = ' ';
                        }
                    }
                }
            }
        }
        
        return answer;
    }
}
