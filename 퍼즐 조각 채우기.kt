class Solution {
    //--공간(퍼즐과 빈공간)의 정보를 저장하는 클래스--//
    class SpaceInfo(val directSorts: IntArray, val posList: List<IntArray>){
        //공간 크기
        val spaceSize = posList.size

        // 공간 체크 여부
        var isChecked = false

        //퍼즐과 공간 일치 여부 체크 함수//
        fun matchCheck(puzzleDirectSort: IntArray): Boolean{
            if(puzzleDirectSort.size == directSorts.size){
                for(i in directSorts.indices)
                    if(directSorts[i] != puzzleDirectSort[i])
                        return false
                return true
            }
            return false
        }
    }

    //방향 상수
    private val directX = intArrayOf(-1, 1, 0, 0)
    private val directY = intArrayOf(0, 0, -1, 1)

    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        var answer = 0
        val spaces = mutableListOf<SpaceInfo>()
        val puzzles = mutableListOf<SpaceInfo>()

        //---------------------내부 사용 함수---------------------

        //좌표 범위 벗어남 여부 체크 함수//
        fun isInRange(x: Int, y: Int) = (x in game_board.indices && y in game_board.indices)

        //dfs 함수//
        fun dfs
        (board: Array<IntArray>, directSort: MutableList<Int>, posList: MutableList<IntArray>, x: Int, y: Int, find: Int){
            board[x][y] = if(find == 1) 0 else 1
            posList.add(intArrayOf(x, y))

            //4방향 체크
            for(i in 0 until 4){
                val nx = x + directX[i]
                val ny = y + directY[i]

                //갈 길이 있다면 경로 추가
                if(isInRange(nx, ny) && board[nx][ny] == find) {
                    directSort.add(i)
                    dfs(board, directSort, posList, nx, ny, find)
                    directSort.add(if(i % 2 == 0) i + 1 else i - 1)
                }
            }
        }

        //모양 정보 저장 함수//
        fun saveShapes(board: Array<IntArray>, store: MutableList<SpaceInfo>, find: Int){
            for(i in board.indices){
                for(j in board[i].indices){
                    if(board[i][j] == find) {
                        val posList = mutableListOf<IntArray>()
                        val directSort = mutableListOf<Int>()

                        dfs(board, directSort, posList, i, j, find)
                        store.add(SpaceInfo(directSort.toIntArray(), posList))
                    }
                }
            }
        }

        //테이블 회전 함수//
        fun tableRotate(){
            val newTable = Array(table.size){IntArray(table.size) }

            for(i in table.indices)
                for(j in table.indices)
                    newTable[j][table.size - 1 - i] = table[i][j]

            for(i in table.indices)
                for(j in table.indices)
                    table[i][j] = newTable[i][j]
        }

        //-----------------------------------------------------

        //게임 보드판 공간 정리
        saveShapes(game_board, spaces, 0)

        //퍼즐 테이블 4개 방향 확인
        for(i in 0 until 4){
            puzzles.clear()
            saveShapes(table, puzzles, 1)

            //끼워 맞춰보기
            for(space in spaces){
                if(space.isChecked) continue

                for(puzzle in puzzles){
                    if(puzzle.isChecked) continue

                    if(space.matchCheck(puzzle.directSorts)){
                        puzzle.isChecked = true
                        space.isChecked = true
                        answer += puzzle.spaceSize
                        break
                    }
                }
            }

            //쓰지 않은 퍼즐 돌려놓고 테이블 회전
            puzzles.filter { !it.isChecked }.map { it.posList }
                .forEach { it.forEach{ pos-> table[pos[0]][pos[1]] = 1 } }
            tableRotate()
        }

        return answer
    }
}
