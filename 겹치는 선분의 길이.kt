class Solution {
    fun solution(lines: Array<IntArray>): Int {
        var answer = 0
        
        val range = IntArray(201){ 0 }
        lines.forEach{
            val start = it[0] + 100
            val end = it[1] + 100
            
            for(i in start until end)
                range[i]++
        }
        
        for(i in 0..200)
            if(range[i] > 1) answer++
        
        return answer
    }
}
