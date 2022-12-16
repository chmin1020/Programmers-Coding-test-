class Solution {
    var answer = 0

    private fun dfs(numbers: IntArray, target: Int, cur: Int, idx: Int){
        //총합이 target과 같으면
        if(idx == numbers.size) {
            if(cur == target) answer++
            return
        }

        dfs(numbers, target, cur + numbers[idx], idx + 1)
        dfs(numbers, target, cur - numbers[idx], idx + 1)
    }

    fun solution(numbers: IntArray, target: Int): Int {
        dfs(numbers, target, numbers[0], 1)
        dfs(numbers, target, -numbers[0], 1)

        return answer
    }
}
