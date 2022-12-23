class Solution {
    private var zeroCnt = 0
    private var oneCnt = 0

    fun solution(arr: Array<IntArray>): IntArray {
        //실질적 행동 함수
        fun zipOrDivide(sx: Int, sy: Int, len: Int){
            val sElement = arr[sx][sy] //압축 시도 값

            //영역 넓이가 1보다 크면 젼체 검사
            if(len > 1) {
                for (i in sx until sx + len) {
                    for (j in sy until sy + len) {
                        if (arr[i][j] != sElement) { //압축 불가 시 나누기
                            val nextLen = len / 2
                            zipOrDivide(sx, sy, nextLen)
                            zipOrDivide(sx + nextLen, sy, nextLen)
                            zipOrDivide(sx, sy + nextLen, nextLen)
                            zipOrDivide(sx + nextLen, sy + nextLen, nextLen)
                            return
                        }
                    }
                }
            }

            //압축 가능하면 압축
            if(sElement == 0) zeroCnt++
            else oneCnt++
        }
        
        //-----------------------------------------------------
        //함수 실행 시작 
        zipOrDivide(0, 0, arr.size)
        return intArrayOf(zeroCnt, oneCnt)
    }
}
