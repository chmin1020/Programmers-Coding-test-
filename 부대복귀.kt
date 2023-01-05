import java.util.*

class Solution {
    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        val shorts = IntArray(n + 1){-1}

        //그래프 채우기
        val graph = Array(n + 1){ mutableListOf<Int>() }
        roads.forEach {
            graph[it[0]].add(it[1])
            graph[it[1]].add(it[0])
        }

        //--bfs를 통한 최소거리 탐색 함수--//
        fun findWay(start: Int){
            val qu:Queue<Pair<Int,Int>> = LinkedList()
            qu.add(Pair(start, 0))
            shorts[start] = 0

            while (qu.isNotEmpty()){
                val (cur, len) = qu.poll()

                //방문 안한 곳 방문 시작
                graph[cur].forEach {
                    if(shorts[it] == -1) {
                        shorts[it] = len + 1
                        qu.add(Pair(it, len + 1))
                    }
                }
            }
        }

        //모든 대원 경로 찾기
        findWay(destination)
        val answer = IntArray(sources.size)
        for(i in answer.indices)
            answer[i] = shorts[sources[i]]
        return answer
    }
}
