class Solution {
    fun solution(a: Int, b: Int): Long {
        return if(a < b)(a..b).fold(0L){tot, it-> tot + it}
               else (b..a).fold(0L){tot, it-> tot + it}
    }
}
