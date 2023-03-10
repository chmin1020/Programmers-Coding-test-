class Solution {
    fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
        var possible = true
        var ptr1 = 0
        var ptr2 = 0
        
        //투 포인터 유지
        for(need in goal){
            if(ptr1 < cards1.size && cards1[ptr1] == need)
                ptr1++
            else if(ptr2 < cards2.size && cards2[ptr2] == need)
                ptr2++
            else{ //두 카드더미에서 둘 다 불가능
                possible = false
                break
            }
        }

        //결과 갱신
        return (if(possible) "Yes" else "No")
    }
}
