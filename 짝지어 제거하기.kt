import java.util.Stack;

class Solution
{
    public int solution(String s)
    {
        Stack<Character> st = new Stack<>();

        for(int i = 0; i < s.length(); i++){
            if(!st.isEmpty() && st.peek() == s.charAt(i))
                st.pop();
            else
                st.push(s.charAt(i));
        }

        if(st.isEmpty()) return 1;
        else return 0;
    }
}
