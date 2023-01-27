class Solution {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var answer = 0L

        //배달과 수거 방면에서 가장 먼 집 포인터
        var deliveryPtr = deliveries.indexOfLast { it > 0 }
        var pickupPtr = pickups.indexOfLast { it > 0 }

        //배달 시뮬레이션 시작
        while(deliveryPtr >= 0 || pickupPtr >= 0){
            //최대 거리 왕복
            answer += (maxOf(deliveryPtr + 1, pickupPtr + 1) * 2)
            
            //배달 가능한 만큼
            var leftCount = cap
            while(deliveryPtr >= 0 && leftCount > 0){
                deliveries[deliveryPtr]--
                leftCount--

                while(deliveryPtr >= 0 && deliveries[deliveryPtr] == 0)
                    deliveryPtr--
            }

            //수거 가능한 만큼
            leftCount = cap
            while(pickupPtr >= 0 && leftCount > 0){
                pickups[pickupPtr]--
                leftCount--

                while(pickupPtr >= 0 && pickups[pickupPtr] == 0)
                    pickupPtr--
            }
        }

        return answer
    }
}
