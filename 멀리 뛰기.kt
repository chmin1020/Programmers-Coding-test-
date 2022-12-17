class Solution {
    //dp를 통한 피보나치
    private val dp = IntArray(2001){0}.also { it[1] = 1; it[2] = 2 }
    private fun fibonacci(n: Int): Int{
        if(dp[n] == 0)
            dp[n] = (fibonacci(n - 2) + fibonacci(n - 1)) % 1234567
        println("$n -> ${dp[n]}")
        return dp[n]
    }

    fun solution(n: Int): Long {
        return fibonacci(n).toLong()
    }
}
