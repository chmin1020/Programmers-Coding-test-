class Solution {
    fun solution(name: Array<String>, yearning: IntArray, photo: Array<Array<String>>): IntArray {
        //이름과 점수 매핑
        val mapping = mutableMapOf<String, Int>()
        for(idx in name.indices)
            mapping[name[idx]] = yearning[idx]

        //각 상황 답 구하기
        val answer = IntArray(photo.size)
        for(idx in answer.indices)
            answer[idx] = photo[idx].fold(0){tot, it -> tot + (mapping[it] ?: 0)}
        
        return answer
    }
}
