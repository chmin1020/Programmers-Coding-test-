import java.util.*;

class Solution {
    private class Pair implements Comparable<Pair>{
        public int start, end;
        Pair(int start, int end){
            this.start = start;
            this.end = end;
        }
        public int compareTo(Pair o){
            return this.end > o.end ? 1 : -1;
        }
    }
    public int solution(int[][] routes) {
        int answer = 0;
        PriorityQueue<Pair> qu = new PriorityQueue<Pair>();
        Pair cur;
        
        for(int i = 0; i < routes.length; i++)
            qu.add(new Pair(routes[i][0], routes[i][1]));
        
        while(!qu.isEmpty()){
            cur = qu.poll();
            answer++;
            while(!qu.isEmpty() && cur.end >= qu.peek().start)
                qu.remove();
        }
        return answer;
    }
}
