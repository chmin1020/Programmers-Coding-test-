import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        
        // 추천인과 이익 표
        Map<String, String> parentTable = new HashMap<String, String>();
        Map<String, Integer> profitTable = new HashMap<String, Integer>();
        
        // 초기 상태 설정
        profitTable.put("-", 0);
        for(int i = 0; i < enroll.length; i++){
            parentTable.put(enroll[i], referral[i]);
            profitTable.put(enroll[i], 0);
        }
        
        // 판매 현황 갱신
        for(int i = 0; i < seller.length; i++){
            String curSeller = seller[i];
            int curAmount = amount[i] * 100;
            
            //추천인에게 이익 계속 분배
            while(parentTable.get(curSeller) != null && curAmount > 0){
                int profit = profitTable.get(curSeller);
                
                //10퍼센트 할당
                if(curAmount / 10 == 0)
                    profit += curAmount;
                else
                    profit += (curAmount - (curAmount / 10));
                
                profitTable.put(curSeller, profit);
                
                curAmount /= 10;
                curSeller = parentTable.get(curSeller);
            }
        }
        
        // 갱신 정보 answer 저장
        for(int i = 0; i < enroll.length; i++)
            answer[i] = profitTable.get(enroll[i]);
        
        return answer;
    }
}
