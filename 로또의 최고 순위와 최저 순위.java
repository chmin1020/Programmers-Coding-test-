import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int corrects = 0;
        int zeros = 0;
        
        Set<Integer> wins = new HashSet<Integer>();
        
        for(int i = 0; i < win_nums.length; i++)
            wins.add(win_nums[i]);
        
        for(int i = 0; i < lottos.length; i++){
            if(lottos[i] == 0)
                zeros++;
            else if(wins.contains(lottos[i]))
                    corrects++;
        }
        
        int[] answer = {7 - (corrects + zeros), 7 - corrects};
        
        for(int i = 0; i < 2; i++)
            if(answer[i] == 7) answer[i]--;
        
        return answer;
    }
}
