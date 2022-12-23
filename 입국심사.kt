class Solution {
    fun solution(n: Int, times: IntArray): Long {
        //이 시간에 얼마나 많이 처리할 수 있는지 확인
        fun howManyCanThey(time: Long): Long{
            return times.fold(0L){res, it -> res + (time / it)}
        }

        //시간의 처음과 끝 한도 정의
        var first = 0L
        var last = (times.maxByOrNull { it } ?: times[0]).toLong() * n

        //lower bound
        while(first <= last){
            val mid = (first + last) / 2
            val result = howManyCanThey(mid)
            
            if(result >= n)
                last = mid
            else
                first = mid + 1
        }
        return last
    }
}
