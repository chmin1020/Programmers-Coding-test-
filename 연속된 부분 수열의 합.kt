class Solution {
    fun solution(sequence: IntArray, k: Int): IntArray {
        var startPtr = 0
        var endPtr = 0
        var sum = sequence[0]

        val answer = intArrayOf(0, sequence.lastIndex)

        //투 포인터로 경우 탐색
        while(startPtr <= endPtr && endPtr < sequence.size){
            if(sum < k && ++endPtr < sequence.size)
                sum += sequence[endPtr]
            else if(sum > k)
                sum -= sequence[startPtr++]

            //길이가 짧다면(앞으로만 이동하므로 더 앞에 있을 가능성은 무시)
            if(sum == k){
                if(answer[1] - answer[0] > endPtr - startPtr) {
                    answer[0] = startPtr
                    answer[1] = endPtr
                }

                sum -= sequence[startPtr++]
            }
        }
        return answer
    }
}
