import java.util.*

class Solution {
    fun solution(n: Int, k: Int, enemy: IntArray): Int {
        var answer = 0

        val heap = PriorityQueue<Int>(Collections.reverseOrder())

        var realN = n
        var realK = k
        
        //무적권이 있는 동안에는 게임 플레이 가능
        while(realK > 0){
            if(realN < enemy[answer]){ //더 못 나아갈 때 
                if(heap.isNotEmpty() && heap.peek() > enemy[answer]) { //예전에 무적권을 쓸까
                    realN += heap.peek()
                    heap.poll()
                }
                else //지금 무적권을 쓸까
                    answer++
                realK--
            }
            else{ //나아갈 때는 n 줄이고 heap 채우고 answer 늘린다
                heap.add(enemy[answer])
                realN -= enemy[answer]
                answer++
            }
        }

        return answer
    }
}
