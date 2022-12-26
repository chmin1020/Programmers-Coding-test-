class Solution {
    private var idx = 1
    private val alphabets = charArrayOf('A','E','I','O','U')
    private val dictionary = mutableMapOf<String, Int>()

    fun solution(word: String): Int {
        val sb = StringBuilder()

        //dfs를 통한 가능 단어 찾기
        fun dfs(nextIdx: Int){
            sb.append(alphabets[nextIdx])

            dictionary[sb.toString()] = idx++
            
            //길이가 되면 끝
            if(sb.length == 5)
                return
            else
                for(i in alphabets.indices)
                    dfs(i)
            sb.deleteCharAt(sb.length - 1)
        }

        //----------------------------------------
        (0..4).forEach{ dfs(it) }
        return dictionary[word] ?: 1
    }
}
