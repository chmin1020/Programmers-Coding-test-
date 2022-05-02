import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        int[] banned = new int[id_list.length];
        HashSet<String>[] mail = new HashSet[id_list.length];
        HashMap<String, Integer> number = new HashMap<String, Integer>();
        
        for(int i = 0; i < id_list.length; i++){
            answer[i] = banned[i] = 0;
            mail[i] = new HashSet<String>();
            number.put(id_list[i], i);
        }
        
        for(int i = 0; i < report.length; i++){
            String[] list = report[i].split(" ");
            if(!mail[number.get(list[1])].contains(list[0])){
                mail[number.get(list[1])].add(list[0]);
                banned[number.get(list[1])]++;
            }
        }
        
        for(int i = 0; i < banned.length; i++){
            if(banned[i] >= k){
                for(String name : mail[i])
                    answer[number.get(name)]++;
            }
        }
        
        
        return answer;
    }
}
