import kotlin.math.abs
import kotlin.math.min

class Solution {
    //dfs 함수
    private fun dfs(graph: Array<MutableList<Int>>, from: Int, to: Int): Int{
        var ans = 1
        
        //상위 노드 제외 나머지 노드 세기
        graph[to].forEach{
            if(it != from)
                ans += dfs(graph, to, it)
        }

        return ans
    }

    fun solution(n: Int, wires: Array<IntArray>): Int {
        var answer: Int = Int.MAX_VALUE
        val graph = Array(n + 1){ mutableListOf<Int>()}

        //그래프 완성
        wires.forEach {
            graph[it[0]].add(it[1])
            graph[it[1]].add(it[0])
        }

        //와이어 한 개씩 끊어 보기
        wires.forEach { nodes ->
            var firstCnt = 0
            var secondCnt = 0
            
            //나눠진 두 개의 subTree 노드 개수 세기(by dfs)
            firstCnt = dfs(graph, nodes[1], nodes[0])
            secondCnt = dfs(graph, nodes[0], nodes[1])
            
            //최소값 갱신
            answer = min(answer, abs(secondCnt - firstCnt))
        }

        return answer
    }
}
