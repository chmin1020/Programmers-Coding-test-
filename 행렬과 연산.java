import java.util.*;

class Solution {
    public int[][] solution(int[][] rc, String[] operations) {
        int rowLen = rc.length;
        int colLen = rc[0].length;
        int startPoint = 0, endPoint = rowLen - 1;
        
        //중간, 양 끝 덱
        Deque<Integer>[] mats = new ArrayDeque[rowLen];
        Deque<Integer> firsts = new ArrayDeque(rowLen);
        Deque<Integer> lasts = new ArrayDeque(rowLen);
        
        //행렬 완성
        for(int i = 0; i < rowLen; i++){
            mats[i] = new ArrayDeque<Integer>(colLen - 2);
            for(int j = 0; j < colLen; j++){
                if(j == 0)
                    firsts.offerLast(rc[i][j]);
                else if(j == colLen - 1)
                    lasts.offerLast(rc[i][j]);
                else
                    mats[i].offerLast(rc[i][j]);
            }
        }
        
        // 명령어 순차 실행
        for(String op: operations){
            if(op.equals("ShiftRow")){ //shiftrow
                //시작, 끝 행 바꾸기
                startPoint = (startPoint - 1 + rowLen) % rowLen;
                endPoint = (endPoint - 1 + rowLen) % rowLen;
                
                //처음과 끝 업데이트
                firsts.offerFirst(firsts.pollLast());
                lasts.offerFirst(lasts.pollLast());
            }
            else{ //rotate
                mats[startPoint].offerFirst(firsts.pollFirst()); //좌상단 모서리
                lasts.offerFirst(mats[startPoint].pollLast()); //우상단 모서리
                mats[endPoint].offerLast(lasts.pollLast()); //우하단 모서리
                firsts.offerLast(mats[endPoint].pollFirst()); //좌하단 모서리
            }
        }
        
        //정답 행렬 만들기
        int[][] answer = new int[rowLen][colLen];
        
        for(int i = 0; i < rowLen; i++){ //처음과 끝
            answer[i][0] = firsts.pollFirst();
            answer[i][colLen - 1] = lasts.pollFirst();
        } 
        for(int i = 0; i < rowLen; i++){ //중간 부분 
            int matIdx = (startPoint + i) % rowLen;
            for(int j = 1; j < colLen - 1; j++)
                answer[i][j] = mats[matIdx].pollFirst();
        }
        
        return answer;
    }
}
