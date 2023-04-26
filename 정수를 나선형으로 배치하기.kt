class Solution {
    private val directX = intArrayOf(1, 0, -1, 0)
    private val directY = intArrayOf(0, -1, 0, 1)
    
    fun solution(n: Int): Array<IntArray> {
        var answer = Array(n){IntArray(n)}
        
        //첫번째 행 채워두기
        for(i in 0 until n)
            answer[0][i] = i + 1
        
        var cx = 0
        var cy = n - 1
        var cd = 0
        var cur = n + 1
        var turn = 0 //한 rest 당 2번
        var rest = n - 1 //2번 당 1씩 줄어듬
        
        while(rest > 0){
            for(i in 0 until rest){
                cx += directX[cd]
                cy += directY[cd]
                answer[cx][cy] = cur++
            }
            
            //속도와 횟수 조절 
            turn++
            if(turn == 2){
                turn = 0
                rest--
            }
            
            //방향 조절
            cd = (cd + 1) % 4
        }
        
        return answer
    }
}
