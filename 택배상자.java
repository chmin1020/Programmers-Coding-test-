import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        int cur = 1;
        int tmp  = 0;
        boolean[] used = new boolean[order.length + 1];
        
        for(answer = 0; answer < order.length; answer++){
            // order보다 작으면 보조 컨테이너 벨트로 옮기고 목표 빼기
            if(cur < order[answer]){
                tmp = order[answer] - 1;
                cur = order[answer] + 1;
            }
            // order과 같으면 바로 목표 빼기
            else if(cur == order[answer]){
                cur = order[answer] + 1;
            }
            // order보다 크면 임시 벨트 확인하기
            else{
                if(tmp != order[answer]) //다르면 끝
                    break;
                else{ //같으면 임시 벨트 갱신
                    tmp--;
                    while(used[tmp])
                        tmp--;
                }
            }
            
            used[order[answer]] = true;
        }
        return answer;
    }
}
