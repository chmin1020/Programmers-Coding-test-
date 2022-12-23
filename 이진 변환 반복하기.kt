import java.lang.StringBuilder

class Solution {
    //답 변수
    private var stepCnt = 0
    private var removedZeroCnt = 0

    //십진수를 이진수로
    private fun toBinary(n: Int): String{
        val sb = StringBuilder()

        //2로 나누고 나머지 세는 과정 반복
        var tar = n
        while(tar != 0){
            val th = tar % 2
            sb.append(if(th == 0) '0' else '1')
            tar /= 2
        }

        return sb.reverse().toString()
    }

    //binary 한 스텝
    private fun binaryProgress(bin: String): String{
        //1 개수 세기
        val num = bin.filter{ it == '1'}.count()

        //답 변수 갱신
        stepCnt++
        removedZeroCnt += (bin.length - num)
        
        return toBinary(num)
    }

    fun solution(s: String): IntArray {
        var str = s
        while(str != "1")
            str = binaryProgress(str)

        return intArrayOf(stepCnt, removedZeroCnt)
    }
}
