import java.util.*;

class Solution {
    Map<String, ArrayList<Integer>> db = new HashMap<String, ArrayList<Integer>>();
    
     public int lowerBound(ArrayList<Integer> list, int score) {
        int left = 0, right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < score)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return left;
    }
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        String[] langs = {"-", "cpp", "java", "python"};
        String[] jobs = {"-", "backend", "frontend"};
        String[] careers = {"-", "junior", "senior"};
        String[] foods = {"-", "chicken", "pizza"};
        
        Arrays.fill(answer, 0);        

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                for(int m = 0; m < 3; m++){
                    for(int n = 0; n < 3; n++){
                        String key = langs[i] + jobs[j] + careers[m] + foods[n];
                        db.put(key, new ArrayList<Integer>());
                    }
                }
            }
        }
        
        for(String str: info){
            String[] each = str.split(" ");
            String[] tpL = {"-", each[0]};
            String[] tpJ = {"-", each[1]};
            String[] tpC = {"-", each[2]};
            String[] tpF = {"-", each[3]};
            int value = Integer.parseInt(each[4]);
            
            for(int i = 0; i < 2; i++){
                for(int j = 0; j < 2; j++){
                    for(int m = 0; m < 2; m++){
                        for(int n = 0; n < 2; n++){
                            String key = tpL[i] + tpJ[j] + tpC[m] + tpF[n];
                            db.get(key).add(value);
                        }
                    }
                }
            }
        }
  
        for(String key: db.keySet())
            Collections.sort(db.get(key));
        
        int idx = 0;
        for(String str: query){
            String[] each = str.split(" ");
            int score = Integer.parseInt(each[7]);
            String key = each[0] + each[2] + each[4] + each[6];

                            
            ArrayList<Integer> list = db.get(key);
            int start = lowerBound(list, score);
            answer[idx++] += (list.size() - start);
        }
        return answer;
    }
}
