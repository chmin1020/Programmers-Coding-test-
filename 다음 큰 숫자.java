class Solution {
    public int solution(int n) {
        int answer = n + 1;
        
        int targetCnt = getBinaryOneCnt(n);
        while (targetCnt != getBinaryOneCnt(answer))
            answer++;

        return answer;
    }

    private int getBinaryOneCnt(int n){
        int res = 0;
        while (n > 0){
            if(n % 2 == 1)
                res++;
            n /= 2;
        }
        return res;
    }
}
