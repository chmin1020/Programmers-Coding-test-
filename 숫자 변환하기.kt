class Solution {
    private data class CalculatingInfo(val num: Int, val step: Int)

    fun solution(x: Int, y: Int, n: Int): Int {
        val visited = BooleanArray(y + 1){false}
        val queue = mutableListOf<CalculatingInfo>()
        queue.add(CalculatingInfo(x, 0))

        //bfs -> 가장 먼저 y 나오는 게 가장 빠름
        while(queue.isNotEmpty()){
            val cur = queue.removeFirst()

            if(cur.num == y)
                return cur.step

            if(cur.num < y){
                //n 더하기
                if(cur.num + n <= y && !visited[cur.num + n]){
                    queue.add(CalculatingInfo(cur.num + n, cur.step + 1))
                    visited[cur.num + n] = true                    
                }
                
                //2 곱하기
                if(cur.num * 2 <= y && !visited[cur.num * 2]){
                    queue.add(CalculatingInfo(cur.num * 2, cur.step + 1))
                    visited[cur.num * 2] = true             
                }
                
                //3 곱하기
                if(cur.num * 3 <= y && !visited[cur.num * 3]){
                    queue.add(CalculatingInfo(cur.num * 3, cur.step + 1))
                    visited[cur.num * 3] = true
                }
            }
        }

        //찾지 못했다면 -1 리턴
        return -1
    }
}
