import java.util.*;

class Solution {
    static final int INF = 1000000000;
    
    private class Node implements Comparable<Node>{
        int num, dis;
        
        Node(int n, int d){
            num = n;
            dis = d;
        }
        
        public int compareTo(Node o){
            return (this.dis > o.dis)? 1: -1;
        }
    }
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        int max = -1;
        
        ArrayList<ArrayList<Integer>> graphInfo = new ArrayList<ArrayList<Integer>>();
        int[] distance = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        
        for(int i = 0; i <= n; i++){
            distance[i] = INF;
            graphInfo.add(new ArrayList<Integer>());
        }
        
        for(int i = 0; i < edge.length; i++){
            graphInfo.get(edge[i][0]).add(edge[i][1]);
            graphInfo.get(edge[i][1]).add(edge[i][0]);
        }
        
        PriorityQueue<Node> heap = new PriorityQueue<Node>();
        heap.add(new Node(1, 0));
        distance[1] = 0;
        
        while(!heap.isEmpty()){
            Node cur = heap.poll();
            
            if(visited[cur.num])
                continue;
            visited[cur.num] = true;
            
            max = Math.max(cur.dis, max);
            
            for(int nd: graphInfo.get(cur.num)){
                if(!visited[nd] && distance[nd] > distance[cur.num] + 1){
                    distance[nd] = distance[cur.num] + 1;
                    heap.add(new Node(nd, distance[nd]));
                }
            }
        }
        
        for(int i = 1; i <= n; i++)
            if(distance[i] == max)
                answer++;
        return answer;
    }
}
