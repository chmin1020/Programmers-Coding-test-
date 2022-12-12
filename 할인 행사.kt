class Solution {
    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        var answer: Int = 0

        // 요구표와 검색표
        val progressTable = mutableMapOf<String, IntArray>()

        // Table 완성
        want.forEachIndexed { index, s ->
            progressTable[s] = intArrayOf(number[index], 0)
        }

        //최초 열흘
        for (i in 0 until 10)
            progressTable[discount[i]]?.let { it[1]++ }
        if(progressTable.all { it.value[0] == it.value[1] })
            answer++

        //모든 열흘 확인
        for(i in 1..discount.size - 10) {
            progressTable[discount[i - 1]]?.let { it[1]-- }
            progressTable[discount[i + 9]]?.let { it[1]++ }
            
            if(progressTable.all { it.value[0] <= it.value[1] })
                answer++
        }

        return answer
    }
}
