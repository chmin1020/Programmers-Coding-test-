class Solution {
    //빈 방 선택 시 줄 적절한 방 map
    private val properRoom = mutableMapOf<Long, Long>()

    //--방 찾기 함수--
    private fun roomFind(num: Long): Long {
        properRoom[num] ?: return num
        properRoom[num]?.let { properRoom[num] = roomFind(it) }
        return properRoom[num] ?: num
    }
    
    fun solution(k: Long, room_number: LongArray): LongArray {
        val answer = mutableListOf<Long>()
        
        //각 방 배정 작업
        room_number.forEach {
            val good = roomFind(it)
            answer.add(good)
            properRoom[good] = roomFind(good + 1)
        }

        return answer.toLongArray()
    }
}
