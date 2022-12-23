class Solution {
    fun solution(n: Int): Array<IntArray> {
        val ansList = mutableListOf<IntArray>()
        
        //하노이 진행 과정 (재귀)
        fun hanoi(num: Int, from:Int, via:Int, to:Int){
            if(num == 1)
                ansList.add(intArrayOf(from, to))
            else {
                hanoi(num - 1, from, to, via)
                ansList.add(intArrayOf(from, to))
                hanoi(num - 1, via, from, to)
            }
        }

        hanoi(n, 1, 2, 3)
        return ansList.toTypedArray()
    }
}
