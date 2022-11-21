import java.util.*;

class Solution {
    private static final char[][] types = {
        {'R', 'T'}, {'C','F'}, {'J','M'}, {'A','N'}
    };
    private Map<Character, Integer> table = new HashMap<Character, Integer>();
    public String solution(String[] survey, int[] choices) {
        
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 2; j++)
                table.put(types[i][j], 0);
        
        for(int i = 0; i < choices.length; i++){
            if(choices[i] <= 4){
                int ori = table.get(survey[i].charAt(0));
                table.put(survey[i].charAt(0), ori + (4 - choices[i]));
            }
            else{
                int ori = table.get(survey[i].charAt(1));
                table.put(survey[i].charAt(1), ori + (choices[i] - 4));
            }
        }
        
        String answer = "";
        for(int i = 0; i < 4; i++){
            char cur = types[i][0];
            if(table.get(types[i][0]) < table.get(types[i][1]))
                cur = types[i][1];
            answer += cur;
        }
        
        return answer;
    }
}
