class Solution {
    fun solution(number: IntArray): Int {
        var answer: Int = 0

        //--dfs 통한 모든 경우의 수 체크
        fun dfs(cnt: Int, sum: Int, cur: Int){
            if(cnt == 3){ //세 명일 때 합이 0이면 answer + 1
                if(sum == 0) answer++
                return
            }

            for(i in cur + 1 until number.size)
                dfs(cnt + 1, sum + number[i], i)
        }
        
        dfs(0, 0, -1)
        return answer
    }
}
