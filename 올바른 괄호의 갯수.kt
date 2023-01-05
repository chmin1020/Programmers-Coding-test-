class Solution {
    private var answer = 0

    //--새 괄호 여는 함수--//
    private fun startParenthesis(openCnt: Int, closeCnt: Int, limit: Int){
        //괄호 다 열었으면 끝
        if(openCnt == limit){
            answer++
            return
        }
        
        //닫을 수 있는 괄호 경우의 수 모두 챙기기
        for(i in closeCnt..openCnt)
            startParenthesis(openCnt + 1, i, limit)
    }

    fun solution(n: Int): Int {
        startParenthesis(1, 0, n)
        return answer
    }
}
