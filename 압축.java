import java.util.*;

class Solution {
    ArrayList<String> dictionary = new ArrayList<String>();
    
    public int[] solution(String msg) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int len = msg.length();
        
        dictionary.add("@");
        for(int i = 0; i < 26; i++){
            String str = "";
            str = str + String.valueOf((char)('A' + i));
            dictionary.add(str);
        }
        
        int curIdx = 0;
        while(curIdx < len){
            int end = len;
            
            while(end > curIdx){
                String tryWord = msg.substring(curIdx, end);
                if(dictionary.contains(tryWord)){
                    result.add(dictionary.indexOf(tryWord));
                    if(end != len){
                        String newWord = tryWord + msg.substring(end, end + 1);
                        dictionary.add(newWord);
                    }
                    curIdx = end;
                    break;
                }
                end--;
            }
        }
        
        int[] answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++)
            answer[i] = result.get(i);
        return answer;
    }
}
