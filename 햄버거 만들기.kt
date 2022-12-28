class Solution {
    private val hamburger = intArrayOf(1, 2, 3, 1)
    private var nextIng = 0

    fun solution(ingredient: IntArray): Int {
        var answer = 0
        val st = mutableListOf<Int>()

        for(each in ingredient){
            st.add(each)
            
            //재료 4개 찰 때마다 검수
            if(st.size >= 4){
                val sub = st.subList(st.size - 4, st.size)
                var right = true

                for(i in 0..3)
                    if(hamburger[i] != sub[i])
                        right = false

                //햄버거 맞으면 answer 올리고 4개 재료 삭제
                if(right){
                    answer++
                    for(i in 0..3)
                        st.removeLast()
                }
            }
        }
        return answer
    }
}
