class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] mat = new int[rows][columns];
        
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < columns; j++)
                mat[i][j] = i * columns + (j + 1);
    
        int idx = 0;
        for(int[] q: queries){
            // 회전 범위 설정 
            int row_low = q[0] - 1;
            int col_low = q[1] - 1;
            int row_high = q[2] - 1;
            int col_high = q[3] - 1;
            
            // min 찾기
            int min = 20000000;
            for(int i = row_low; i <= row_high; i++)
                for(int j = col_low; j <= col_high; j++)
                    if((i == row_low || i == row_high || j == col_low || j == col_high))
                        min = Math.min(min, mat[i][j]);
            answer[idx++] = min;
            
            // 모퉁이 값 미리 저장 
            int lu_corner = mat[row_low + 1][col_low];
            int ld_corner = mat[row_high][col_low + 1];
            int ru_corner = mat[row_low][col_high - 1];
            int rd_corner = mat[row_high - 1][col_high];
            
            //회전 
            for(int i = col_high - 1; i > col_low; i--)
                mat[row_low][i] = mat[row_low][i - 1];
            
            for(int i = row_high - 1; i > row_low; i--)
                mat[i][col_high] = mat[i - 1][col_high];
            
            for(int i = col_low + 1; i < col_high; i++)
                mat[row_high][i] = mat[row_high][i + 1];
            
            for(int i = row_low + 1; i < row_high; i++)
                mat[i][col_low] = mat[i + 1][col_low];

            mat[row_low][col_low] = lu_corner;
            mat[row_high][col_low] = ld_corner;
            mat[row_low][col_high] = ru_corner;
            mat[row_high][col_high] = rd_corner;
        }
        
        return answer;
    }
}
