class Solution {
    fun solution(wallpaper: Array<String>): IntArray {
        var minX = Int.MAX_VALUE
        var minY = Int.MAX_VALUE
        var maxX = 0
        var maxY = 0

        //완전 탐색
        wallpaper.forEachIndexed { x, row ->
            row.forEachIndexed{ y, mark ->
                if(mark == '#'){
                    minX = minOf(minX, x)
                    minY = minOf(minY, y)
                    maxX = maxOf(maxX, x + 1)
                    maxY = maxOf(maxY, y + 1)
                }
            }
        }

        //답 생성
        return intArrayOf(minX, minY, maxX, maxY)
    }
}
