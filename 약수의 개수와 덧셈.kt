class Solution {

    //약수 구하는 함수
    private fun countDivisor(num: Int): Int{
        var cnt = 0
        for(i in 1..num)
            if(i * (num/i) == num)
                cnt++
        
        return cnt
    }

    fun solution(left: Int, right: Int): Int {
        var answer: Int = 0

        //약수 세서 더하거나 빼기
        for(n in left..right){
            val count = countDivisor(n)

            if(count % 2 == 0)
                answer += n
            else
                answer -= n
        }
        return answer
    }
}
