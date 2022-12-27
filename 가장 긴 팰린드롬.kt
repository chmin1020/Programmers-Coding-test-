import java.lang.StringBuilder

class Solution {
    fun solution(s: String): Int {
        var answer = 1

        //padding 넣은 문자열 만들기
        fun strWithPadding(s: String): String{
            val sb = StringBuilder()
            for(i in s.indices){
                sb.append("#")
                sb.append(s[i])
            }
            sb.append("#")

            return sb.toString()
        }
        val chkStr = strWithPadding(s)
        val strLen = chkStr.length

        //dp 채우기
        val dp = IntArray(chkStr.length){0}
        fun fillDP(){
            for(i in chkStr.indices){
                while(i - dp[i] - 1 >= 0 && i + dp[i] + 1 < strLen && chkStr[i - dp[i] - 1] == chkStr[i + dp[i] + 1])
                    dp[i]++
            }
        }
        fillDP()

        return dp.maxByOrNull { it } ?: 1
    }
}
