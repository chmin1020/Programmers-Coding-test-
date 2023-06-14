class Solution {
    fun solution(str_list: Array<String>): Array<String> {
        var idx = 0
        val answer = mutableListOf<String>()

        for(idx in str_list.indices){
            if(str_list[idx] == "l"){
                for(i in 0 until idx)
                    answer.add(str_list[i])
                break
            }
            if(str_list[idx] == "r"){
                for(i in idx + 1 until str_list.size)
                    answer.add(str_list[i])
                break
            }
        }
        
        return answer.toTypedArray()
    }
}
