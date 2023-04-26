import java.util.*

class Solution {
    companion object{
        private lateinit var Board:Array<IntArray>
        private lateinit var Visited:Array<Array<BooleanArray>>
        private val rotate = intArrayOf(1, 3)
        private val directX = intArrayOf(1, 0, -1, 0)
        private val directY = intArrayOf(0, 1, 0, -1)
    }

    private data class MoveInfo(val x: Int, val y: Int,
                                val d: Int, val step: Int)

    fun solution(board: Array<IntArray>): Int{
        Board = board

        //첫번째 x,y, 방향
        Visited = Array(Board.size){ Array(Board.size) { BooleanArray(4) } }

        return bfs()
    }

    //-- bfs 통한 답 찾기 --//
    private fun bfs(): Int{
        val qu: Queue<MoveInfo> = LinkedList()

        Visited[0][0][1] = true
        Visited[0][1][3] = true

        qu.add(MoveInfo(0,0,1,0))
        while(qu.isNotEmpty()){
            val cur = qu.poll()
            val partnerX = cur.x + directX[cur.d]
            val partnerY = cur.y + directY[cur.d]


            //목적지에 도착
            if((cur.x == Board.lastIndex && cur.y == Board.lastIndex)
                || (partnerX == Board.lastIndex && partnerY == Board.lastIndex))
                return cur.step

            //이동
            for(d in 0 until 4){
                val nx1 = cur.x + directX[d]
                val ny1 = cur.y + directY[d]
                val nx2 = partnerX + directX[d]
                val ny2 = partnerY + directY[d]

                if(isInPossible(nx1, ny1) && isInPossible(nx2, ny2) && !Visited[nx1][ny1][cur.d]){
                    Visited[nx1][ny1][cur.d] = true
                    qu.add(MoveInfo(nx1, ny1, cur.d, cur.step + 1))
                }
            }

            //회전
            for(r in 0 until 2){
                val nD1 = (cur.d + rotate[r]) % 4
                val nD2 = (cur.d + rotate[1 - r]) % 4

                //1기준 회전
                val nx2 = cur.x + directX[nD1]
                val ny2 = cur.y + directY[nD1]
                if(isInPossible(partnerX + directX[nD1], partnerY + directY[nD1])
                    && isInPossible(nx2, ny2)
                    && !Visited[cur.x][cur.y][nD1]){
                    Visited[cur.x][cur.y][nD1] = true
                    qu.add(MoveInfo(cur.x, cur.y, nD1, cur.step + 1))
                }

                //2기준 회전
                val nx1 = partnerX + directX[nD2]
                val ny1 = partnerY + directY[nD2]
                if(isInPossible(nx1, ny1)
                    && isInPossible(cur.x + directX[nD2], cur.y + directY[nD2])
                    && !Visited[partnerX][partnerY][nD2]){
                    Visited[partnerX][partnerY][nD2] = true
                    qu.add(MoveInfo(partnerX, partnerY, nD2, cur.step + 1))
                }
            }
        }
        return -1
    }

    //-- 큐 삽입 가능 여부 체크 --//
    private fun isInPossible(nx: Int, ny: Int): Boolean{
        //범위 벗어남
        if(nx !in Board.indices || ny !in Board.indices)
            return false

        //1을 만남
        if(Board[nx][ny] == 1)
            return false

        return true
    }
}
