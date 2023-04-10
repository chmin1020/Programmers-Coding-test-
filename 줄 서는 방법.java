class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];

        int cur = n - 1;
        int idx = 0;
        long prev = 0;
        boolean[] used = new boolean[n + 1];
        while(cur > 0){
            long divFac = factorial(cur--);
            long lowLimit = prev + divFac;

            //가장 큰 범위로 체크
            for(int i = 1; i <= n; i++){
                if(used[i]) continue; //이미 앞에서 사용함

                //범위에 속하면 그 수는 확정
                if(lowLimit - divFac < k && lowLimit >= k){
                    used[i] = true;
                    answer[idx++] = i;
                    prev = lowLimit - divFac;
                    break;
                }
                lowLimit += divFac;
            }
        }

        //마지막 수
        for(int i = 1; i <= 20; i++){
            if(!used[i]){
                answer[n - 1] = i;
                break;
            }
        }

        return answer;
    }
  
    //-- factorial 구하는 함수 --//
    private long factorial(int n){
        long res = 1;

        for(int i = 1; i <= n; i++)
            res *= i;
        return res;
    }
}
