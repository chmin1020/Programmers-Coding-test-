class Solution {
    fun solution(keymap: Array<String>, targets: Array<String>): IntArray {
        //키맵 분석
        val fastWay = mutableMapOf<Char, Int>()
        keymap.forEach { key ->
            key.forEachIndexed { idx, c ->
                fastWay[c] = minOf((fastWay[c] ?: Int.MAX_VALUE), idx + 1)
            }
        }

        //답 만들기
        val answer = mutableListOf<Int>()
        targets.forEach {each ->
            var cnt = 0
            for(c in each){
                if(fastWay[c] == null) {
                    cnt = -1
                    break
                }
                cnt += fastWay[c] ?: 0
            }
            answer.add(cnt)
        }
        return answer.toIntArray()
    }
}
