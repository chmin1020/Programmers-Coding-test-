class Solution {
    fun solution(stones: IntArray, k: Int): Int {
        var left = stones.minOrNull() ?: 0
        var right = stones.maxOrNull() ?: 0

        //이진탐색으로 적절한 수 찾기
        outer@ while(left < right){
            val mid = (left + right) / 2

            var cnt = 0
            var maxCnt = 0
            for(i in stones.indices){
                //연속된 수명 다 한 징검다리 개수 카운트
                if(stones[i] <= mid) maxCnt = maxOf(++cnt, maxCnt)
                else cnt = 0
            }

            if(maxCnt < k)
                left = mid + 1
            else if(maxCnt >= k)
                right = mid
        }

        return right
    }
}
