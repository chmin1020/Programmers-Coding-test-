class Solution {
    fun solution(quiz: Array<String>): Array<String> {
        val answer = Array(quiz.size){ "" }
        
        quiz.forEachIndexed{ idx, it ->
            val elements = it.split(" ")
            val num1 = elements[0].toInt()
            val num2 = elements[2].toInt()
            val result = elements[4].toInt()
            
            if(elements[1] == "+")
                answer[idx] = if(num1 + num2 == result) "O" else "X"
            else
                answer[idx] = if(num1 - num2 == result) "O" else "X"           
        }
    
        return answer
    }
}
