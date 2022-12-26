
class Solution {
    //콜라츠 작업
    private fun collatzCalc(n: Int): Int = if(n % 2 == 0) n/2 else n*3 + 1

    //사다리꼴 넓이 계산
    private fun getExtent(a: Int, b: Int): Double =(a + b) / 2.0

    //구간을 구하기
    private fun getRealRange(range: IntArray, end: Int): IntArray = intArrayOf(range[0], end + range[1] - 1)

    fun solution(k: Int, ranges: Array<IntArray>): DoubleArray {
        val answer = mutableListOf<Double>()

        //실제 수열을 계산하여 그래프 결과 수열 완성
        fun drawGraph(): IntArray{
            var num = k
            val progressList = mutableListOf<Int>()
            while(num != 1){
                progressList.add(num)
                num = collatzCalc(num)
            }
            progressList.add(1)
            return progressList.toIntArray()
        }
        val graph = drawGraph()

        //구간에 따른 전체 넓이 구하기
        fun doIntegral(range: IntArray): Double{
            var res = 0.0
            if(range[0] > range[1]) res = -1.0
            else
                for(i in range[0] until range[1])
                        res += getExtent(graph[i], graph[i + 1])
            return res
        }

        ranges.forEach { answer.add(doIntegral(getRealRange(it, graph.size))) }
        return answer.toDoubleArray()
    }
}
