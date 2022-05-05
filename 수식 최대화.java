import java.util.*;

class Solution {
    String[] seq = {"+-*", "+*-", "-+*", "-*+", "*+-", "*-+"};
    public long calculate(long n1, long n2, char op){
        if(op == '+')
            return n1 + n2;
        if(op == '-')
            return n1 - n2;
        return n1 * n2;
    }
    
    public long solution(String expression) {
        long answer = 0;
        int ptr;
        String[] tmp = expression.replaceAll("[+*-]", " ").split(" ");
        Long[] nums = new Long[tmp.length];
        for(int i = 0; i < tmp.length; i++)
            nums[i] = Long.parseLong(tmp[i]);
        
        tmp = expression.replaceAll("[^+*-]", "").split("");
        Character[] oprs = new Character[tmp.length];
        for(int i = 0; i < tmp.length; i++)
            oprs[i] = tmp[i].charAt(0);
        
        for(int i = 0; i < 6; i++){
            List<Long> nu = new ArrayList<Long>(Arrays.asList(nums));
            List<Character> op = new ArrayList<Character>(Arrays.asList(oprs));
                
            for(int j = 0; j < 3; j++){
                ptr = 0;
                while(ptr < op.size()){
                    if(op.get(ptr) == seq[i].charAt(j)){
                        nu.set(ptr + 1, calculate(nu.get(ptr),nu.get(ptr + 1), seq[i].charAt(j)));
                        nu.remove(ptr);
                        op.remove(ptr);   
                    }
                    else
                        ptr++;
                }
            }
            if(nu.get(0) < 0) nu.set(0, -nu.get(0));
            answer = Math.max(answer, nu.get(0));
        }
        return answer;
    }
}
