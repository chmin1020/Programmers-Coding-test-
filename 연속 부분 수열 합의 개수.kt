class Solution {
    fun solution(elements: IntArray): Int {
        val limit = elements.size
        val possibles = mutableSetOf<Int>()

        //해당 길이의 모든 수열 체크
        fun checkAllSeq(len: Int){
            var sum = elements.filterIndexed{index, _ ->  index < len}.sum()
            var eIdx = len - 1

            //가능한 합 set에 추가
            possibles.add(sum)
            for(sIdx in 1 until limit){
                eIdx = (eIdx + 1) % limit
                sum -= elements[sIdx - 1]
                sum += elements[eIdx]
                possibles.add(sum)
            }
        }

        //------------------------------------
        for(i in 1..limit)
            checkAllSeq(i)
        return possibles.size
    }
}
