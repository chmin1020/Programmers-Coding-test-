import java.util.*

class Solution {
    //각 음식 정보 데이터
    private data class Info(val time:Int, val idx:Int): Comparable<Info>{
        override fun compareTo(other: Info): Int {
            return this.time - other.time
        }
    }

    fun solution(food_times: IntArray, k: Long): Int {
        var curTime = 0L
        var curCycle = 0

        val nextFood = IntArray(food_times.size){i ->  (i + 1) % food_times.size }
        val heap = PriorityQueue<Info>()

        //heap에 음식 정보 넣기
        food_times.forEachIndexed { index, it -> heap.add(Info(it, index)) }

        //-- 다음 음식을 찾아주는 함수 --//
        fun getNext(n: Int): Int{
            if(food_times[nextFood[n]] == 0)
                nextFood[n] = getNext(nextFood[n])
            return nextFood[n]
        }

        //heap을 통해 빠르게 음식 하나씩 없애기
        while (heap.isNotEmpty()){
            val thisCycle = heap.peek().time //이번 음식 시간 크기
            val addTime = (thisCycle - curCycle) * heap.size.toLong() //더해질 시간
            if(curTime + addTime <= k) {
                curTime += addTime
                curCycle += (thisCycle - curCycle)
                //같은 시간 대의 모든 음식 제거
                while (heap.isNotEmpty() && thisCycle == heap.peek().time) {
                    food_times[heap.peek().idx] = 0
                    heap.poll()
                }
            }
            else break
        }

        //음식 다 제거 시 -1, 아니면 일렬 체크
        if(heap.isEmpty())
            return -1
        else{
            //체크 시작 음식 찾기
            var curPtr = 0
            if (food_times[curPtr] <= 0)
                curPtr = getNext(curPtr)

            //마무리까지 돌리기
            while(curTime + heap.size < k)
                curTime += heap.size
            while(curTime++ < k)
                curPtr = getNext(curPtr)
            return curPtr + 1
         }
    }
}
