class Solution {
    fun solution(n: Int, a: Int, b: Int): Int {
        var answer = 0

        //a가 무조건 작게 조정
        var an = a
        var bn = b
        if(an > bn) an = bn.also { bn = an }
        
        //같은 경기를 뛸 때까지 돌리기
        while(an != bn){
            an = (an + 1) / 2
            bn = (bn + 1) / 2
            answer++
        }
        return answer
    }
}
