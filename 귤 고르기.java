import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        int[] count;
        int maxC = 0;
        
        // 최대 귤 크기 찾아서 count 배열 설정
        for(int each: tangerine)
            maxC = Math.max(maxC, each);
        count = new int[maxC + 1];
        Arrays.fill(count, 0);
        
        // count 배열 채우고 정렬
        for(int each: tangerine)
            count[each]++;
        Arrays.sort(count);
        
        // 개수 순서대로 살피며 답 확인 
        int cur = 0;
        for(answer = maxC ; answer >= 0; answer--){
            cur += count[answer];
            if(cur >= k)
                break;
        }
        
        return maxC - answer + 1;
    }
}
