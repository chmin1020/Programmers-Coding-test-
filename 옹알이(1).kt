class Solution {
    fun checkWord(word: String): Boolean{
        return (word == "aya" || word == "ye" || word == "woo" || word == "ma")
    }

    fun solution(babbling: Array<String>): Int {
        var answer = 0

        babbling.forEach {
            val sb = StringBuilder()

            for(c in it){
                sb.append(c)
                if(sb.length > 3)
                    break
                if(checkWord(sb.toString()))
                    sb.clear()
            }

            if(sb.isEmpty()) answer++
        }

        return answer
    }
}
