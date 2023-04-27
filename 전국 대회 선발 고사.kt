import java.util.*

class Solution {
    private data class Rank(val idx: Int, val rank: Int): Comparable<Rank>{
        override fun compareTo(other: Rank) = this.rank - other.rank
    }
    
    fun solution(rank: IntArray, attendance: BooleanArray): Int {
        val heap = PriorityQueue<Rank>()
        
        rank.forEachIndexed{ idx, it ->
            if(attendance[idx])
                heap.add(Rank(idx, it))
        }
        
        val a = heap.poll().idx
        val b = heap.poll().idx
        val c = heap.poll().idx
        
        return 10000 * a + 100 * b + c
    }
}
