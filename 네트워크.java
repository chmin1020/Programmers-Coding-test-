import java.util.*;

class Solution {
    private boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                Queue<Integer> qu = new LinkedList<Integer>();
                qu.add(i);
                visited[i] = true;
                answer++;
                
                while(!qu.isEmpty()){
                    int cur = qu.poll();
                    
                    for(int j = 0; j < n; j++){
                        if(computers[cur][j] == 1 && !visited[j]){
                            qu.add(j);
                            visited[j] = true;
                        }
                    }
                }
            }    
        }
        return answer;
    }
}
