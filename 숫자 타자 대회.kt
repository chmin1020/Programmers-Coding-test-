class Solution {
    companion object{
        const val INF = 10000000
        const val NOT_DECIDED = Int.MAX_VALUE
    }

    private fun Char.toNumber() = this - '0'

    fun solution(numbers: String): Int {
        //손가락 이동 가중치 미리 구하기
        val fingerWeights = Array(10){ IntArray(10){ INF } }
        preCalculateFingerWeights(fingerWeights)

        val dp = Array(10) { Array(10) { IntArray(numbers.length){ NOT_DECIDED } } }

        //첫번째 버튼 상태 설정

        when(val firstNumber = numbers[0].toNumber()){
            4 -> dp[firstNumber][6][0] = 1
            6 -> dp[4][firstNumber][0] = 1
            else -> {
                dp[firstNumber][6][0] = fingerWeights[4][firstNumber]
                dp[4][firstNumber][0] = fingerWeights[6][firstNumber]
            }
        }

        //나머지 dp 돌리기
        for(idx in 1 until numbers.length) {
            val next = numbers[idx].toNumber()

            for(left in 0 until 10){
                for(right in 0 until 10){
                    //이전에 이렇게 누른 적이 없으면 넘어가기
                    if(dp[left][right][idx - 1] == NOT_DECIDED)
                        continue

                    //왼쪽,오른쪽 엄지로 새로 누르기
                    if(left == next)
                        dp[next][right][idx] = minOf(dp[next][right][idx], dp[left][right][idx - 1] + fingerWeights[left][next])
                    else if(right == next)
                        dp[left][next][idx] = minOf(dp[left][next][idx], dp[left][right][idx - 1] + fingerWeights[right][next])
                    else {
                        dp[next][right][idx] =
                            minOf(dp[next][right][idx], dp[left][right][idx - 1] + fingerWeights[left][next])
                        dp[left][next][idx] =
                            minOf(dp[left][next][idx], dp[left][right][idx - 1] + fingerWeights[right][next])
                    }
                }
            }
        }

        //최솟값 구하기
        var answer = NOT_DECIDED
        for(left in 0 until 10) {
            for (right in 0 until 10) {
                if (dp[left][right][numbers.lastIndex] == NOT_DECIDED)
                    continue

                answer = minOf(answer, dp[left][right][numbers.lastIndex])
            }
        }

        return answer
    }

    //-- 손가락 이동 가중치 구하기 --//
    private fun preCalculateFingerWeights(fingerWeights: Array<IntArray>){
        //가까운 버튼 세팅
        for(start in 0 until 10){
            for(end in 0 until 10){
               when{
                   start == end -> fingerWeights[start][end] = 1
                   areTheyNeighbor(start, end) -> fingerWeights[start][end] = 2
                   areTheyDiagonal(start, end) -> fingerWeights[start][end] = 3
               }
            }
        }

        //나머지 버튼 세팅
        for(mid in 0 until 10)
            for(start in 0 until 10)
                for(end in 0 until 10)
                    fingerWeights[start][end] = minOf(fingerWeights[start][end],fingerWeights[start][mid] + fingerWeights[mid][end])

    }

    //-- 두 숫자가 인접한 수인지 구하기 --//
    private fun areTheyNeighbor(cur: Int, tar: Int): Boolean{
        return when(cur){
            1 -> (tar == 2 || tar == 4)
            2 -> (tar == 1 || tar == 3 || tar == 5)
            3 -> (tar == 2 || tar == 6)
            4 -> (tar == 1 || tar == 5 || tar == 7)
            5 -> (tar == 2 || tar == 4 || tar == 6 || tar == 8)
            6 -> (tar == 3 || tar == 5 || tar == 9)
            7 -> (tar == 4 || tar == 8)
            8 -> (tar == 5 || tar == 7 || tar == 9 || tar == 0)
            9 -> (tar == 6 || tar == 8)
            else -> (tar == 8)
        }
    }

    //-- 두 숫자가 대각선 관계인지 구하기 -//
    private fun areTheyDiagonal(cur: Int, tar: Int): Boolean{
        return when(cur){
            1 -> (tar == 5)
            2 -> (tar == 4 || tar == 6)
            3 -> (tar == 5)
            4 -> (tar == 2 || tar == 8)
            5 -> (tar == 1 || tar == 3 || tar == 7 || tar == 9)
            6 -> (tar == 2 || tar == 8)
            7 -> (tar == 5 || tar == 0)
            8 -> (tar == 4 || tar == 6)
            9 -> (tar == 5 || tar == 0)
            else -> (tar == 7 || tar == 9)
        }
    }
}
