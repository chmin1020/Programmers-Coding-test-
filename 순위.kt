class Solution {
    fun solution(n: Int, results: Array<IntArray>): Int {
        val graph = Array(n){ BooleanArray(n){ false } }

        //그래프 정보 채우기
        results.forEach {
            graph[it[0] - 1][it[1] - 1] = true
        }

        //플로이드 와샬로 갱신
        for(k in graph.indices)
            for(i in graph.indices)
                for(j in graph.indices)
                    if(graph[i][k] && graph[k][j])
                        graph[i][j] = true

        //본인 제외 나머지와 모두 이어지는지 확인
        var answer = 0
        for(i in 0 until n) {
            var cnt = 0
            for(j in graph.indices)
                if(graph[i][j] || graph[j][i])
                    cnt++
            if(cnt == n - 1) answer++
        }

        return answer
    }
}
