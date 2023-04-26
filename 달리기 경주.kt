class Solution {
    private val rankMap = mutableMapOf<String, Int>()
    
    fun solution(players: Array<String>, callings: Array<String>): Array<String> {
        //최초 랭크 등록
        players.forEachIndexed{ idx, name ->
            rankMap[name] = idx
        }    
        
        //순위 바뀌기 과정
        callings.forEach{ backName ->
            val frontName = players[(rankMap[backName] ?: 0) - 1]
            
            rankMap[frontName] = (rankMap[frontName] ?: 0) + 1
            rankMap[backName] = (rankMap[backName] ?: 0) - 1
            players[rankMap[frontName] ?: 0] = frontName
            players[rankMap[backName] ?: 0] = backName
        }
        
        return players
    }
}
