import kotlin.math.max

class Solution {
    //탐험 여부 체크 배열
    private lateinit var visited: BooleanArray

    //dfs를 통한 탐색
    private fun dfs(dungeons: Array<IntArray>, cur: Int, rest:Int, cnt: Int): Int{
        visited[cur] = true
        var res = cnt

        //현 상황에서 탐험 진행 시도
        dungeons.forEachIndexed{ idx, it ->
                if(it[0] <= rest && !visited[idx])
                    res = max(res, dfs(dungeons, idx, rest - it[1], cnt + 1))
            }

        visited[cur] = false
        return res
    }

    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        var answer: Int = -1

        visited = BooleanArray(dungeons.size){false}

        //최초 탐험 진행 시도
        dungeons.forEachIndexed{ idx, it ->
            if(it[0] <= k)
                answer = max(answer, dfs(dungeons, idx, k - it[1], 1))
        }
        

        return answer
    }
}
