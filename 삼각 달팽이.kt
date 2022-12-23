class Solution {
    fun solution(n: Int): IntArray {
        //빈 배열 생성
        val len = (1..n).fold(0){tot, it -> tot + it}
        val board = Array(1001){IntArray(1001){0}}

        //달팽이 과정
        var rowFirst = 0
        var rowLast = n - 1
        var colFirst = 0
        var colLast = n - 1
        var curNum = 1

        while (rowFirst <= rowLast && colFirst <= colLast && curNum <= len) {
            for (i in colFirst until colLast) //아래로
                board[i][rowFirst] = curNum++

            for (i in rowFirst until rowLast) //옆으로
                board[colLast][i] = curNum++

            var eachEnd = rowLast
            for (i in colLast downTo colFirst + 1) //위로
                board[i][eachEnd--] = curNum++

            //한 칸만 남은 경우는 위 반복문 적용 안되므로 따로 설정
            if(rowFirst == rowLast && colFirst == colLast)
                board[colFirst][rowFirst] = curNum

            //새 내부 삼각형을 위한 위치 조절
            rowFirst++
            rowLast -= 2
            colFirst += 2
            colLast--
        }

        //답 출력
        val answer = IntArray(len){0}
        curNum = 0
        for(i in 0 until n)
            for(j in 0..i)
                answer[curNum++] = board[i][j]

        return answer
    }
}
