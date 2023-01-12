import java.util.PriorityQueue

class Solution {
    fun solution(n: Long): Long {
        var answer = 0L
        
        val heap = PriorityQueue<Long>()
        var tmp = n
        while(tmp != 0L)
            heap.add(tmp % 10L).apply{ tmp /= 10L }
        
        var mul = 1L
        while(heap.isNotEmpty())
            answer += heap.poll() * mul.apply{ mul *= 10L }
            
        return answer
    }
}
