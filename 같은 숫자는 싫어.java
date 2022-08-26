import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int cur = arr.length - 1;
        Stack<Integer> st = new Stack<Integer>();

        st.push(arr[cur--]);
        for(int i = cur; i >= 0; i--){
            if(st.peek() != arr[i])
                st.push(arr[i]);
        }
        
        int[] answer = new int[st.size()];
        cur = 0;
        while(!st.isEmpty())
            answer[cur++] = st.pop();
    
        return answer;
    }
}
