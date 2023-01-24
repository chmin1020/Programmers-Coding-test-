class Solution {
    fun solution(scores: Array<IntArray>): Int {
        var answer = 1
        val target = scores[0]
        val targetSum = target[0] + target[1]

        //정렬(태도 내림차순, 평가 오름차순)
        val ranks = scores.sortedWith(compareBy({-it[0]}, {it[1]} ))

        //target 찾기
        var maxEvalScore = 0
        for(i in ranks.indices){
            //인센티브 불가는 날리기
            if(ranks[i][1] < maxEvalScore) {
                if(ranks[i][0] == target[0] && ranks[i][1] == target[1])
                    return -1
                continue
            }
            
            //평가 점수 최댓값 갱신
            maxEvalScore = ranks[i][1]

            //랭크 갱신 
            if(ranks[i][0] + ranks[i][1] > targetSum)
                answer++;
        }
        return answer
    }
}
