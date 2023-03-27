class Solution {
    private var answer = Int.MAX_VALUE

    private val diamondPickaxe = mapOf( "diamond" to 1, "iron" to 1, "stone" to 1)
    private val ironPickaxe = mapOf( "diamond" to 5, "iron" to 1, "stone" to 1)
    private val stonePickaxe = mapOf( "diamond" to 25, "iron" to 5, "stone" to 1)

    fun solution(picks: IntArray, minerals: Array<String>): Int {
        doWork(picks, minerals, mutableListOf(), 0)
        return answer
    }

    //-- 재귀로 곡괭이 순서대로 선택 --//
    private fun doWork(picks: IntArray, minerals: Array<String>, selected: MutableList<Int>, curIdx: Int){
        //선택 리스트대로 일해보기
        if(curIdx >= minerals.size || picks.all { it == 0 }){
            val fatigue = countFatigue(minerals, selected)
            answer = minOf(answer, fatigue)
            return
        }

        //세 개 중 하나 선택
        for(pick in picks.indices){
            if(picks[pick] == 0) continue
            selected.add(pick)
            picks[pick]--
            doWork(picks, minerals, selected,curIdx + 5)
            picks[pick]++
            selected.removeLast()
        }
    }

    //-- 선택된 곡괭이 순으로 광물 캐기 --//
    private fun countFatigue(minerals: Array<String>, selected: List<Int>): Int{
        var fatigue = 0
        var curMineralIdx = 0
        var curLim = minOf(5, minerals.size)

        //곡괭이 다 쓸 때까지
        for(curPickaxe in selected){
            //5개 채광
            for(i in curMineralIdx until curLim){
                fatigue += when(curPickaxe){
                    0 -> diamondPickaxe[minerals[i]] ?: 0
                    1 -> ironPickaxe[minerals[i]] ?: 0
                    else -> stonePickaxe[minerals[i]] ?: 0
                }
            }

            //미네랄 다 캠
            if(curLim >= minerals.size)
                break

            curMineralIdx = curLim
            curLim = minOf(curLim + 5, minerals.size)
        }
        return fatigue
    }
}
