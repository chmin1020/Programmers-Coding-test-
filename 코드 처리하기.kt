class Solution {
    fun solution(code: String): String {
        var mode = 0
        val sb = StringBuilder("")
        
        code.forEachIndexed{ idx, c ->
            if(c == '1')
                mode = 1 - mode
            else if(mode == 0 && idx % 2 == 0)
                sb.append(c)
            else if(mode == 1 && idx % 2 == 1)
                sb.append(c)
        }
        
        val answer = if(sb.isEmpty()) "EMPTY" else sb.toString()
        return answer
    }
}
