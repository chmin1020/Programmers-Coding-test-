class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        for(int i = 1; i <= s.length()/2; i++){
            StringBuilder result = new StringBuilder();
            String zipStr = s.substring(0, i);
            int zipCnt = 1;
            
            for(int j = i; j < s.length(); j += i){
                int lim = (i + j <= s.length()) ? (i + j) : s.length();
                String nextStr = s.substring(j, lim);
                
                if(nextStr.equals(zipStr))
                    zipCnt++;
                else{
                    String num = (zipCnt == 1) ? "":Integer.toString(zipCnt);
                    result.append(num + zipStr);
                    zipStr = nextStr;
                    zipCnt = 1;
                }
            }
            String num = (zipCnt == 1) ? "":Integer.toString(zipCnt);
            result.append(num + zipStr);
            
            answer = Math.min(result.length(), answer);
        }
        return answer;
    }
}
