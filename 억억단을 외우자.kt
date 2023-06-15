import java.util.*

data class Node(val idx: Int, val cnt: Int): Comparable<Node>{
    override fun compareTo(other: Node): Int {
        return if(other.cnt == this.cnt) this.idx - other.idx
        else other.cnt - this.cnt
    }  
}

class Solution {
    fun solution(e: Int, starts: IntArray): IntArray {
        val arr = IntArray(e + 1){ 1 }
        
        //약수 개수 세기
        for(num in 2..e){
            var cur = num * 2
            while(cur <= e){
                arr[cur]++
                cur += num
            }
        }
        
        //약수 개수 내림차순 배열 만들기
        val heap = PriorityQueue<Node>()
        for(i in 1 until arr.size)
            heap.add(Node(i, arr[i]))
        val sorted = mutableListOf<Node>()
        for(i in 0 until e)
            sorted.add(heap.poll())
        
        
        val answer = mutableListOf<Int>()
        starts.forEach{
            val start = it
            for(i in sorted.indices){
                if(sorted[i].idx in start..e){
                    answer.add(sorted[i].idx)
                    break
                }
            }
        }
        
        return answer.toIntArray()
    }
}
