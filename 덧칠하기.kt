class Solution {
    fun solution(n: Int, m: Int, section: IntArray): Int {
        var curIdx = 1
        var startIdx = 0
        var curCnt = 1

        //그리디 방식으로 색칠
        while (curIdx < section.size){
            if(section[curIdx] - section[startIdx] + 1 > m){
                curCnt++
                startIdx = curIdx
            }
            curIdx++
        }

        return curCnt
    }
}
