import java.util.*

class Solution {
    fun solution(gems: Array<String>): IntArray {
        //맵으로 각 보석의 위치를 별개 큐에 집어넣기
        val mapOfPos = mutableMapOf<String, Queue<Int>>()
        gems.forEachIndexed { i, it ->
            mapOfPos[it] ?: mapOfPos.put(it, LinkedList())
            mapOfPos[it]?.add(i + 1)
        }

        //힙과 max 값으로 최솟값 계산
        var lowLim = Int.MAX_VALUE
        var highLim = 0
        val heap = PriorityQueue<Pair<String, Int>>(compareBy { it.second })
        mapOfPos.forEach{
            highLim = maxOf(highLim, it.value.peek())
            lowLim = minOf(lowLim, it.value.peek())
            heap.add(Pair(it.key, it.value.poll()))
        }
        var len = highLim - lowLim
        var curMax = highLim

        while(heap.isNotEmpty()){
            val cur = heap.poll()

            if(mapOfPos[cur.first]?.isEmpty() != false) //더 남은 보석 위치 x
                break
            else{
                //보석 하나 빼고 그 보석의 다음 위치 넣기
                val new = mapOfPos[cur.first]?.poll() ?: -1
                curMax = maxOf(curMax, new)
                heap.add(Pair(cur.first, new))

                //더 짧은 구간 갱신
                if(curMax - heap.peek().second in 0 until len) {
                    len = curMax - heap.peek().second
                    highLim = curMax
                    lowLim = heap.peek().second
                }
            }
        }

        return intArrayOf(lowLim, highLim)
    }
}
