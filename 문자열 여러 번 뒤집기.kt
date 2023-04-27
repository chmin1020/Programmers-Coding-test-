class Solution {
    fun solution(my_string: String, queries: Array<IntArray>): String {
        var tmp = my_string
        
        queries.forEach{
            val (s, e) = it
            
            val reverse = tmp.substring(s..e).reversed()
            val newStr = tmp.substring(0 until s) + reverse + tmp.substring(e + 1)
            tmp = newStr
        }
        
        return tmp
    }
}
