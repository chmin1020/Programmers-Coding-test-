class Solution {
    fun solution(s: String): IntArray {

        //각 단계의 숫자들만 문장에 남기기
        val eachSets = s.substring(2, s.length - 2).split("},{").toTypedArray()

        //길이에 맞게 정렬 (순서대로 배치)
        eachSets.sortBy{it.length}

        val answer = IntArray(eachSets.size)

        //set을 통해 정수 순서 체크
        val containCheck = mutableSetOf<Int>()
        var idx = 0
        eachSets.forEach {
            val eachNum = it.split(",")

            //새로운 수 보면 추가하고 넘어감
            for(i in eachNum.indices){
                val n = eachNum[i].toInt()
                
                if(!containCheck.contains(n)){
                    containCheck.add(n)
                    answer[idx++] = n     
                    break
                }
            }
        }

        return answer
    }
}
