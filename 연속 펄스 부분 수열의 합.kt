class Solution {
    companion object{
        private const val PLUS_PULSE = 0
        private const val MINUS_PULSE = 1
    }

    fun solution(sequence: IntArray): Long {
        //dp 세팅
        val dp = Array(sequence.size){ LongArray(2) }
        for((idx, each) in dp.withIndex()){
            each[PLUS_PULSE] = sequence[idx].toLong()
            each[MINUS_PULSE] = -sequence[idx].toLong()
        }

        //답 초기 세팅
        var answer = maxOf(dp[0][PLUS_PULSE], dp[0][MINUS_PULSE])

        //dp 연산
        for(idx in 1 until dp.size){
            dp[idx][PLUS_PULSE] = maxOf(dp[idx][PLUS_PULSE], dp[idx][PLUS_PULSE] + dp[idx - 1][MINUS_PULSE])
            dp[idx][MINUS_PULSE] = maxOf(dp[idx][MINUS_PULSE], dp[idx][MINUS_PULSE] + dp[idx - 1][PLUS_PULSE])
            answer = maxOf(answer, maxOf(dp[idx][PLUS_PULSE], dp[idx][MINUS_PULSE]))
        }

        return answer
    }
}
