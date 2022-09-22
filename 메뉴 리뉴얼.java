import java.util.*;

class Solution {
    Map<String, Integer> orderEx = new HashMap<String, Integer>();
    int maxCnt;
    
    private void getPossibles(int n, int cnt, int idx, String full, String course){
        if(idx == full.length())
            return;
        
        char[] chArr = (course + full.substring(idx, idx + 1)).toCharArray();
        Arrays.sort(chArr);
        String newStr = new String(chArr);
        
        if(n == cnt){
            int many = 1;
            
            if(orderEx.get(newStr) != null){
                 many += orderEx.get(newStr);
                maxCnt = Math.max(maxCnt, many);
            }
            orderEx.put(newStr, many);
            
            return;
        }
        
        for(int i = idx + 1; i < full.length(); i++)
            getPossibles(n, cnt + 1, i, full, newStr);
    }
    
    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> result = new ArrayList<String>();
        
        //모든 course에 대해
        for(int num: course){
            maxCnt = -1;
            
            //모든 가능성 계산
            for(String each: orders)
                for(int i = 0; i < each.length(); i++)
                    getPossibles(num, 1, i, each, "");
            
            //최대 추가
            for(String key: orderEx.keySet())
                if(orderEx.get(key) == maxCnt)
                    result.add(key);
            
            orderEx.clear();
        }
        
        String[] answer = new String[result.size()];
        for(int i = 0; i < answer.length; i++)
            answer[i] = result.get(i);
        
        Arrays.sort(answer);
        
        return answer;
    }
}
