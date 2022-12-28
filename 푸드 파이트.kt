import java.lang.StringBuilder

class Solution {
    fun solution(food: IntArray): String {
        //음식 라인 완성하기
        val foodLine = StringBuilder()
        for(i in 1 until food.size)
            foodLine.append(i.toString().repeat(food[i]/2))
        
        return (foodLine.toString() + "0" + foodLine.reverse().toString())
    }
}
