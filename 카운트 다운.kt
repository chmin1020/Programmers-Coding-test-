
fun main(){
    Solution().solution(58)
}
class Solution {
    companion object{
        const val DART_CNT = 0
        const val SPECIAL_CNT = 1
        const val POSSIBLE_SINGLE_DART_CNT = 60
    }

    fun solution(target: Int): IntArray {
        val dp = Array(100001){ IntArray(2) {0} }

        //가장 단순한 dp부터 채우기
        for(i in 1..20) {
                dp[i] = intArrayOf(1, 1)
                dp[i * 2] = intArrayOf(1, 0)
                dp[i * 3] = intArrayOf(1, 0)
        }
        if(target >= 50)
            dp[50] = intArrayOf(1, 1)

        //나머지 상향식으로 채우기
        for (score in 21..target) {
            if (score <= POSSIBLE_SINGLE_DART_CNT) {
                if (!canByOneDart(dp, score))
                    findBestForDart(dp, score, score - 1)
            }
            else findBestForDart(dp, score, POSSIBLE_SINGLE_DART_CNT)
        }
        
        return dp[target]
    }

    //-- 한개로 끝낼 수 있는지? --//
    private fun canByOneDart(dp: Array<IntArray>, score: Int) = (dp[score][DART_CNT] == 1)

    //-- 최고의 다트 방식 찾기 --//
    private fun findBestForDart(dp: Array<IntArray>, targetScore: Int, possibleScore: Int){
        var dartCnt = targetScore
        var maxSpecialCnt = 0

        for (dartScore in 1..possibleScore) {
            //한 방으로 정의되는 놈만 확인
            if (!canByOneDart(dp, dartScore)) continue

            //이전 다트 점수 + 현재 던질 다트 점수로 계산
            val beforeScore = targetScore - dartScore
            val beforeCnt = dp[beforeScore][DART_CNT]
            val beforeSpecialCnt = dp[beforeScore][SPECIAL_CNT]

            val expectedDartCnt = beforeCnt + 1
            val expectedSpecialCnt = beforeSpecialCnt + dp[dartScore][SPECIAL_CNT]

            //조건에 맞으면 dp 갱신
            if (expectedDartCnt < dartCnt || (expectedDartCnt == dartCnt && expectedSpecialCnt > maxSpecialCnt)) {
                dartCnt = expectedDartCnt
                maxSpecialCnt = expectedSpecialCnt
            }
        }

        //최종 입력
        dp[targetScore][DART_CNT] = dartCnt
        dp[targetScore][SPECIAL_CNT] = maxSpecialCnt
    }
}
