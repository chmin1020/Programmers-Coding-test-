class Solution {
    private inner class Node{
        val map = mutableMapOf<Char, Node>()
        var cnt = 1
    }

    private inner class Trie{
        var cnt = 0
        private val root = Node()

        // 문자열 삽입
        fun insert(str: String){
            cnt++
            var tmp = root
            for(ch in str) {
                val childNode = tmp.map[ch]
                tmp = childNode?.apply { cnt++ } ?: Node().also { tmp.map[ch] = it  }
            }
        }

        // 문자열 찾기
        fun find(str: String): Int{
            var tmp = root
            for(ch in str) {
                val childNode = tmp.map[ch]
                childNode ?: return 0
                tmp = childNode
            }
            return tmp.cnt
        }
    }

    fun solution(words: Array<String>, queries: Array<String>): IntArray {
        val answer = IntArray(queries.size)

        val savedMap = mutableMapOf<String, Int>()
        val fromStartTries = mutableMapOf<Int, Trie>()
        val fromEndTries = mutableMapOf<Int,Trie>()

        //트라이에 저장
        words.forEach {
            if(fromStartTries[it.length] == null)
                fromStartTries[it.length] = Trie()
            if(fromEndTries[it.length] == null)
                fromEndTries[it.length] = Trie()

            fromStartTries[it.length]?.insert(it)
            fromEndTries[it.length]?.insert(it.reversed())
        }

        //각 쿼리 확인
        queries.forEachIndexed { idx, it ->
            if(savedMap[it] != null) answer[idx] = (savedMap[it] ?: 0)
            else{
                val strWithNoWild = it.replace("?", "")

                if(strWithNoWild.isBlank())
                    answer[idx] = fromStartTries[it.length]?.cnt ?: 0
                else {
                    val res =
                        if (it[0] == '?') fromEndTries[it.length]?.find(strWithNoWild.reversed()) ?: 0
                        else fromStartTries[it.length]?.find(strWithNoWild) ?: 0

                    savedMap[it] = res
                    answer[idx] = res
                }
            }
        }

        return answer
    }
}
