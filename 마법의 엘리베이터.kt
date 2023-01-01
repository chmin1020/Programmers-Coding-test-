class Solution {
    fun solution(storey: Int): Int {
        var answer = 0
        var storeyCal = storey
        
        //0층에 가기 전까지 반복
        while(storeyCal != 0){
            val cur = storeyCal % 10

            if(cur < 5) { // 5보다 크면 올림
                answer += cur
                storeyCal -= cur
            }
            else if(cur > 5){ //5보다 작으면 내림
                answer += (10 - cur)
                storeyCal += (10 - cur)
            }
            else{ //5와 같으면 다음 자리수에 따라 판별
                val next = (storeyCal / 10) % 10
                if(next < 5){
                    answer += cur
                    storeyCal -= cur
                }
                else{
                    answer += (10 - cur)
                    storeyCal += (10 - cur)
                }
            }
            storeyCal /= 10
        }
        return answer
    }
}
