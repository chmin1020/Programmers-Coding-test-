class Solution {
    fun solution(a: IntArray): Int {
        var answer = 0

        //각 원소 개수 세기
        val existNumbers = a.groupBy { it }.map { Pair(it.key, it.value.count()) }.toMap().toSortedMap()

        existNumbers.forEach{
            //원소 값과 개수
            val targetNum = it.key
            val targetCnt = it.value

            //답이 커질 가능성이 있음
            if(targetCnt > answer) {
                var curResult = 0
 
                var targetWaiting = false
                var otherPossible = false

                for (element in a) {
                    //교집합 원소 충족
                    if(element == targetNum)
                        targetWaiting = true

                    //교집합 외 원소 충족
                    if(element != targetNum)
                        otherPossible = true

                    //짝수 집합 하나 충족
                    if(targetWaiting && otherPossible) {
                        curResult++
                        targetWaiting = false
                        otherPossible = false
                    }
                }
                answer = maxOf(answer, curResult)
            }
        }

        return answer * 2
    }
}
