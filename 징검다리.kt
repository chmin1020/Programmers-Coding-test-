class Solution {
    fun solution(distance: Int, rocks: IntArray, n: Int): Int {
        var answer = 0
        
        //징검다리 사이 거리들 배열 생성
        rocks.sort()
        val distances = IntArray(rocks.size + 1).also {
            it[0] = rocks[0]
            it[it.lastIndex] = distance - rocks.last()

            for(i in 1 until rocks.size)
                it[i] = rocks[i] - rocks[i - 1]
        }

        //이진탐색 준비
        var left = 1
        var right = distance

        //distance 범위 기준 이진탐색
        while(left <= right){
            val mid = (left + right) / 2

            var culTotal = 0
            var removed = 0

            //없애야 할 징검다리 카운트
            for(dis in distances){
                culTotal += dis
                if(culTotal < mid) removed++
                else culTotal = 0
            }

            //lower bound
            if(removed > n) right = mid - 1
            else {
                answer = mid
                left = mid + 1
            }
        }

        return answer
    }
}
