import java.util.*;

class Solution {
    //String 시간 정보를 int로
    private int calTime(String time){
        String[] info = time.split(":");

        int hour = Integer.parseInt(info[0]);
        int minute = Integer.parseInt(info[1]);
        int second = Integer.parseInt(info[2]);
        
        return (hour * 3600 + minute * 60 + second);
    }
    
    //int 시간 정보를 String으로
    private String backTime(int time){
        String hour = Integer.toString(time / 3600);
        if(hour.length() == 1)
            hour = "0" + hour;
        
        time %= 3600;
        String minute = Integer.toString(time / 60);
        if(minute.length() == 1)
            minute = "0" + minute;
        
        time %= 60;
        String second = Integer.toString(time);
        if(second.length() == 1)
            second = "0" + second;
        
        return (hour + ":" + minute + ":" + second);
    }
    
    public String solution(String play_time, String adv_time, String[] logs) {
        // 영상 시간 계산
        int playLen = calTime(play_time);
        int advLen = calTime(adv_time);
                
        //timetable에 시청 기록 채우기
        int[] timeTable = new int[playLen + 1];
        Arrays.fill(timeTable, 0);

        for(String log: logs){
            String[] points = log.split("-");
            timeTable[calTime(points[0])]++;
            timeTable[calTime(points[1])]--;
        }
        for(int i = 1; i < playLen; i++)
            timeTable[i] += timeTable[i - 1];
        
        //누적 합으로 만들기
        for(int i = 1; i < playLen; i++)
            timeTable[i] += timeTable[i - 1];
        
        //판별하기
        int good = 0;
        int curMax = timeTable[advLen - 1];
        
        for(int i = advLen; i < playLen; i++){
            int cul = timeTable[i] - timeTable[i - (advLen - 1) - 1];
            if(cul > curMax){
                good = i - (advLen - 1);
                curMax = cul;
            }
        }
        
        return backTime(good);
    }
}
