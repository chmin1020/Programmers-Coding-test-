class Solution {
    private var xLen = 0
    private var yLen = 0
    private val visited by lazy { Array(xLen){BooleanArray(yLen){ false } } }
    private val answers = mutableListOf<Int>()

    //이동 방향
    private val directX = intArrayOf(-1, 1, 0, 0)
    private val directY = intArrayOf(0, 0, -1, 1)

    //--dfs 함수--//
    private fun dfs(maps: Array<String>, x: Int, y: Int): Int{
        visited[x][y] = true
        var result = (maps[x][y] - '0')

        //4방향 체크
        for(direct in 0 until 4){
            val nx = x + directX[direct]
            val ny = y + directY[direct]

            //유효하고 방문하지 않은 섬이라면 추가
            if(nx in 0 until xLen && ny in 0 until yLen && maps[nx][ny] != 'X' && !visited[nx][ny])
                result += dfs(maps, nx, ny)
        }

        return result
    }

    fun solution(maps: Array<String>): IntArray {
        xLen = maps.size
        yLen = maps[0].length

        //결과 배열 제작
        for(i in 0 until xLen)
            for(j in 0 until yLen)
                if(maps[i][j] != 'X' && !visited[i][j])
                    answers.add(dfs(maps, i, j))

        //정렬 및 반환
        if(answers.isEmpty()) answers.add(-1)
        answers.sort()
        return answers.toIntArray()
    }
}
