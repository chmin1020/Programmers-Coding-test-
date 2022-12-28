class Solution {
    fun solution(a: Int, b: Int, n: Int): Int {
        //a개 주면 b병, 최초 n개 빈병
        var answer = 0
        var cur = n
        while(cur > 0){
            val newBottles = (cur / a) * b
            if(newBottles == 0) break
            cur %= a
            cur += newBottles
            answer += newBottles
        }

        return answer
    }
}
