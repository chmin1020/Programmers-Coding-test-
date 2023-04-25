import kotlin.math.*

class Solution {
    private data class Pos(val x: Int, val y: Int)   
    fun solution(r1: Int, r2: Int): Long {
        var answer: Long = 0
        
        val r1x = r1.toDouble().pow(2)
        val r2x = r2.toDouble().pow(2)
        
        for(x in 1..r2){
            val y1 = ceil(sqrt(r1x - x.toDouble().pow(2))).toLong()
            val y2 = floor(sqrt(r2x - x.toDouble().pow(2))).toLong()

            answer += (y2 - y1 + 1) * 4
        }
    
        return answer
    }
}
