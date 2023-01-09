class Solution {
    fun solution(s: String): IntArray {
        val answer = IntArray(s.length){-1}
        val lastLocationTable = mutableMapOf<Char, Int>()

        s.forEachIndexed { idx, it -> 
            lastLocationTable[it]?.let { last -> answer[idx] = idx - last }
            lastLocationTable[it] = idx
        }

        return answer
    }
}
