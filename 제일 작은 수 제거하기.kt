class Solution {
    fun solution(arr: IntArray): IntArray {
        // -1
        if(arr.size == 1)
            return intArrayOf(-1)
    
        //최솟값 빼고 넣은 리스트 생성
        val minVal = arr.minByOrNull{ it } ?: arr[0]
        val answer = mutableListOf<Int>()
        arr.forEach{ if(it != minVal) answer.add(it)}
        
        return answer.toIntArray()
    }
}
