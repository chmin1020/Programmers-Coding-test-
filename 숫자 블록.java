import static java.lang.Math.*;

class Solution {
    private static final int maxNum = 10000000;
    public int[] solution(long begin, long end) {
        int[] answer = new int[Long.valueOf(end - begin).intValue() + 1];

        //하나씩 채우기
        int idx = 0;
        for(long num = begin; num <= end; num++)
            answer[idx++] = getMaxNum(num);

        return answer;
    }

    //-- 위에 올라올 최대 숫자 계산 --//
    private int getMaxNum(long n){
        int ans = 0;
        for(int i = 1; i <= sqrt(n); i++) {
            if (n % i == 0) {
                long candidate1 = n / i;

                if (candidate1 <= maxNum && candidate1 * 2 <= n)
                    ans = max(ans, Long.valueOf(candidate1).intValue());
   
                if (i <= maxNum && i * 2L <= n)
                    ans = max(ans, i);
            }
        }
        return ans;
    }
}
