import java.util.*

class Solution {
    private data class Assignment(val name:String, val startTime: Int, val rest: Int): Comparable<Assignment>{
        override fun compareTo(other: Assignment): Int {
            return this.startTime - other.startTime
        }
    }

    fun solution(plans: Array<Array<String>>): Array<String> {
        val planOrder = PriorityQueue<Assignment>().also {it.addAll(parseToAssignments(plans)) }
        return getProperOrder(planOrder)
    }

    //-- 순서에 맞춰 답 구하기 --//
    private fun getProperOrder(order: PriorityQueue<Assignment>): Array<String>{
        val results = mutableListOf<String>()
        val rest = Stack<Assignment>()

        while (order.isNotEmpty()){
            val cur = order.poll()

            //다음거 있으면 추가 작업, 없으면 그대로 풀이
            if(order.isNotEmpty()){
                val possibleTime = order.peek().startTime - cur.startTime

                if(possibleTime >= cur.rest){ //시간 충분
                    results.add(cur.name) //이 과제 해결

                    //못해서 남은 거 최대한 풀이
                    var restTime = possibleTime - cur.rest
                    while (restTime > 0 && rest.isNotEmpty()){
                        val restJob = rest.pop()

                        if(restTime >= restJob.rest){
                            results.add(restJob.name)
                            restTime -= restJob.rest
                        }
                        else{
                            rest.push(Assignment(restJob.name, restJob.startTime, restJob.rest - restTime))
                            break
                        }
                    }
                }
                else //시간 부족
                    rest.add(Assignment(cur.name, cur.startTime, cur.rest - possibleTime))
            }
            else
                results.add(cur.name)
        }

        //못해서 남은 것들 다 처리
        while (rest.isNotEmpty())
            results.add(rest.pop().name)

        return results.toTypedArray()
    }


    //-- plan 내용을 data 객체로 --//
    private fun parseToAssignments(plans: Array<Array<String>>): List<Assignment>{
        return plans.map { Assignment(it[0], it[1].timeToIntValue(), it[2].toInt()) }
    }

    //-- 시간을 정수형으로 변환 --//
    private fun String.timeToIntValue()
        = this.substring(0 until 2).toInt() * 60 + this.substring(3).toInt()
}
