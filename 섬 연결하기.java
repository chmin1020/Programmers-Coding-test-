import java.util.*;

class Solution {
    static class Candidate implements Comparable<Candidate>{
        public int number, weight;
        Candidate(int number, int weight){
            this.number = number;
            this.weight = weight;
        }
    
        public int compareTo(Candidate o){
            return this.weight > o.weight ? 1 : -1; 
        }
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        boolean[] isFixed = new boolean[100];
        Candidate cur = null;
        PriorityQueue<Candidate> heap = new PriorityQueue<Candidate>();
        
        Arrays.fill(isFixed, false);
        heap.offer(new Candidate(0, 0));
        
        while(!heap.isEmpty()){
            cur = heap.poll();        
            if(isFixed[cur.number])
                continue;
            isFixed[cur.number] = true;
            answer += cur.weight;
            
            for(int i = 0; i < costs.length; i++){
                if(costs[i][0] == cur.number && !isFixed[costs[i][1]])
                    heap.offer(new Candidate(costs[i][1], costs[i][2]));
                else if(costs[i][1] == cur.number && !isFixed[costs[i][0]])
                    heap.offer(new Candidate(costs[i][0], costs[i][2]));
            }
        }
        return answer;
    }
}
