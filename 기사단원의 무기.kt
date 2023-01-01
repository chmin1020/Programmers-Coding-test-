import kotlin.math.sqrt

class Solution {
    fun solution(number: Int, limit: Int, power: Int): Int {
        //약수 혹은 power, 즉 적절한 무게 선택하는 함수
        fun getWeight(n: Int): Int{
            //약수 구하기 (제곱근까지)
            var cnt = 0
            for(i in 1..sqrt(n.toDouble()).toInt()) {
                if (n % i == 0) {
                    cnt++
                    if(n / i != i)
                        cnt++
                }
                if (cnt > limit)
                    break
            }
            
            //약수가 제한보다 적으면 약수 개수, 아니면 power
            return if(cnt <= limit) cnt else power
        }

        //---------------------------
        var answer = 0

        for(i in 1..number)
            answer += getWeight(i)
        return answer
    }
}
