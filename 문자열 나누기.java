class Solution {
    public int solution(String s) {
        int answer = 0;
        
        int idx = 0;
        int first, last;
        char target;
        
        // 문자열 전체 탐색
        for(int i = 0; i < s.length(); i++){
            target = s.charAt(i);
            idx = i + 1;
            first = 1;
            last = 0;
            
            // 조건에 맞을 때까지 탐색
            while(idx < s.length()){
                if(s.charAt(idx) == target)
                    first++;
                else
                    last++;
                
                if(first == last)
                    break;
                idx++;
            }
            answer++;
            i = idx;
        }
        
        return answer;
    }
}
