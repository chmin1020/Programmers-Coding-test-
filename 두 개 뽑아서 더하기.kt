class Solution {
    fun solution(numbers: IntArray): IntArray {
        //가능한 합 모두 체크
        val possibleSet = mutableSetOf<Int>()
        for(i in numbers.indices)
            for(j in i + 1 until numbers.size)
                possibleSet.add(numbers[i] + numbers[j])
        
        //배열로 만들고 정렬 
        val answer = possibleSet.toList().toIntArray()
        answer.sort()
        return answer
    }
}
