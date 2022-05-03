import java.util.Stack;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        int ptr = 0, tp, dis;
        int len = dartResult.length();
        char bns1, bns2, cur;
        Stack<Integer> scores = new Stack<Integer>();
        
        while(ptr < len){
            dis = 1;
            if(dartResult.charAt(ptr + 1) >= '0' && dartResult.charAt(ptr + 1) <= '9')
                dis = 2;

            tp = Integer.parseInt(dartResult.substring(ptr, ptr + dis));
            bns1 = dartResult.charAt(ptr + dis);
            bns2 = ' ';
            
            if(ptr + dis + 1 < len)
                bns2 = dartResult.charAt(ptr + dis + 1);
            
            if(bns1 == 'D')
                tp *= tp;
            else if(bns1 == 'T')
                tp *= (tp * tp);
            
            if(bns2 == '*'){
                if(!scores.isEmpty()){
                    int tp2 = scores.pop();
                    tp2 *= 2;
                    scores.push(tp2);
                }
                tp *= 2;
                ptr += (dis + 2);
            }
            else if(bns2 == '#'){
                tp *= (-1);
                ptr += (dis + 2);
            }
            else
                ptr += (dis + 1);
            
            scores.push(tp);   
        }
        
        while(!scores.isEmpty())
            answer += scores.pop();
        
        return answer;
    }
}
