import java.util.LinkedList
import java.util.Queue

class Solution {
    private data class Pos(val x: Int, val y: Int)
    private data class MoveInfo(val pos: Pos, val time: Int)

    private lateinit var lever: Pos
    private lateinit var start: Pos
    private lateinit var exit: Pos

    companion object{
        const val DIRECT_CNT = 4
        val directX = intArrayOf(-1, 1, 0, 0)
        val directY = intArrayOf(0, 0, -1, 1)
    }

    fun solution(maps: Array<String>): Int {
        //맵 분석
        maps.forEachIndexed { rowIdx, row ->
            row.forEachIndexed { colIdx, it ->
                when (it) {
                    'S' -> start = Pos(rowIdx, colIdx)
                    'L' -> lever = Pos(rowIdx, colIdx)
                    'E' -> exit = Pos(rowIdx, colIdx)
                }
            }
        }

        //start -> lever -> exit
        val toLever = bfs(maps, start, lever)
        val toExit = bfs(maps, lever, exit)

        //답 생성
        return if (toLever < 0 || toExit < 0) -1 else (toLever + toExit)
    }

    //-- bfs 통한 경로 탐색 --//
    private fun bfs(map: Array<String>, start: Pos, end: Pos): Int {
        val qu: Queue<MoveInfo> = LinkedList()
        val visited = Array(map.size) { BooleanArray(map[0].length) }
        qu.add(MoveInfo(start, 0))
        visited[start.x][start.y] = true

        //bfs
        while (qu.isNotEmpty()){
            val cur = qu.poll()

            if(cur.pos == end)
                return cur.time

            //4방향 탐색
            for(d in 0 until DIRECT_CNT){
                val nx = cur.pos.x + directX[d]
                val ny = cur.pos.y + directY[d]

                //범위 벗어남, 벽 마주침, 이미 방문함
                if(nx !in map.indices || ny !in map[0].indices) continue
                if(map[nx][ny] == 'X') continue
                if(visited[nx][ny]) continue

                visited[nx][ny] = true
                qu.add(MoveInfo(Pos(nx, ny), cur.time + 1))
            }
        }

        return -1
    }
}
