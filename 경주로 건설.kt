import java.util.*

class Solution {
    //각 경로에 관한 정보 클래스
    private data class Path(val x: Int, val y: Int, val cost: Int, val bd: Int): Comparable<Path>{
        override fun compareTo(other: Path): Int {
            return this.cost - other.cost
        }
    }

    private data class Space(var direct: Int, var cost: Int)

    //4방향의 배열식 표현
    private val directX = intArrayOf( -1, 1, 0, 0)
    private val directY = intArrayOf( 0, 0, -1, 1)

    //진행하는 동안 범위와 방향 일치성 체크
    private fun isSameDirect(d1:Int, d2:Int): Boolean = (d1 == d2 || d1 == -1)
    private fun isInRange(x: Int, y: Int, dest: Int): Boolean = (x in 0..dest && y in 0..dest)

    fun solution(board: Array<IntArray>): Int {
        val check = Array(board.size){Array(board.size){ Space(-1, Int.MAX_VALUE) } }

        val dest = board.size - 1

        //bfs를 통한 경로 탐색
        val qu:Queue<Path> = LinkedList()
        qu.add(Path(0, 0, 0, -1))
        while(qu.isNotEmpty()){
            val cur = qu.poll()

            if(check[cur.x][cur.y].cost >= cur.cost)
                check[cur.x][cur.y] = Space(cur.bd, cur.cost)
            else if(check[cur.x][cur.y].direct == cur.bd)
                continue

            //이동
            for(d in 0 until 4){
                val nx = cur.x + directX[d]
                val ny = cur.y + directY[d]
                val cost = if(isSameDirect(cur.bd, d)) 100 else 600
                if(isInRange(nx, ny, dest) && board[nx][ny] != 1){
                    qu.add(Path(nx, ny, cur.cost + cost, d))
                }
            }
        }

        for(i in 0..dest){
            for(j in 0..dest){
                print("${check[i][j]}   ")
            }
            println()
        }

        return check[dest][dest].cost
    }
}
