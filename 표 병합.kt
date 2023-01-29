const val EMPTY_VALUE = "EMPTY"
const val NOT_MERGE_ID = -1

class Solution {
    private data class Pos(val x: Int, val y: Int)
    private data class EachInfo(var value: String, var mergeId: Int)

    //병합 아이디 할당 변수
    private var allocatedMergeId = 0

    //값에 따른 칸 검색 맵
    private val searchMap = mutableMapOf<String, MutableSet<Pos>>()

    //병합 세트 검색 맵
    private val mergeMap = mutableMapOf<Int, MutableSet<Pos>>()

    //진짜 값들 테이블
    private val table = Array(51){Array(51){EachInfo(EMPTY_VALUE, NOT_MERGE_ID)}}

    //프린트 결과 값들
    private val printResults = mutableListOf<String>()


    //--최종 함수--
    fun solution(commands: Array<String>): Array<String> {
        commands.forEach {
            //명령어 분석
            val elements = it.split(" ").toTypedArray()

            //분석에 따른 행동 수행
            when(elements[0]){
                "UPDATE" -> {
                    if(elements.size == 4) updateOne(elements[1].toInt(), elements[2].toInt(), elements[3])
                    else updateAll(elements[1], elements[2])
                }
                "MERGE" -> merge(elements[1].toInt(),elements[2].toInt(), elements[3].toInt(), elements[4].toInt())
                "UNMERGE" -> unMerge(elements[1].toInt(), elements[2].toInt())
                "PRINT" -> print(elements[1].toInt(), elements[2].toInt())
            }
        }

        return printResults.toTypedArray()
    }


    //----------------------------
    //각 명령어 함수들

    //--값 업데이트 함수--
    private fun updateWork(pos: Pos, originalValue: String, newValue: String){
        searchMap[originalValue]?.remove(pos)
        searchMap[newValue]?.add(pos) ?: run{ searchMap[newValue] = mutableSetOf(pos)}
        table[pos.x][pos.y].value = newValue
    }

    //--UPDATE r c value--
    private fun updateOne(r: Int, c: Int, value: String){
        if(table[r][c].mergeId != NOT_MERGE_ID)  //병합 상태일 시 병합 셀 값 모두 변경
            mergeMap[table[r][c].mergeId]?.forEach { updateWork(it, table[it.x][it.y].value, value) }
        else //아니면 그 값만 변경
            updateWork(Pos(r, c), table[r][c].value, value)
    }

    //--UPDATE value1 value2--
    private fun updateAll(value1: String, value2: String){
        if(value1 == value2)
            return
        
        //기존 값 가진 모든 셀 갱신
        val updateList = searchMap[value1]?.toList()
        updateList?.forEach { updateOne(it.x, it.y, value2) }
        searchMap.remove(value1)
    }

    //--MERGE r1 c1 r2 c2--
    private fun merge(r1: Int, c1: Int, r2: Int, c2: Int){
        //셀 병합 id
        val firstMergeID = table[r1][c1].mergeId
        val secondMergeID = table[r2][c2].mergeId

        //같은 셀이면 무시
        if(r1 == r2 && c1 == c2)
            return
        if(firstMergeID != NOT_MERGE_ID && firstMergeID == secondMergeID)
            return

        //값 통일 작업
        if(table[r1][c1].value != table[r2][c2].value) {
            if (table[r1][c1].value != EMPTY_VALUE)
                updateOne(r2, c2, table[r1][c1].value)
            else if (table[r2][c2].value != EMPTY_VALUE)
                updateOne(r1, c1, table[r2][c2].value)
        }

        //셀 병합 작업
        if(firstMergeID == NOT_MERGE_ID && secondMergeID == NOT_MERGE_ID){ //둘 다 안 병합 상태
            //새 아이디로 통합
            val newId = allocatedMergeId++

            table[r1][c1].mergeId = newId
            table[r2][c2].mergeId = newId
            mergeMap[newId] = mutableSetOf(Pos(r1, c1), Pos(r2, c2))
        }
        else if(secondMergeID == NOT_MERGE_ID){ //1만 병합 상태
            //2를 1에 통합
            table[r2][c2].mergeId = firstMergeID
            mergeMap[firstMergeID]?.add(Pos(r2, c2))
        }
        else if(firstMergeID == NOT_MERGE_ID){ //2만 병합 상태
            //1을 2에 통합
            table[r1][c1].mergeId = secondMergeID
            mergeMap[secondMergeID]?.add(Pos(r1, c1))
        }
        else{ //둘 다 병합 상태
            //2번 병합셋 아이디 바꾸기
            mergeMap[secondMergeID]?.forEach { table[it.x][it.y].mergeId = firstMergeID }

            //1번 병합셋으로 통합 후 병합된 아이디 삭제
            mergeMap[firstMergeID]?.addAll(mergeMap[secondMergeID] ?: setOf())
            mergeMap.remove(secondMergeID)
        }
    }

    //--UNMERGE r c--
    private fun unMerge(r: Int, c: Int){
        val mergeId = table[r][c].mergeId
        val mergeValue = table[r][c].value

        //병합된 각 셀들 분리 적용
        mergeMap[mergeId]?.forEach {
            table[it.x][it.y].mergeId = NOT_MERGE_ID

            //r, c가 아니면 empty
            if(it.x != r || it.y != c) {
                searchMap[mergeValue]?.remove(it)
                table[it.x][it.y].value = EMPTY_VALUE
            }
        }
        mergeMap.remove(mergeId)
    }

    //--PRINT r c--
    private fun print(r: Int, c: Int){
        val value =  table[r][c].value
        printResults.add(value)
    }
}
