import java.util.*;

class Solution {
    private String findProgress(String str){
        if(str.length() == 0)
            return str;
        
        int leftCnt = 0, rightCnt = 0;
        
        int idx = 0;
        while(leftCnt != rightCnt || leftCnt == 0 && idx < str.length()){
            if(str.charAt(idx++) == '(')
                leftCnt++;
            else
                rightCnt++;
        }
        
        String u = str.substring(0, idx);
        String v = str.substring(idx, str.length());
        
        if(checkRight(u))
            return u + findProgress(v);
        else{
            String tmp = "(" + findProgress(v) + ")";
            StringBuilder plus = new StringBuilder();
            for(int i = 1; i < u.length() - 1; i++){
                if(u.charAt(i) == '(')
                    plus.append(")");
                else
                    plus.append("(");
            }
            tmp = tmp + plus.toString();
            
            return tmp;
        }
    }
    
    private boolean checkRight(String str){
        Stack<Character> st = new Stack<Character>();
        
        int idx = 0;
        while(idx < str.length()){
            if(str.charAt(idx) == '(')
                st.push(str.charAt(idx));
            else{
                if(st.isEmpty())
                    return false;
                st.pop();
            }
            idx++;
        }
        if(!st.isEmpty())
            return false;
        return true;
    }
    
    public String solution(String p) {
        return findProgress(p);
    }
}
