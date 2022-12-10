import java.util.*
import kotlin.math.max

class Solution {

    fun solution(cards: IntArray): Int { 
        // 각 카드의 사이클 크기 저장 배열
        val inCycle = BooleanArray(cards.size){false}
        
        //max 순 저장할 힙
        val heap = PriorityQueue<Int>(Collections.reverseOrder())
        
        // 모든 카드 사이클 크기 갱신
        cards.forEach { card->
            //아직 갱신 시도를 안했으면
            if(!inCycle[card - 1]){
                val stack = Stack<Int>()
                stack.push(card - 1)

                //사이클 확인까지 돌리기
                var cur = cards[card - 1]
                while(cur != card) {
                    stack.push(cur - 1)
                    cur = cards[cur - 1]
                }

                //크기 갱신
                heap.add(stack.size)
                while(!stack.isEmpty())
                    inCycle[stack.pop()] = true
            }
        }
        
        if(heap.size > 1)
            return heap.poll() * heap.poll()
        return 0
    }
}
