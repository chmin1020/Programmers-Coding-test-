import java.util.*

class Solution {
    fun solution(k: Int, score: IntArray): IntArray {
        val answer = IntArray(score.size)

        val heap = PriorityQueue<Int>()
        for(i in score.indices){
            heap.add(score[i])

            while (heap.size > k) //크기 제한
                heap.poll()

            answer[i] = heap.peek()
        }

        return answer
    }
}
