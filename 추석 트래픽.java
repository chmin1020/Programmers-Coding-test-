class Solution {
    public int getTime(String str){
        int res = 0;
        String[] each = str.split(":");
        
        res += Integer.parseInt(each[0]) * 3600000;
        res += Integer.parseInt(each[1]) * 60000;
        res += (int)(Double.parseDouble(each[2]) * 1000);
        
        return res;
    }
    public int solution(String[] lines) {
        int answer = 0;
        int[] start = new int[lines.length];
        int[] end = new int[lines.length];
        
        for(int i = 0; i < lines.length; i++){
            String[] nst = lines[i].split(" ");
            end[i] = getTime(nst[1]);
            start[i] = end[i] - (int)(Double.parseDouble(nst[2].replace("s", "")) * 1000) + 1;
        }
        
        for(int i = 0; i < lines.length; i++){
            int tp = 0;
            for(int j = i; j < lines.length;j++)
                if(start[j] - 1000 < end[i])
                    tp++;
            if(tp > answer) answer = tp;
        }
        
        return answer;
    }
}
