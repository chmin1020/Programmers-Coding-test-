class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();

        while (n > 0){
            int rest = n % 3;
            n /= 3;

            if(rest == 0){
                n--; //나누어 떨어지면 몫을 1 감소시킨다
                answer.append('4');
            }
            else if(rest == 1)
                answer.append('1');
            else
                answer.append('2');
        }

        return answer.reverse().toString();
    }
}
