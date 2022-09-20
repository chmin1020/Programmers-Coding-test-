import java.util.*; 

class Solution {
    private String toStringForTime(int time){
        int hour = time / 60;
        int minute = time % 60;
                
        String hourToString = Integer.toString(hour);
        if(hour < 10)
            hourToString = "0" + hourToString;
                
        String minuteToString = Integer.toString(minute);
        if(minute < 10)
            minuteToString = "0" + minuteToString;
                
        return (hourToString + ":" + minuteToString);
    }
    
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        int[] crewTimes = new int[timetable.length];
        
        for(int i = 0; i < timetable.length; i++){
            String[] timeInfo = timetable[i].split(":");
            crewTimes[i] = Integer.parseInt(timeInfo[0]) * 60 + Integer.parseInt(timeInfo[1]); 
        }
        Arrays.sort(crewTimes);
        
        int lastTime = 540;
        
        int curIdx = 0;
        int cnt = 0;
            
        for(int i = 0; i < n; i++){
            cnt = 0;
            
            while(curIdx < crewTimes.length && crewTimes[curIdx] <= lastTime && cnt < m){
                cnt++;
                curIdx++;
            }
            lastTime += t;
        }
        lastTime -= t;
            
        if(cnt == m)
            answer = toStringForTime(crewTimes[curIdx - 1] - 1);
        else
            answer = toStringForTime(lastTime);
   
        return answer;
    }
}
