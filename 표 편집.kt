import java.lang.StringBuilder
import java.util.*

class Solution {
    //테이블을 표현할 연결리스트
    private class NodeList(first: Node){
        private var last = first //최초 노드 추가를 위한 마지막 노드 포인터
        private var cur = first //현재 커서 포인터
        private val ctrlZ = Stack<Node>() //z 작업을 위한 보관 공간 

        //리스트에 노드 추가
        fun add(new: Node){
            last.next = new
            new.prev= last
            last = new
        }
        
        //U 작업
        fun moveUp(n: Int){
            for(i in 1..n)
                cur.prev?.let{ cur = it }
        }

        //D 작업
        fun moveDown(n: Int){
            for(i in 1..n)
                cur.next?.let{ cur = it }
        }

        //Z 작업
        fun restore(){
            if(ctrlZ.isNotEmpty()){
                val tar = ctrlZ.pop()
                tar.linked = true

                var tmp = tar.prev
                while(tmp != null && !tmp.linked)
                    tmp = tmp.prev
                tmp?.next = tar
                tar.prev = tmp

                tmp = tar.next
                while (tmp != null && !tmp.linked)
                    tmp = tmp.next
                tmp?.prev = tar
                tar.next = tmp
            }
        }

        //C 작업
        fun delete(){
            cur.linked = false
            cur.prev?.next = cur.next
            cur.next?.prev = cur.prev
            ctrlZ.push(cur)

            cur = cur.next ?: cur.prev ?: cur
        }

        //리스트 순회하여 최초 테이블과 비교
        fun lastDance(check: BooleanArray){
            while(cur.prev != null)
                cur.prev?.let { cur = it }

            var ite:Node? = cur

            while(ite != null){
                check[ite.num] = true
                ite = ite.next
            }
        }
    }
    
    private data class Node
        (val num: Int, var linked: Boolean = true, var prev: Node? = null, var next: Node? = null)

    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        //테이블 완성
        val table = NodeList(Node(0))
        for(i in 1 until n)
            table.add(Node(i))
        table.moveDown(k)

        //명령어 수행
        cmd.forEach {
            val each = it.split(" ")

            when(each[0]){
                "U"-> table.moveUp(each[1].toInt())
                "D"-> table.moveDown(each[1].toInt())
                "C"-> table.delete()
                "Z"-> table.restore()
            }
        }

        //남아 있는 행 체크
        val check = BooleanArray(n){false}
        table.lastDance(check)

        //결과를 토대로 답 문자열 생성
        val answerMaker = StringBuilder()
        for(i in check.indices) {
            if (check[i]) answerMaker.append("O")
            else answerMaker.append("X")
        }
        return answerMaker.toString()
    }
}
