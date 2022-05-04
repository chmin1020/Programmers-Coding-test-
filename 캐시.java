import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0, curLen = 0;
        HashSet<String> now = new HashSet<String>();
        LinkedList<String> list = new LinkedList<String>();
        
        if(cacheSize == 0) answer = cities.length * 5;
        else{
            for(int i = 0; i < cities.length; i++){
                cities[i] = cities[i].toLowerCase();

                if(now.contains(cities[i])){
                    int idx = 0;
                    for(String item : list){
                        if(item.equals(cities[i]))
                            break;
                        idx++;
                    }           
                    list.remove(idx);
                    answer++;
                }
                else{
                    if(list.size() >= cacheSize){
                        now.remove(list.get(list.size() - 1));
                        list.removeLast();  
                    }
                    now.add(cities[i]);
                    answer += 5;
                }

                list.addFirst(cities[i]);         
            }
        }
        return answer;        
    }
}
