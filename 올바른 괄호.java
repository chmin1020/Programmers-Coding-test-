import java.util.Stack;

class Solution {
    boolean solution(String s) {
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(')
                st.push('(');
            else{
                if(st.empty() || st.peek() != '(') { //올바르지 않음
                    return false;
                }
                else
                    st.pop();
            }
        }

        return st.isEmpty();
    }
}
