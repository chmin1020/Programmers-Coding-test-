class Solution {
    public int solution(int n) {
        int answer = 0;

        int sum = 0;
        int start = 1;
        for(int end = 1; end <= n; end++){
            sum += end;

            //왼쪽 포인터 처리
            while (sum > n)
                sum -= (start++);

            if(sum == n)
                answer++;
        }

        return answer;
    }
}
