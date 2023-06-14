class Solution {
    fun solution(num_list: IntArray): Int {
        var evenTot = 0
        var oddTot = 0
        
        for(i in num_list.indices){
            if(i % 2 == 0) evenTot += num_list[i]
            else oddTot += num_list[i]
        }
        
        return maxOf(evenTot, oddTot)
    }
}
