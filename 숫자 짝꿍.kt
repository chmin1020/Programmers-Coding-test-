import java.lang.StringBuilder

class Solution {

    fun solution(X: String, Y: String): String {
        val commons = mutableListOf<Char>()
        val Xcontains = IntArray(10){0}

        //x,y의 공통 체크
        X.forEach { Xcontains[Character.getNumericValue(it)]++ }
        Y.forEach {
            val idx = Character.getNumericValue(it)
            if(Xcontains[idx] > 0){
                commons.add(it)
                Xcontains[idx]--
            } 
        }

        //정렬
        commons.sort()

        //출력
        if(commons.size == 0) //같은 것 x
            return "-1"
        else {
            val answer = StringBuilder()
            
            //0만 있는지 체크
            var onlyZero = true
            commons.forEach { 
                if(it != '0') 
                    onlyZero = false
                answer.append(it)
            }
            
            if(onlyZero) //0만 있음
                return "0"
            else 
                return answer.reverse().toString()
        }
    }
}
