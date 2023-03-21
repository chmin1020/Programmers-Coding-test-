const val INF = Int.MAX_VALUE
class Solution {
    fun solution(beginning: Array<IntArray>, target: Array<IntArray>): Int {
        val beginBoardForRowFirst1 = beginning.toBoolean2DArray()
        val beginBoardForRowFirst2 = beginning.toBoolean2DArray()
        val beginBoardForColumnFirst1 = beginning.toBoolean2DArray()
        val beginBoardForColumnFirst2 = beginning.toBoolean2DArray()
        val targetBoard = target.toBoolean2DArray()

        //--------- 첫 행 세팅(안 뒤집고) ----------------//
        val colReverseCnt1 = firstRowSetting(beginBoardForRowFirst1, targetBoard)
        val rowReverseCnt1 = restJobWithFirstRowSetting(beginBoardForRowFirst1, targetBoard)
        val firstRowAnswer1 = if (rowReverseCnt1 == INF) INF else (colReverseCnt1 + rowReverseCnt1)

        //--------- 첫 열 뒤집기(안 뒤집고) ----------------//
        val rowReverseCnt2 = firstColumnSetting(beginBoardForColumnFirst1, targetBoard)
        val colReverseCnt2 = restJobWithFirstColumnSetting(beginBoardForColumnFirst1, targetBoard)
        val firstColAnswer1 = if(colReverseCnt2 == INF) INF else (colReverseCnt2 + rowReverseCnt2)

        //--------- 첫 행 세팅(뒤집고) ----------------//
        rowInverse(beginBoardForRowFirst2, 0)
        val colReverseCnt3 = firstRowSetting(beginBoardForRowFirst2, targetBoard)
        val rowReverseCnt3 = restJobWithFirstRowSetting(beginBoardForRowFirst2, targetBoard)
        val firstRowAnswer2 = if (rowReverseCnt3 == INF) INF else (colReverseCnt3 + rowReverseCnt3 + 1)

        //--------- 첫 열 뒤집기(뒤집고) ----------------//
        colInverse(beginBoardForColumnFirst2, 0)
        val rowReverseCnt4 = firstColumnSetting(beginBoardForColumnFirst2, targetBoard)
        val colReverseCnt4 = restJobWithFirstColumnSetting(beginBoardForColumnFirst2, targetBoard)
        val firstColAnswer2 = if(colReverseCnt4 == INF) INF else (colReverseCnt4 + rowReverseCnt4 + 1)
            
        val answer = minOf(firstRowAnswer1, minOf(firstColAnswer1, minOf(firstRowAnswer2, firstColAnswer2)))
        return if(answer == INF) -1 else answer
    }

    //-- 입력 배열을 판별을 위한 비트마스크 배열로 변환 --//
    private fun Array<IntArray>.toBoolean2DArray(): Array<BooleanArray> {
        val result = Array(this.size){ BooleanArray(this[0].size)}
        for (row in this.indices)
            for (col in this[0].indices)
                result[row][col] = (this[row][col] != 0)
        return result
    }

    //-- 첫 행 상태 세팅 --//
    private fun firstRowSetting(board: Array<BooleanArray>, target: Array<BooleanArray>): Int {
        var cnt = 0

        //다를 때마다 열 돌리기
        for(col in board[0].indices){
            if(board[0][col] != target[0][col]) {
                colInverse(board, col)
                cnt++
            }
        }
        return cnt
    }

    //--나머지 행 변환 시뮬레이팅 --//
    private fun restJobWithFirstRowSetting(board: Array<BooleanArray>, target: Array<BooleanArray>): Int {
        var cnt = 0

        //나머지 행 돌리기
        for (row in 1 until board.size) {
            //행의 첫번째 열이 목표와 다르면 돌리기
            if (board[row][0] != target[row][0]) {
                rowInverse(board, row)
                cnt++
            }
        }

        //비교 작업 후 답 반환
        return if(areTwoBoardsSame(board, target)) cnt else INF
    }

    //-- 첫 열 상태 세팅 --//
    private fun firstColumnSetting(board: Array<BooleanArray>, target: Array<BooleanArray>): Int {
        var cnt = 0

        //다를 때마다 열 돌리기
        for(row in board.indices){
            if(board[row][0] != target[row][0]) {
                rowInverse(board, row)
                cnt++
            }
        }
        return cnt
    }

    //--나머지 열 변환 시뮬레이팅 --//
    private fun restJobWithFirstColumnSetting(board: Array<BooleanArray>, target: Array<BooleanArray>): Int {
        var cnt = 0

        //나머지 열 돌리기
        for (col in 1 until board[0].size) {
            //열의 첫번째 행이 목표와 다르면 돌리기
            if (board[0][col] != target[0][col]) {
                colInverse(board, col)
                cnt++
            }
        }

        //비교 작업 후 답 반환
        return if(areTwoBoardsSame(board, target)) cnt else INF
    }

    //-- 각 행 뒤집기 --//
    private fun rowInverse(board: Array<BooleanArray>, row: Int){
        for(col in board[0].indices)
            board[row][col] = !board[row][col]
    }

    //-- 각 열 뒤집기 --//
    private fun colInverse(board: Array<BooleanArray>, col: Int){
        for(row in board.indices)
            board[row][col] = !board[row][col]
    }

    //-- 두 보드 비교 --//
    private fun areTwoBoardsSame(board: Array<BooleanArray>, target: Array<BooleanArray>): Boolean{
        for (row in board.indices)
            for (col in board[0].indices)
                if (board[row][col] != target[row][col])
                    return false
        return true
    }
}
