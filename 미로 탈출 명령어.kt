import java.util.*
import kotlin.math.abs

class Solution {
    private class MoveInfo(val row:Int, val col:Int, val cnt:Int, val path:String)

    //이동 방향 알파벳 순 (d, l, r, u)
    private val directRow = intArrayOf(1, 0, 0, -1)
    private val directCol = intArrayOf(0, -1, 1, 0)
    private val directChar = charArrayOf('d', 'l', 'r', 'u')

    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        val visitCheck = Array(n + 1){Array(m + 1){BooleanArray(k  + 1){false} } }
        var answer = "impossible"

        // 경로 찾을 bfs 함수
        fun bfs() {
            val queue = LinkedList<MoveInfo>()
            queue.add(MoveInfo(x, y, 0, ""))
            visitCheck[x][y][0] = true

            while (queue.isNotEmpty()){
                val cur = queue.poll()

                if(cur.cnt == k){
                    if(cur.row ==r && cur.col == c) {
                        answer = cur.path
                        return
                    }
                    continue
                }

                for (i in 0 until 4) {
                    val nx = cur.row + directRow[i]
                    val ny = cur.col + directCol[i]

                    if(nx in 1..n && ny in 1..m && !visitCheck[nx][ny][cur.cnt + 1]) {
                        queue.add(MoveInfo(nx, ny, cur.cnt + 1, cur.path + directChar[i]))
                        visitCheck[nx][ny][cur.cnt + 1] = true
                    }
                }
            }
        }

        //----------------
        //함수 실행

        val bestCnt = abs(r - x) + abs(c - y)
        if(abs(k - bestCnt) % 2 == 0) bfs()
        return answer
    }
}
