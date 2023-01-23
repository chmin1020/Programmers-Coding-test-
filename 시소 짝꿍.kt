class Solution {
    //각 수의 경우의 수 체크를 위해 곱하고 나눌 수 조합
    private val mulNumbers = intArrayOf(1, 4, 3, 2, 3, 2, 1)
    private val divNumbers = intArrayOf(1, 3, 2, 1, 4, 3, 2)

    fun solution(weights: IntArray): Long {
        var answer = 0L

        //맵 채우기
        val numberTable = mutableMapOf<Int, Int>()
        weights.forEach { 
            numberTable[it] = (numberTable[it] ?: 0) + 1
        }
        
        //답 계산
        weights.forEach { 
            //본인 제거
            numberTable[it] = (numberTable[it] ?: 1) - 1
            
            //겹칠 수 있는 7가지 경우의 수 체크
            for(i in 0 until 7){
                if(it % divNumbers[i] != 0)
                    continue
                
                val possibleNum = it * mulNumbers[i] / divNumbers[i]
                answer += (numberTable[possibleNum] ?: 0)
            }
        }
        
        return answer
    }
}
