class Solution {
    fun solution(a: IntArray): Int {
        var answer = a.size

        val toRightMin = IntArray(a.size).also { it[0] = a[0] }
        val toLeftMin = IntArray(a.size).also { it[a.lastIndex] = a.last() }

        //각 방향 누적 최솟값 저장
        for(i in 1 until a.size)
            toRightMin[i] = minOf(a[i], toRightMin[i - 1])
        for(i in a.lastIndex - 1 downTo 0)
            toLeftMin[i] = minOf(a[i], toLeftMin[i + 1])

        //양 끝을 제외한 나머지 가능 여부 체크
        for(i in 1 until a.size - 1){
            //두 측면 모두 최솟값이 현재 풍선보다 작다 (유지 불가능)
            if(toLeftMin[i] != a[i] && toRightMin[i] != a[i])
                answer--
        }
        return answer
    }
}
