import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        Queue<Integer> qu1 = new LinkedList<Integer>();
        Queue<Integer> qu2 = new LinkedList<Integer>();
        long qu1_sum = 0, qu2_sum = 0;
        
        for(int i = 0; i < queue1.length; i++){
            qu1.add(queue1[i]);
            qu1_sum += queue1[i];
            qu2.add(queue2[i]);
            qu2_sum += queue2[i];            
        }
        
        if(qu1_sum + qu2_sum % 2 == 1)
            answer = -1;
        else{
            while(qu1_sum != qu2_sum){
                
                if(qu1.isEmpty() || qu2.isEmpty() || answer >= queue1.length * 3){
                    answer = -1;
                    break;
                }
                if(qu1_sum > qu2_sum){
                    int n = qu1.poll();
                    qu2.add(n);
                    
                    qu1_sum -= n;
                    qu2_sum += n;
                }
                else{
                    int n = qu2.poll();
                    qu1.add(n);
                    
                    qu1_sum += n;
                    qu2_sum -= n;
                }
                answer++;
            }
        }
        return answer;
    }
}
