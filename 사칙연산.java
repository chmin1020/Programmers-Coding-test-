class Solution {
    public int solution(String arr[]) {
        int numCnt = arr.length / 2 + 1;

        //각 숫자 이전에 있는 연산자 저장
        char[] operators = new char[numCnt];
        for (int i = 1; i < numCnt; i++)
            operators[i] = arr[i * 2 - 1].charAt(0);

        //dp로 순서에 따른 합 크기 계산
        int[][] dp = new int[numCnt][numCnt];
        for (int i = 0; i < numCnt; i++) {
            for (int j = 0; j < numCnt; j++) {
                if(operators[j] == '-') dp[i][j] = 100000000;
                else dp[i][j] = -100000000;
            }
        }


        for (int i = 0; i < numCnt; i++)
            dp[i][i] = Integer.parseInt(arr[i * 2]);

        //끝에서부터 대각선으로 dp 채우기
        for (int i = 1; i < numCnt; i++) {
            for (int j = 0; i + j < numCnt; j++) {
                for (int k = 1; k <= i; k++) {
                    if (operators[j] == '-')
                        dp[i + j][j] = Math.min(dp[i + j][j], calculate(operators[j + k], dp[j - 1 + k][j], dp[i + j][j + k]));
                    else
                        dp[i + j][j] = Math.max(dp[i + j][j], calculate(operators[j + k], dp[j - 1 + k][j], dp[i + j][j + k]));
                }
            }
        }

        return dp[numCnt - 1][0];
    }

    private int calculate(char op, int n1, int n2) {
        if (op == '+')
            return n1 + n2;
        else
            return n1 - n2;
    }
}

