import java.util.*

class Solution {
    fun solution(n: Int): Int {
        var answer = 0

        var tmp = n
        val stack = Stack<Int>()
        
        //3진법 뒤집기 스택 저장
        while(tmp != 0){
            stack.push(tmp % 3)
            tmp /= 3
        }

        //구한 값을 통한 덧셈을 답 계산
        var mulNum = 1
        while(stack.isNotEmpty()){
            answer += (mulNum * stack.pop())
            mulNum *= 3
        }
        
        return answer
    }
}
