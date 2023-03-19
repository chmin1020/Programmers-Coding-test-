class Solution {
    fun solution(s: String, skip: String, index: Int): String {
        val skipSet = mutableSetOf<Char>()
        skip.forEach { skipSet.add(it) }

        val sb = StringBuilder()
        s.forEach {
            var idx = it.toIdx()
            var cnt = 0
            while (cnt < index){
                if(skipSet.contains((++idx).toCharacter()))
                    continue
                cnt++
            }
            sb.append(idx.toCharacter())
        }

        return sb.toString()
    }

    private fun Char.toIdx() = this - 'a'
    private fun Int.toCharacter() = 'a' + (this % 26)
}
