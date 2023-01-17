class Solution {
    private fun getInclination(pos1: IntArray, pos2: IntArray)
        = (pos1[1] - pos2[1]) / (pos1[0] - pos2[0]).toDouble()
    
    fun solution(dots: Array<IntArray>): Int {
        if(getInclination(dots[0], dots[1]) == getInclination(dots[2], dots[3]))
            return 1
        if(getInclination(dots[0], dots[2]) == getInclination(dots[1], dots[3]))
            return 1
        if(getInclination(dots[0], dots[3]) == getInclination(dots[1], dots[2]))
            return 1
        
        return 0
    }
}
