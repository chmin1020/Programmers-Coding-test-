import java.util.*

class Solution {
    private val directX = intArrayOf(-1, 1, 0, 0)
    private val directY = intArrayOf(0, 0, -1, 1)

    private data class Pos(val x: Int, val y: Int)
    private data class Move(val pos: Pos, val cnt: Int)

    fun solution(board: Array<String>): Int {
        var robotX = 0
        var robotY = 0

        //로봇 위치 찾기
        outer@while (robotX in board.indices){
            while (robotY in board[0].indices){
                if(board[robotX][robotY] == 'R')
                    break@outer
                robotY++
            }
            robotX++
            robotY = 0
        }

        return bfsFinding(board, Pos(robotX, robotY))
    }

    //-- bfs 통해 최소 경로 구하기 --//
    private fun bfsFinding(board: Array<String>, initialPos: Pos): Int {
        val qu: Queue<Move> = LinkedList()
        val visited = Array(board.size) { BooleanArray(board[0].length) { false } }

        visited[initialPos.x][initialPos.y] = true
        qu.add(Move(initialPos, 0))

        while (qu.isNotEmpty()){
            val cur = qu.poll()

            if(board[cur.pos.x][cur.pos.y] == 'G')
                return cur.cnt

            for(d in 0 until 4){
                var nx = cur.pos.x
                var ny = cur.pos.y

                //이동하기
                if(d in 0 until 2) {
                    while (nx in board.indices && board[nx][ny] != 'D')
                        nx += directX[d]
                    nx -= directX[d]
                }
                else {
                    while (ny in board[0].indices && board[nx][ny] != 'D')
                        ny += directY[d]
                    ny -= directY[d]
                }

                //이미 방문
                if(visited[nx][ny])
                    continue

                //큐에 삽입
                visited[nx][ny] = true
                qu.add(Move(Pos(nx, ny), cur.cnt + 1))
            }
        }
        return -1
    }
}
