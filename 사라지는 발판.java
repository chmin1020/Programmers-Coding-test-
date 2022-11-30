import java.util.*;

class Solution {
    private int ans = 0;
    private int xlen, ylen;
    
    //정보 저장 객체
    private class AnsPair{
        boolean isWin;
        int turns;
        
        AnsPair(boolean isWin, int turns){
            this.isWin = isWin;
            this.turns = turns;
        }
    }
    
    //이동 배열
    private int[] directX = {-1, 1, 0, 0};
    private int[] directY = {0, 0, -1, 1};
    
    //게임 속 agent의 한 step
    private AnsPair step(int[][] board, int[] nowloc, int[] fixloc, int cnt){
        //못 움직이는 상황이면 끝
        if(cantMove(board, nowloc[0], nowloc[1]))
            return new AnsPair(false, cnt);
        //두 agent가 같은 공간에 있으면 다음 턴에 끝
        if(nowloc[0] == fixloc[0] && nowloc[1] == fixloc[1])
            return new AnsPair(true, cnt + 1);
        
        boolean isWin = false;
        int min = Integer.MAX_VALUE;
        int max = 0;
        
        //4방향 나아가기 검사
        board[nowloc[0]][nowloc[1]] = 0;       
        for(int i = 0; i < 4; i++){
            int newX = nowloc[0] + directX[i];
            int newY = nowloc[1] + directY[i];
            int[] newloc = {newX, newY};
        
            //나아갈 수 있는 경우에 나아간다.
            if(isInRange(newX, newY) && board[newX][newY] == 1){
                AnsPair cur = step(board, fixloc, newloc, cnt + 1);
                
                if(!cur.isWin){ //본인 승리 -> 최대한 빨리
                    isWin = true;
                    min = Math.min(min, cur.turns);
                }
                else if(!isWin) //본인 패배 -> 최대한 늦게
                    max = Math.max(max, cur.turns);
              }
        } 
        board[nowloc[0]][nowloc[1]] = 1;
        
        return new AnsPair(isWin, (isWin)?min:max);
    }
    
    //범위를 벗어나지 않았는지 체크
    private boolean isInRange(int x, int y){
        return (x >= 0 && x < xlen && y >= 0 && y < ylen);
    }
    
    //움직이지 못하는 상황인지 체크
    private boolean cantMove(int[][] board, int x, int y){
        for(int i = 0; i < 4; i++){
            int newX = x + directX[i];
            int newY = y + directY[i];
            
            //한 곳이라도 갈 곳이 있으면
            if(isInRange(newX, newY) && board[newX][newY] == 1)
                return false;
        }
        return true;
    }
 
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        //범위 지정
        xlen = board.length;
        ylen = board[0].length;
        
        //게임 simulate
        return step(board, aloc, bloc, 0).turns;
    }
}
