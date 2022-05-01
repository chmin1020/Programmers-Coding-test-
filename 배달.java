import java.util.*;

class Solution {
    final static int INF = 10000000;
    public class Pair implements Comparable<Pair>{
        int first, second;
        Pair(int first, int second){
            this.first = first;
            this.second = second;
        }
        
        public int compareTo(Solution.Pair o){
            if(this.second == o.second) return 0;
            return (this.second > o.second)? 1:-1;
        } 
    }
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[] distance = new int[N + 1];
        int[][] edges = new int[N + 1][N + 1];
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Pair> heap = new PriorityQueue<Pair>();
        
        for(int i = 0; i < N + 1; i++)
            Arrays.fill(edges[i], INF);
        for(int i = 0; i < road.length; i++){
            if(edges[road[i][0]][road[i][1]] > road[i][2]){
                edges[road[i][0]][road[i][1]] = road[i][2];
                edges[road[i][1]][road[i][0]] = road[i][2];
            }
        }
        
        Arrays.fill(distance, INF);
        heap.add(new Pair(1, 0));
        distance[1] = 0;
        
        Pair cur = null;
        while(!heap.isEmpty()){
            cur = heap.poll();
            
            if(visited[cur.first]) continue;
            visited[cur.first] = true;
            
            for(int i = 1; i <= N; i++){
                if(!visited[i] && distance[i] > edges[cur.first][i] + distance[cur.first]){
                    distance[i] = edges[cur.first][i] + distance[cur.first];
                    heap.add(new Pair(i, distance[i]));
                }
            }
        }
        for(int i = 1; i <= N; i++)
           if(distance[i] <= K) 
                answer++;
        
        return answer;
    }
}
