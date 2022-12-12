class Solution {
    fun solution(numbers: LongArray): LongArray {
        val answer = LongArray(numbers.size)

        numbers.forEachIndexed { idx, it ->
            if(it % 2 == 0L) //짝수면 +1
                answer[idx] = it + 1
            else{ //홀수면 맨 처음 01을 10으로
                var comp = 2L
                while(it.and(comp) != 0L)
                    comp = comp.shl(1)

                answer[idx] = it.and(it - comp/2) + comp
            }
        }
        return answer
    }
}
