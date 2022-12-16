class Solution {
    //사용 여부 체크 비트 마스크
    private var used =  0     
    //밴 배열크기
    private var banSize = 0
    //가능 조합 집합
    private val answers = HashSet<Int>()

    //dfs 탐색 함수
    private fun dfs(possibleLists: Array<MutableList<Int>>, cur: Int, idx: Int){
        used = used.or((1).shl(cur)) //비트 마스크 true 체크

        if(idx == banSize) //가능한 조합으로 추가
            answers.add(used)
        else { //새로운 원소 추가 시도
            possibleLists[idx].forEach {
                if (used.and((1).shl(it)) == 0) // 사용 안한 상태
                    dfs(possibleLists, it, idx + 1)
            }
        }
        
        used = used.and((1).shl(cur).inv()) //비트 마스크 false 체크
    }

    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        val possibleLists = Array(banned_id.size){ mutableListOf<Int>() }
        banSize = banned_id.size

        //정규표현식으로 각 ban id가 가능한 user 넣기
        banned_id.forEachIndexed{idx, ban ->
            val reg = Regex(ban.replace("*", "."))
            user_id.forEachIndexed{ idx2, id ->
                if(reg.matches(id))
                    possibleLists[idx].add(idx2)
            }
        }

        //dfs를 통한 탐색
        possibleLists[0].forEachIndexed { idx, it ->
            dfs(possibleLists, it, 1)
        }

        return answers.size
    }
}
