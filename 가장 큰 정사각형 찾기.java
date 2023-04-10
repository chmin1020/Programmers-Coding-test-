import static java.lang.Math.min;

class Solution {
    public int solution(int[][] board) {
        int[][] dp = new int[board.length][board[0].length];

        //dp 끄트머리 채우기
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(i != 0 && j != 0) continue;
                dp[i][j] = board[i][j];
            }
        }

        //내부 dp 채우기
        for (int i = 1; i < board.length; i++)
            for (int j = 1; j < board[0].length; j++)
                if(board[i][j] == 1)
                    dp[i][j] = min(dp[i - 1][j - 1], min(dp[i][j - 1], dp[i - 1][j])) + 1;

        //최대 길이 찾기
        int maxLen = 0;
        for (int[] ints : dp)
            for (int j = 0; j < dp[0].length; j++)
                if (ints[j] > maxLen)
                    maxLen = ints[j];

        return maxLen * maxLen;
    }
}
