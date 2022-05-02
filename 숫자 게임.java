import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        PriorityQueue<Integer> ready = new PriorityQueue<Integer>();
        
        Arrays.sort(A);
        for(int i = 0; i < B.length; i++)
            ready.add(B[i]);
        
        for(int i = 0; i < A.length; i++){
            while(!ready.isEmpty()){
                int cur = ready.poll();                
                if(cur > A[i]){
                    answer++;
                    break;
                }
            }   
            
            if(ready.isEmpty())
                break;
        }
    
        return answer;
    }
}
