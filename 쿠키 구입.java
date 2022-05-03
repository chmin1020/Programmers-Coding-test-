class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        int len = cookie.length;
        int start, end, left, right;
        
        for(int i = 0; i < len - 1; i++){
            start = i;
            end = i + 1;
            left = cookie[start];
            right = cookie[end];
            
            while(start >= 0 && end < len){
                if(left >= right){
                    if(left == right)
                        answer = Math.max(left, answer);
                    end++;
                    if(end < len)
                        right += cookie[end];
                }
                else{
                    start--;
                    if(start >= 0)
                       left += cookie[start];
                }
            }
        }
        return answer;
    }
}
