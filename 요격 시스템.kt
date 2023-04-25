class Solution {
    fun solution(targets: Array<IntArray>): Int {
        var answer: Int = 0
        
        targets.sortBy{ it[1] }
        
        val base = intArrayOf(-1, 0)
        for(i in targets.indices){
            if(targets[i][0] >= base[1]){
                answer++
                base[0] = targets[i][0]
                base[1] = targets[i][1]
            }
        }
        
        return answer
    }
}
