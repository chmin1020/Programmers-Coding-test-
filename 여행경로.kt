import java.util.*

class Solution {
    private data class Ticket(val dest: String, var isUsed: Boolean)

    fun solution(tickets: Array<Array<String>>): Array<String> {
        //티켓 그래프 만들기
        val graph = mutableMapOf<String, MutableList<Ticket>>()
        fun makeGraph(){
            tickets.forEach {
                graph[it[0]] ?: run{ graph[it[0]] = mutableListOf() }
                graph[it[1]] ?: run{ graph[it[1]] = mutableListOf() }
                graph[it[0]]?.add(Ticket(it[1], false))
            }
            graph.values.forEach {
                it.sortBy { ticket ->  ticket.dest }
                it.forEach { ea -> print("${ea.dest}  ") }
                println()
            }
        }
        makeGraph()

        //dfs를 통해 순서를 지킨 경로 중 가장 빠른 리스트 기록
        val answerStack = Stack<String>()
        fun dfs(ticketCnt: Int, spot: String): Boolean {
            //모든 티켓을 다 썼다면
            if(ticketCnt == tickets.size){
                answerStack.push(spot)
                return true
            }

            for(each in graph[spot]!!){
                if(!each.isUsed){
                    //티켓 사용 경우의 수
                    each.isUsed = true
                    val possible = dfs(ticketCnt + 1, each.dest)
                    each.isUsed = false

                    //성공적인 경로를 찾았다면
                    if(possible){
                        answerStack.push(spot)
                        return true
                    }
                }
            }
            return false
        }

        //-----------------------------------------------------
        //ICN부터 티켓 사용 시작
        dfs(0,"ICN")

        val answerList = mutableListOf<String>()
        while(answerStack.isNotEmpty())
            answerList.add(answerStack.pop())

        return answerList.toTypedArray()
    }
}
