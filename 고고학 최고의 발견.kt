const val INF = Int.MAX_VALUE

class Solution {
    //퍼즐 크기
    private var len: Int = 0

    //방향 상수 배열
    private val directX = intArrayOf(-1, 1, 0, 0)
    private val directY = intArrayOf(0, 0, -1, 1)

    //답 프로퍼티
    private var answer = INF


    fun solution(clockHands: Array<IntArray>): Int {
        len = clockHands.size

        //첫번째 행을 기점으로 가능한 모든 경우의 수 체크
        setFirstRowSituation(clockHands, 0, 0)

        return answer
    }

    //-- 첫번째 줄의 상태를 결정하기 위한 재귀 함수 --//
    private fun setFirstRowSituation(clockHands: Array<IntArray>, col: Int, rotateCnt: Int){
        if(col == len) {
            //기존 표본 변경 방지를 위해 복사본 생성
            val copy = Array(len){IntArray(len)}
            for(i in 0 until len)
                for(j in 0 until len)
                    copy[i][j] = clockHands[i][j]

            decideRestParts(copy, rotateCnt)
        }
        else {
            //0~3번 회전 경우의 수 등록
            for (step in 0 until 4) {
                rotate(clockHands, 0, col, step) //회전
                setFirstRowSituation(clockHands, col + 1, rotateCnt + step)
                rotate(clockHands, 0, col, 4 - step) //복구
            }
        }
    }

    //-- 첫 줄 제외 나머지 부분의 회전을 결정하고 확인하는 함수 --//
    private fun decideRestParts(clockHands: Array<IntArray>, rotateCnt: Int){
        var lastCnt = rotateCnt

        //나머지 줄 돌리기
        for(eachRow in 1 until len){
            for(eachCol in 0 until len){
                //윗 좌표 시계 방향에 따른 현재 회전 스텝 결졍
                val step = (4 - clockHands[eachRow - 1][eachCol]) % 4

                //실제 회전
                rotate(clockHands, eachRow, eachCol, step)
                lastCnt += step
            }
        }

        //조건을 충족하면 답 갱신
        if(clockHands.all { eachRow -> eachRow.all { it == 0 }})
            answer = minOf(answer, lastCnt)
    }


    //-- 해당 좌표 시계를 step 횟수 회전시키는 함수 --//
    private fun rotate(clockHands: Array<IntArray>, x: Int, y: Int, step: Int){
        if(step % 4 == 0) return

        //해당 좌표 시계 step 횟수 회전
        clockHands[x][y] = (clockHands[x][y] + step) % 4

        //근처 가능한 좌표 시계 마찬가지로 회전
        for(directConstant in 0 until 4){
            val nx = x + directX[directConstant]
            val ny = y + directY[directConstant]

            if(nx in 0 until len && ny in 0 until len)
                clockHands[nx][ny] = (clockHands[nx][ny] + step) % 4
        }
    }
}
