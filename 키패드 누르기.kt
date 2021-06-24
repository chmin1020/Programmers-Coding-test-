class Solution {
    fun solution(numbers: IntArray, hand: String): String {
        var answer = ""
        var lLoc = Pair(3,0)
        var rLoc = Pair(3,2)
        var lDis = 0
        var rDis = 0
        
        for(i in 0 until numbers.size){
            if(numbers[i] % 3 == 2 || numbers[i] == 0) {
                if(numbers[i] == 0) numbers[i] = 10
                
                lDis = Math.abs(lLoc.first - numbers[i]/3) + Math.abs(lLoc.second -1)
                rDis = Math.abs(rLoc.first - numbers[i]/3) + Math.abs(rLoc.second -1)
                                                                     
                if(lDis < rDis || (lDis == rDis && hand == "left")) {
                    answer+='L'
                    lLoc = lLoc.copy(first = numbers[i]/3, second = 1)
                }
                else if(lDis > rDis || (lDis == rDis && hand == "right")){
                    answer+='R'
                    rLoc = rLoc.copy(first = numbers[i]/3, second = 1)
                }
                
                if(numbers[i] == 10) numbers[i] = 0
            }
            else if(numbers[i] % 3 == 0) {
                answer+='R'
                rLoc = rLoc.copy(first = numbers[i]/3 - 1, second = 2)
            }
            else{
                answer+='L'
                lLoc = lLoc.copy(first = numbers[i]/3, second = 0)
            }
        }
        return answer
    }
}
