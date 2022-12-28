import java.util.*

class Solution {
    fun solution(k: Int, m: Int, score: IntArray): Int {
        var answer = 0
        //우선순위 큐로 큰 것부터
        val heap = PriorityQueue<Int>(Collections.reverseOrder())
        heap.addAll(score.toList())

        //상자 만들 수 있는 경우 큰 것부터 넣기
        while(heap.size >= m){
            for(i in 1 until m) heap.poll()
            answer += m * heap.poll()
        }

        return answer
    }
}
