class Solution {
    fun solution(price: Int, money: Int, count: Int): Long {
        var total:Long = 0L
        
        for(i in 1..count)
            total += (price * i).toLong()
            
        val answer = if(money > total) 0 else total - money
        return answer
    }
}
