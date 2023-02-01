import java.util.Stack

class Solution {
    fun solution(numbers: IntArray): IntArray {
        val answers = IntArray(numbers.size){-1}
        val backLarges = Stack<Int>()

        for(idx in numbers.lastIndex downTo 0){
            //본인 이하인 놈은 무시
            while (backLarges.isNotEmpty() && backLarges.peek() <= numbers[idx])
                backLarges.pop()

            //큰 놈이 있으면 기입, 없으면 -1 유지
            if(backLarges.isNotEmpty())
                answers[idx] = backLarges.peek()

            backLarges.push(numbers[idx])
        }
        return answers
    }
}
