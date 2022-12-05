import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        Map<Integer, Integer> cul = new HashMap<Integer, Integer>();
        Set<Integer> first = new HashSet<Integer>();
        Set<Integer> second = new HashSet<Integer>();
        
        // map으로 각 원소 누적합 저장, set 설정 
        for(int i = 0; i < topping.length; i++){
            int add = 1;
            if(cul.get(topping[i]) != null)
                add += cul.get(topping[i]);
            cul.put(topping[i], add);
            
            second.add(topping[i]);
        }
        
        //자른 점 기반으로 남는 원소 종류 체크
        for(int i = 0; i < topping.length - 1; i++){
            first.add(topping[i]);
            
            int update = cul.get(topping[i]) - 1;
            cul.put(topping[i], update);

            if(update == 0)
                second.remove(topping[i]);
                
            //두 조각의 원소 종류 같으면 answer 증가
            if(first.size() == second.size())
                answer++;
        }
        
        return answer;
    }
}
