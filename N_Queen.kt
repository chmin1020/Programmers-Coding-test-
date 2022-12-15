import kotlin.math.abs
import kotlin.math.pow

class Solution {
    var max = 0
    var answer = 0

    //여왕 위치 리스트
    private val queens = mutableListOf<Pair<Int, Int>>()

    //가능한 열 보여줌
    private var impossibleCols = 4095

    //백트래킹을 통해 체스말을 배치하는 함수
    private fun placeQueens(cnt: Int, cx: Int, cy: Int){

        //여기에 배치가 가능한지 체크
        val isImpossible = queens.any{
                    it.first == cx ||
                    it.second == cy ||
                    abs(it.first - cx) == abs(it.second - cy)}

        if(isImpossible)
            return

        //여왕을 모두 배치하면 answer + 1
        if(cnt == max){
            answer++
            return
        }

        //아니면 다음 배치 테스트
        queens.add(Pair(cx, cy))
        impossibleCols = impossibleCols.xor((1).shl(cy))

        //다음 배치 진행(다음 행부터)
        for(i in 0 until max)
            if(impossibleCols.and((1).shl(i)) != 0) placeQueens(cnt + 1, cx + 1, i)

        queens.remove(Pair(cx, cy))
        impossibleCols = impossibleCols.or((1).shl(cy))
    }

    fun solution(n: Int): Int {
        max = n

        for(i in 0 until n)
            placeQueens(1, 0, i)

        return answer
    }
}
