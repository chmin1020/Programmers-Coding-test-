class Solution {
    fun solution(n: Int, lighthouse: Array<IntArray>): Int {
        var answer = 0
        val lightOn = BooleanArray(n + 1){false}
        val graph = Array(n + 1){ mutableListOf<Int>() }

        // 정보를 토대로 인접 리스트 생성
        fun makeAdjacentList(){
            lighthouse.forEach {
                graph[it[0]].add(it[1])
                graph[it[1]].add(it[0])
            }
        }

        // 하나만 연결된 등대 반대쪽 불 밝히기
        fun oneLineLightOn(){
            println()
            graph.forEachIndexed { i, it ->
                if(it.size == 1){
                    if(!lightOn[i] && !lightOn[it.first()]){
                        lightOn[it.first()] = true
                        answer++
                    }

                    //처리한 등대 삭제
                    graph[it.first()].removeIf { each -> each == i }
                    graph[i].clear()
                }
            }
        }

        //----------------------------------------------------------
        makeAdjacentList()
        while(graph.any { it.size > 0 })
            oneLineLightOn()
        return answer
    }
}
