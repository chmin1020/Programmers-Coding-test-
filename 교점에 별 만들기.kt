import java.lang.StringBuilder

class Solution {

    //정수 교점 구하는 함수
    private fun getCrossPoint(pos1: IntArray, pos2: IntArray): IntArray?{
        val xTop = pos1[1].toLong() * pos2[2] - pos1[2].toLong() * pos2[1]
        val xBottom = pos1[0].toLong() * pos2[1] - pos1[1].toLong() * pos2[0]
        val yTop = pos1[2].toLong() * pos2[0] - pos1[0].toLong() * pos2[2]
        val yBottom = pos1[0].toLong() * pos2[1] - pos1[1].toLong() * pos2[0]

        if(xBottom == 0L || yBottom == 0L)
            return null
        else{
            val x = xTop / xBottom
            val y = yTop / yBottom

            if(xTop % xBottom != 0L || yTop % yBottom != 0L)
                return null
            return intArrayOf(x.toInt(), y.toInt())
        }
    }

    fun solution(line: Array<IntArray>): Array<String> {
        val crosses = mutableListOf<IntArray>()

        //모든 선 조합 탐색
        for(i in line.indices){
            for(j in i + 1 until line.size){
                val cross = getCrossPoint(line[i], line[j])
                cross?.let { crosses.add(it) }
            }
        }

        //y 내림차순, x 오름차순으로 정렬
        val ansList = crosses.sortedWith(compareBy ({ -it[1] }, { it[0] }))

        //그릴 좌표 범위 구하기
        val xMin = crosses.minOf{ it[0] }
        val xMax = crosses.maxOf{ it[0] }
        val yMin = crosses.minOf{ it[1] }
        val yMax = crosses.maxOf{ it[1] }

        //별 찍기
        val beforeAnswer = Array(yMax - yMin + 1){CharArray(xMax - xMin + 1){'.'} }
        ansList.forEach{ beforeAnswer[yMax - it[1]][it[0] - xMin] = '*' }

        val answer = Array(yMax - yMin + 1){""}

        answer.forEachIndexed{ idx, it ->
            val sb = StringBuilder()
            beforeAnswer[idx].forEach { c -> sb.append(c) }
            answer[idx] = sb.toString()
        }

        return answer
    }
}
