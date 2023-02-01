class Solution {
    public static int solution(int n, int[] cores) {
        int start = 0;
        int end = cores[0] * n;

        //이진탐색으로 끝이 나는 시간대 찾기
        while(start < end){
            int mid = (start + end) / 2;

            int count = workCounting(mid, cores);

            if(count >= n)
                end = mid;
            else
                start = mid + 1;
        }

        //해당 시간에서 n과의 차이 계산
        int targetTime = end;
        int diff = workCounting(targetTime, cores) - n;

        //뒤로 이동하며 구하기
        for(int i = cores.length - 1; i >= 0; i--)
            if(targetTime % cores[i] == 0 && diff-- == 0)
                return (i + 1);
        return 1;
    }

    //-- 시간 당 작업 완료 개수 카운팅 함수 --//
    private static int workCounting(int time, int[] cores){
        int count = cores.length;

        for(int core: cores)
            count += (time / core);

        return count;
    }
}
