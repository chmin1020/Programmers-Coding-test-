import java.lang.StringBuilder

class Solution {
    private fun sameCheck(str: String) = (str == "ye" || str == "ma" || str == "aya" || str == "woo")
    
    fun solution(babbling: Array<String>): Int {
        var answer = 0
        var before = ""
        val sb = StringBuilder()

        //하나씩 체크
        babbling.forEach {
            sb.clear()
            before = ""
            for(c in it){
                sb.append(c)

                if(sb.length > 3) break
                if(before != sb.toString() && sameCheck(sb.toString())) {
                    before = sb.toString()
                    sb.clear()
                }
            }
            if(sb.isEmpty()) answer++
        }

        return answer
    }
}
