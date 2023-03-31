import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

class Solution {
    fun solution(m: Int, n: Int, startX: Int, startY: Int, balls: Array<IntArray>): IntArray {
        val answer = IntArray(balls.size)

        //각 공 계산
        balls.forEachIndexed { idx, it ->
            val (endX, endY) = it
            var result = Int.MAX_VALUE

            if(startY != endY || startX <= endX) //왼쪽
                result = minOf(result, getLengthSquare(startX, endX, abs(startY - endY)))
            if(startY != endY || startX >= endX) //오른쪽
                result = minOf(result, getLengthSquare(m - startX, m - endX, abs(startY - endY)))
            if(startX != endX || startY >= endY) //위쪽
                result = minOf(result, getLengthSquare(n - startY, n - endY, abs(startX - endX)))
            if(startX != endX || startY <= endY) //아래쪽
                result = minOf(result, getLengthSquare(startY, endY, abs(startX - endX)))

            answer[idx] = result
        }

        return answer
    }

    //-- 비율을 통한 제곱 최소 구하기 --//
    private fun getLengthSquare(startLen: Int, endLen: Int, dividingLen: Int): Int{
        val dividePart = dividingLen / (startLen + endLen).toDouble()

        val startPath = sqrt((dividePart * startLen).pow(2) + startLen.toDouble().pow(2))
        val endPath = sqrt((dividePart * endLen).pow(2) + endLen.toDouble().pow(2))

        return (startPath + endPath).pow(2).roundToInt()
    }
}
