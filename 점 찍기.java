import java.util.*;

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        int a = 0;
        while(a <= d){
            long inSq = (long)d * (long)d - (long)a * (long)a;
            answer += ((long)Math.sqrt(inSq) / k + 1);
            a += k;
        }
        return answer;
    }
}
