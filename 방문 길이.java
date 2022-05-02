import java.util.*;

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        int[][] map = new int[11][11];
        int x = 5, y = 5;
        HashMap<Character, Integer> table = new HashMap<Character, Integer>();
        table.put('U', 8); table.put('D', 4); table.put('L', 2); table.put('R', 1);
        
        HashMap<Character, Character> table2 = new HashMap<Character, Character>();
        table2.put('U', 'D'); table2.put('D', 'U'); table2.put('L', 'R'); table2.put('R', 'L');
         
        
        for(int i = 0; i < 11; i++)
            Arrays.fill(map[i], 0);
        
        for(int i = 0; i < dirs.length(); i++){
            char cur = dirs.charAt(i);
            char reverse = table2.get(cur);
            boolean fresh = false;
            
            if((table.get(cur) & map[x][y]) == 0){
                map[x][y] += table.get(cur);
                fresh = true;
            }
            
            if(cur == 'U' && x > 0)
                x--; 
            else if(cur == 'D' && x < 10)
                x++;
            else if(cur == 'L' && y > 0)
                y--;
            else if(cur == 'R' && y < 10)
                y++;
            else
                continue;
            
            if((table.get(reverse) & map[x][y]) == 0){
                map[x][y] += table.get(reverse);
                fresh = true;
            }
            
            if(fresh) 
                answer++;
        }
        
        return answer;
    }
}
