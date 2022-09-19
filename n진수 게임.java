class Solution {
    char[] table = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    
    private String convertNum(int n, int tar){
        if(tar == 0)
            return "0";
        
        String result = "";
        while(tar > 0){
            int cIdx = tar % n;
            tar /= n;
            result = result + String.valueOf(table[cIdx]);
        }
        
        StringBuilder sb = new StringBuilder(result);
        return sb.reverse().toString();
    }
    
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        int num = 0, cur = 0, idx = 0;
        
        while(idx < t){
            String convNum = convertNum(n, num);
            for(int i = 0; i < convNum.length(); i++){
                if(cur == (p - 1)){
                    answer = answer + String.valueOf(convNum.charAt(i));   
                    idx++;
                }
                if(idx >= t)
                    break;
                cur = (cur + 1) % m;
            }
            num++;
        }
        return answer;
    }
}
