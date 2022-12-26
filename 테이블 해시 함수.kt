class Solution {
    //정렬을 위한 row 데이터 클래스
    private data class RowInfo (val col: Int, val row: IntArray): Comparable<RowInfo>{
        override fun compareTo(other: RowInfo): Int {
            if(this.row[col] == other.row[col])
                return other.row[0] - this.row[0]
            return this.row[col] - other.row[col]
        }
    }
    
    fun solution(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int): Int {
        //정렬
        val sortedRows = data.map { RowInfo(col - 1, it) }.sorted().toTypedArray()

        //하나씩 si 만들어서 xor
        var answer = sortedRows[row_begin - 1].row.fold(0) { tot, it -> tot + (it % row_begin) } 
        for(i in row_begin until row_end){
            val si = sortedRows[i].row.fold(0) { tot, it -> tot + (it % (i + 1)) }
            answer = answer.xor(si)
        }
        return answer
    }
}
