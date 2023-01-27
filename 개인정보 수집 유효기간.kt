class Solution {
    //만료날짜 구하기 함수
    private fun getExpireDate(termMonth: Int, originalDate: IntArray): IntArray{
        originalDate[1] += termMonth
        originalDate[2]--

        if(originalDate[2] <= 0){
            originalDate[2] += 28
            originalDate[1]--
        }
        if(originalDate[1] > 12){
            originalDate[1] -= 12
            originalDate[0]++
        }

        return originalDate
    }

    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        val answer = mutableListOf<Int>()

        //오늘 날짜 배열정리
        val todayDate = (today.split(".").map { it.toInt() }).toIntArray()

        //계약 기간 맵 만들기
        val termMap = mutableMapOf<Char, Int>()
        terms.forEach {
            val info = it.split(" ")
            termMap[info[0][0]] = info[1].toInt()
        }

        //각 기간 체크
        privacies.forEachIndexed{ idx, it ->
            val (dateStr, type) = it.split(" ")
            val date = dateStr.split(".").map { it.toInt() }.toIntArray()

            getExpireDate(termMap[type[0]] ?: 0, date).let{
                if(todayDate[0] > it[0] ||
                    (todayDate[0] == it[0] && todayDate[1] > it[1]) ||
                    (todayDate[0] == it[0] && todayDate[1] == it[1] && todayDate[2] > it[2]))
                    answer.add(idx + 1)
            }
        }

        return answer.toIntArray()
    }
}
