import kotlin.math.sqrt

class Solution {

    //두 수의 최대공약수 구하기
    fun euclidFun(n1: Int, n2: Int): Int{
        var first = n1
        var second = n2

        //큰 게 앞으로
        if(first < second){
            first = n2
            second = n1
        }

        while(second != 0){
            val rest = first % second
            first = second
            second = rest
        }

        return first
    }

    //한 수의 약수를 구하는 함수
    fun getDivisors(num: Int): IntArray{
        val res = mutableListOf<Int>()
        
        for(i in 1..sqrt(num.toDouble()).toInt()) {
            if (num % i == 0) {
                res.add(i)
                res.add(num / i)
            }
        }
        return res.toIntArray()
    }

    fun solution(arrayA: IntArray, arrayB: IntArray): Int {
        var answer = 0

        // a와 b에서 집합의 공약수들 추출
        val aCandidates = getDivisors(arrayA.reduce{res, it -> euclidFun(res, it)})
        val bCandidates = getDivisors(arrayB.reduce{res, it -> euclidFun(res, it)})

        // a의 조건을 만족시키는 숫자를 b로 확인
        for(i in aCandidates.indices){
            if(arrayB.none { it % aCandidates[i] == 0 })
                answer = maxOf(answer, aCandidates[i])
        }

        // 반대도 확인
        for(i in bCandidates.indices){
            if(arrayA.none { it % bCandidates[i] == 0 })
                answer = maxOf(answer, bCandidates[i])
        }

        return answer
    }
}
