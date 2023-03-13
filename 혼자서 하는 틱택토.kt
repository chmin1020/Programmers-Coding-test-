class Solution {
    companion object{
        private const val X = 'X'
        private const val O = 'O'
    }

    fun solution(board: Array<String>): Int {
        var answer = 1

        //1. x가 o보다 1개 적거나 같아야 한다.
        val xCnt = board.indices.fold(0){tot, row -> tot + board[row].count { it == X } }
        val oCnt = board.indices.fold(0){tot, row -> tot + board[row].count { it == O } }
        if(xCnt != oCnt && xCnt != oCnt - 1) answer = 0

        //2. x와 o가 둘 다 성공한 상황은 안된다.
        val xSuccess = isSuccessCaseExist(board, X)
        val oSuccess = isSuccessCaseExist(board, O)
        if(xSuccess && oSuccess) answer = 0

        //3. x가 3개 연속일 때는 o가 x의 개수와 같아야 한다.
        if(xSuccess && xCnt != oCnt) answer = 0

        //4. o가 3개 연속일 때는 x가 o의 개수보다 1개 적어야 한다.
        if(oSuccess && xCnt != oCnt - 1) answer = 0

        return answer
    }

    private fun isSuccessCaseExist(board: Array<String>, target: Char): Boolean{
        for(i in 0 until 3) {
            //가로
            if (board[i][0] == target && board[i][1] == target && board[i][2] == target)
                return true

            //세로
            if(board[0][i] == target && board[1][i] == target && board[2][i] == target)
                return true
        }

        //대각선
        if(board[0][0] == target && board[1][1] == target && board[2][2] == target)
            return true
        if(board[0][2] == target && board[1][1] == target && board[2][0] == target)
            return true

        return false
    }
}
