class Solution {
    private data class UserInfo(val wantRate: Int, val limitPrice: Int, var curPrice: Int)

    //유저 정보 저장
    private val userInfoArr = mutableListOf<UserInfo>()

    //답 가능성 정보 저장
    private var maxMemberCnt = 0
    private var maxPrice = 0

    private fun dfs(idx: Int, emoticons: IntArray){
        //각 이모티콘을 할인율 범위 안에서 세일 시도
        for(rate in 10 .. 40 step(10)){
            //세일 했을 때 이모티콘 가격
            val eachPrice = (emoticons[idx] * (100 - rate)) / 100

            //현재할인율 이하인 사람은 구매
            userInfoArr.forEach {
                if(it.wantRate <= rate)
                    it.curPrice += eachPrice
            }
           
            //만약 마지막 이모티콘이었으면 최종 합산 시도
            if(idx == emoticons.lastIndex) {
                val memberCnt = userInfoArr.filter{ it.limitPrice <= it.curPrice }.count()
                val totalPrice = userInfoArr.filter { it.limitPrice > it.curPrice }.sumBy { it.curPrice }

                if(maxMemberCnt < memberCnt) { //더 큰거 나옴 -> 가입자수와 수익 모두 갱신
                    maxMemberCnt = memberCnt
                    maxPrice = totalPrice
                }
                else if(maxMemberCnt == memberCnt) {//같음 -> 가격만 갱신
                    maxPrice = maxOf(maxPrice, totalPrice)
                }
            }
            else //아니면 다음 이모티콘 확인하러
                dfs(idx + 1, emoticons)

            //다음 할인율 체크를 위해 현재할인율 가격 빼기
            userInfoArr.forEach {
                if(it.wantRate <= rate)
                    it.curPrice -= eachPrice
            }
        }
    }
    
    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        userInfoArr.addAll(users.map { UserInfo(wantRate = it[0], limitPrice = it[1], curPrice = 0) })

        //최적의 할인율 셋 탐색
        dfs(0, emoticons)

        return intArrayOf(maxMemberCnt, maxPrice)
    }
}
