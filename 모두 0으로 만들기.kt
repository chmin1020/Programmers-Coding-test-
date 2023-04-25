import kotlin.math.abs
import java.util.Stack

class Solution {
    var answer: Long = 0
    val graph = Array(300001){ mutableListOf<Int>() }
    lateinit var b:LongArray
    
    private data class Node(val num: Int, val parent: Int)
    
    fun solution(a: IntArray, edges: Array<IntArray>): Long {
        b = a.map{ it.toLong() }.toLongArray()
        
        if(b.sum() != 0L) return -1
        
        val visited = BooleanArray(a.size)
        edges.forEach{
            graph[it[0]].add(it[1])
            graph[it[1]].add(it[0])
        }
        
        val stack = Stack<Node>()
        stack.push(Node(0, -1))
        while(stack.isNotEmpty()){
            val cur = stack.pop()
            
            if (visited[cur.num]) {
                answer += abs(b[cur.num])
                if(cur.parent >= 0) b[cur.parent] += b[cur.num]
                b[cur.num] = 0
                continue
            }

            visited[cur.num] = true
            stack.push(cur)  

            for (next in graph[cur.num]) {
              if (!visited[next]) stack.push(Node(next, cur.num))
            }
        }
        
        return if(b[0] == 0L) answer else -1
    }
}
