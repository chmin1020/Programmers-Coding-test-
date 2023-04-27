class Solution {
    fun solution(num: Int, total: Int): IntArray {
        var cur = (0 until num).fold(0){ tot, it -> tot + it }
        var start = 0
        var end = num - 1
        
        //투포인터
        while(cur != total){
            if(cur > total){
                cur -= end--
                cur += --start
            }
            else{
                cur += ++end
                cur -= start++
            }
        }
        
        val answer = IntArray(num)
        for(i in start..end)
            answer[i - start] = i
        return answer
    }
}
