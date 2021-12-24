import java.util.*;

class Solution {
    public int solution(int[] money) {
        int dp1[] = new int[money.length + 1];
        int dp2[] = new int[money.length + 1];
            
        dp1[0] = 0; 
        dp1[1] = money[0];
        for(int i = 2; i < dp1.length - 1; i++)
            dp1[i] = Math.max(dp1[i - 2] + money[i - 1], dp1[i - 1]);
            
        dp2[0] = 0;
        dp2[1] = 0;
        for(int i = 2; i < dp2.length; i++)
            dp2[i] = Math.max(dp2[i - 2] + money[i - 1], dp2[i - 1]);
    
        dp1[money.length] = Math.max(dp1[money.length - 1], dp2[money.length]);
        return dp1[money.length];
    }
}
