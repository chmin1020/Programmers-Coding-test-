import java.lang.Math.pow

class Solution {
    // 11011 표현 배열
    private val bitSet = intArrayOf(1, 1, 0, 1, 1)

    //제곱값 내보내는 함수
    private fun longPow(n: Int): Long = (1..n).fold(1L){res, _ -> res * 5}

    //범위에서 1을 세는 함수
    private fun countOnes(l: Long, r: Long, size: Long, allZero: Boolean): Int{
        if(size == 5L){ //크기가 5이면 계산
            val res = if(allZero) 0 else bitSet.filterIndexed { i, it -> i in l.toInt()..r.toInt() && it == 1 }.count()
            return res
        }
        else{ //크기가 5보다 크면 divide
            val newSize = size / 5L

            var start = 0L
            var result = 0
            var cnt = 1
            var rightDone = false

            //왼쪽 찾기
            while(cnt <= 5){
                if(l in start until (start + newSize)){
                    if(r in start until (start + newSize)) {
                        result += countOnes(l - start, r - start, newSize, (allZero || cnt == 3))
                        rightDone = true
                    }
                    else
                        result += countOnes(l - start, newSize - 1, newSize, (allZero || cnt == 3))
                    cnt++
                    break
                }
                start += newSize
                cnt++
            }
            start += newSize

            //처리 안되었다면 오른쪽 찾기
            while(!rightDone && cnt <= 5){
                if(r in start until (start + newSize)){
                    result += countOnes(0, r - start, newSize, (allZero || cnt == 3))
                    break
                }
                result += countOnes(0, newSize - 1, newSize, (allZero || cnt == 3))
                start += newSize
                cnt++
            }

            return result
        }
    }

    fun solution(n: Int, l: Long, r: Long): Int {
        return countOnes(l - 1, r - 1, longPow(n), false)
    }
}
