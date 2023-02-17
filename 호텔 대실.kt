import java.util.*

//시간 범위 데이터 클래스
data class TimeRange(val startTime: Int, val endTime: Int): Comparable<TimeRange>{
    override fun compareTo(other: TimeRange) = this.endTime - other.endTime
}

class Solution {
    fun solution(book_time: Array<Array<String>>): Int {
        //사용 중인 방 현황과 남은 방 개수
        val usingRooms = PriorityQueue<TimeRange>()
        var roomCnt = 0

        //모든 예약에 대한 연산
        book_time.sortBy { it[0] }
        book_time.forEach {
            //새로 방을 할당할 예약
            val curTimeRange = TimeRange(startTimeMaking(it[0]), endTimeMaking(it[1]))

            //이 시각 사용 가능한 방 체크
            while(usingRooms.isNotEmpty() && usingRooms.peek().endTime <= curTimeRange.startTime)
                usingRooms.poll()

            //사용 가능한 방이 없으면 방 하나 더 추가
            if(roomCnt == usingRooms.size) roomCnt++
            usingRooms.add(curTimeRange)
        }

        //남은 빈 방 + 현재 사용 중인 방
        return roomCnt
    }

    //--각 시간을 정수형으로 만들기(시작 시간, 종료 시간)--..
    private fun startTimeMaking(str: String) = str.substring(0 until  2).toInt() * 100 + str.substring(3).toInt()
    private fun endTimeMaking(str: String): Int{
        var hour = str.substring(0 until 2).toInt()
        var minute = str.substring(3).toInt() + 10

        if(minute >= 60){
            hour++
            minute %= 60
        }
        return hour * 100 + minute
    }
}
