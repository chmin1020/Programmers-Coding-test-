import kotlin.math.*

class Solution {
    fun solution(nums: IntArray): Int {
        var answer = 0
        var tmp = 0
        var isPrime = true
        
        for(i in 0 until nums.size){
            for(j in i+1 until nums.size){
                for(k in j+1 until nums.size){
                    tmp = nums[i] + nums[j] + nums[k]
                    isPrime = true
                    for(n in 2 until sqrt(tmp.toDouble()).toInt()+1){
                        if(tmp%n == 0){
                            isPrime = false
                            break
                        }
                    }
                   if(isPrime) answer++
                }
            }
        }
        return answer
    }
}
