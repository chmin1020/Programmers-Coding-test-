import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(Collections.reverseOrder());
        
        for(int i = 0; i < works.length; i++)
            heap.add(works[i]);
        
        while(n > 0 && !heap.isEmpty()){
            int cur = heap.poll() - 1;
            
            if(cur > 0)
                heap.add(cur);
            n--;
        }
        
        while(!heap.isEmpty()){
            int cur = heap.poll();
            answer += (cur * cur);
        }
        
        return answer;
    }
}
