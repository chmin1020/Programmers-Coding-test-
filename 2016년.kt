class Solution {
    fun solution(a: Int, b: Int): String {
        var days = 0
        
        for(i in 1 until a){
            if(i==2)
                days += 29
            else if((i <= 7 && i % 2 == 1) || (i >= 8 && i %2 == 0))
                days += 31
            else
                days += 30
        }
        days += (b - 1)
        
        if(days%7 == 0) return "FRI"
        else if(days%7 == 1) return "SAT"
        else if(days%7 == 2) return "SUN"
        else if(days%7 == 3) return "MON"
        else if(days%7 == 4) return "TUE"
        else if(days%7 == 5) return "WED"
        else return "THU"
    }
}
