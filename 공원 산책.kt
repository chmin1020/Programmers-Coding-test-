fun main(){
    Solution().solution(arrayOf("SOO", "OOO", "OOO"), arrayOf("E 2", "S 2", "W 1"))
}

class Solution {
    private val directXMap = mapOf('E' to 0, 'W' to 0, 'S' to 1, 'N' to -1)
    private val directYMap = mapOf('E' to 1, 'W' to -1, 'S' to 0, 'N' to 0)

    fun solution(park: Array<String>, routes: Array<String>): IntArray {
        val rowLim = park.size
        val colLim = park[0].length

        //시작점 정하기
        var curX = 0
        var curY = 0
        outer@for(i in park.indices) {
            for (j in park[0].indices) {
                if (park[i][j] == 'S') {
                    curX = i
                    curY = j
                    break@outer
                }
            }
        }

        outer@for(route in routes){
            val direct = route[0]
            val distance = route[2] - '0'

            //다음 공간 설정
            val nx = curX + (directXMap[direct] ?: 0) * distance
            val ny = curY + (directYMap[direct] ?: 0) * distance
            
            //범위 넘는지 확인
            if(nx !in 0 until rowLim || ny !in 0 until colLim) continue
            
            //장애물 만나는지 확인
            for(i in minOf(curX, nx) .. maxOf(curX, nx))
                for(j in minOf(curY, ny) .. maxOf(curY, ny))
                    if(park[i][j] == 'X')
                        continue@outer

            //최종 이동
            curX = nx
            curY = ny
        }
        return intArrayOf(curX, curY)
    }
}
