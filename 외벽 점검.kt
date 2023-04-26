class Solution {
    private var answer = Int.MAX_VALUE
    
    fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
        dist.sort()
        
        for (start in weak.indices)
            dfs(n, weak, dist, 1, start, 0)
            
        return if (answer == Int.MAX_VALUE) -1 else answer
    }
    
    fun dfs(n: Int, weak: IntArray, dist: IntArray, cnt: Int, pos: Int, isVisited: Int) {
        // 이미 전체 인원 투입 or 의미 없음
        if (cnt > dist.size) return
        if (cnt >= answer) return
        
        var visited = isVisited
        
        //커버할 수 있는 만큼 커버
        for (i in weak.indices) {
            val nextPos = (pos + i) % weak.size
            var diff = weak[nextPos] - weak[pos];
            
            // 최대 인덱스 지남(양수로 변환) 
            if (diff < 0)
                diff += n
            
            // nextPos 커버 불가 
            if (diff > dist[dist.size - cnt]) break
            else visited = visited or (1 shl nextPos)
        }
        
        // 모든 지점 탐색 완료
        if (visited == (1 shl weak.size) - 1) {
            answer = minOf(answer, cnt)
            return
        }
        
        //새로운 시작
        for (i in weak.indices) {
            if (visited and (1 shl i) != 0) continue
            dfs(n, weak, dist, cnt + 1, i, visited)
        }
    }
}
