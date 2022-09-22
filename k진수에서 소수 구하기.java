import java.util.Stack;

class Solution {
    char[] numbers = {'0','1','2','3','4','5','6','7','8','9'};
    
    private boolean isPrime(long num){
        if(num == 1)
            return false;
        
        long lim = (long)Math.sqrt(num);

        for(long i = 2; i <= lim; i++)
            if(num % i == 0)
                return false;
        return true;
    }
    
    private String convertNum(int n, int k){
        Stack<Character> st = new Stack<Character>(); 
        while(n > 0){
            st.push(numbers[n % k]);
            n /= k;
        }
        
        String res = "";
        while(!st.isEmpty())
            res = res + st.pop();
        
        return res;
    }
    
    public int solution(int n, int k) {
        int answer = 0;
        
        String cvNum = convertNum(n, k);
        String[] eachNums = cvNum.split("0");        
        
        for(String num: eachNums){
            if(num.equals("")) continue;
            
            long cur = Long.parseLong(num);
            if(isPrime(cur))
                answer++;
        }
        
        return answer;
    }
}
