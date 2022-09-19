import java.util.*;

class Solution {
    Map<String, ArrayList<String>> links = new HashMap<String, ArrayList<String>>();
    Map<String, Boolean> visited = new HashMap<String, Boolean>();    
    Map<String, Integer> seq = new HashMap<String, Integer>();
    
    private class Pair{
        String str;
        int num;
        
        Pair(String s, int n){
            str = s;
            num = n;
        }
    }
    
    private boolean areTheyLinked(String s1, String s2){
        int cnt = 0;
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i))
                cnt++;
            if(cnt > 1)
                return false;
        }

        if(cnt == 1)
            return true;
        return false;
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        links.put(begin, new ArrayList<String>());
        visited.put(begin, false);
        seq.put(begin, 0);
        
        for(String str: words){
            links.put(str, new ArrayList<String>());
            visited.put(str, false);
        }
        
        for(String str: words){
            if(areTheyLinked(begin, str)){
                links.get(begin).add(str);
                links.get(str).add(begin);
            }
        }   
        for(String str1: words){    
            for(String str2: words){
                if(areTheyLinked(str1, str2)){
                    links.get(str1).add(str2);
                    links.get(str2).add(str1);
                }
            }
        }
        
        Queue<String> qu = new LinkedList<String>();
        qu.add(begin);
        visited.put(begin, true);
        
        while(!qu.isEmpty()){
            String cur = qu.poll();
            ArrayList<String> friends = links.get(cur);
                  
            if(cur.equals(target)){
                answer = seq.get(cur);
                break;
            }
            
            for(String str: friends){
                if(!visited.get(str)){
                    qu.add(str);
                    visited.put(str, true);
                    seq.put(str, seq.get(cur) + 1);
                }
            }
        }
        return answer;
    }
}
