import java.util.*;

class Solution {
    Map<Integer, Integer> timeTable = new HashMap<Integer, Integer>();
    Map<Integer, Integer> parkTable = new HashMap<Integer, Integer>();
    PriorityQueue<ParkInfo> heap = new PriorityQueue<ParkInfo>();
    
    class ParkInfo implements Comparable<ParkInfo>{
        int num, cost;
        
        ParkInfo(int n, int c){
            num = n;
            cost = c;
        }
        
        public int compareTo(ParkInfo o){
            return this.num - o.num;
        }
    }
    
    private int timeToNum(String time){
        int hour = Integer.parseInt(time.substring(0,2));
        int minute = Integer.parseInt(time.substring(3,5));
        
        return hour * 60 + minute;
    }
    
    private void culTime(int number, int time){
        int timeSoFar = time - parkTable.get(number);
        
        if(timeTable.get(number) == null)
            timeTable.put(number, timeSoFar);
        else
            timeTable.put(number, timeTable.get(number) + timeSoFar);        
    }
    
    private void calCost(int fees[], int number, int time){
        int totalTime = time - fees[0];
        int cost = fees[1];
             
        if(totalTime > 0){
            cost += totalTime/fees[2] * fees[3];
            if(totalTime % fees[2] != 0)
                cost += fees[3];
        }
        heap.add(new ParkInfo(number, cost));
    }
    
    public int[] solution(int[] fees, String[] records) {        
        for(String each: records){
            String[] info = each.split(" ");
            int time = timeToNum(info[0]);
            int number = Integer.parseInt(info[1]);
            String direct = info[2];    
        
            if(direct.equals("IN"))
                parkTable.put(number, time);    
            else{
                culTime(number, time);
                parkTable.remove(number);
            }
        }
            
        for(int key: parkTable.keySet())
            culTime(key, 1439);

        for(int key: timeTable.keySet())
            calCost(fees, key, timeTable.get(key));
        
        int[] answer = new int[timeTable.size()];
        int idx = 0;
        while(!heap.isEmpty()){
            answer[idx++] = heap.poll().cost;
        }
        return answer;
    }
}
