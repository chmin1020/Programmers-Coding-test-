import kotlin.math.*

class Solution {
    private val existNum = mutableSetOf<Int>()
    private val numCount = intArrayOf(0, 0, 0, 0, 0, 0, 0)
    
    fun solution(a: Int, b: Int, c: Int, d: Int): Int {
        performPreProcess(a)
        performPreProcess(b)
        performPreProcess(c)
        performPreProcess(d)
        
        return when(existNum.size){
            4 -> existNum.minByOrNull{it} ?: 0
            3 -> existNum.filter{numCount[it] == 1}.fold(1){res, it -> res * it}
            2 -> {
                val three = existNum.firstOrNull{numCount[it] == 3}
                if(three != null){
                //3개 1개
                    (10.0 * three + existNum.first{numCount[it] == 1}).pow(2).toInt()   
                }
                else{
                //2개 2개
                    val minN = existNum.minByOrNull{it} ?: 0
                    val maxN = existNum.maxByOrNull{it} ?: 0
                    (minN + maxN) * (maxN - minN)  
                }                
            }
            1 -> 1111 * existNum.first()
            else -> -1
        }
    }
    
    private fun performPreProcess(n: Int){
        numCount[n]++
        existNum.add(n)
    }
}
