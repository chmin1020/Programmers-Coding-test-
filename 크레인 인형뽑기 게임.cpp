#include <string>
#include <vector>
#include <stack>

using namespace std;

int solution(vector<vector<int>> board, vector<int> moves) {
    int answer = 0, tmp;
    vector<int> cnt(board[0].size(), 0);
    stack<int> bracket;

    for (int i = 0; i < moves.size(); i++) {
        for (; cnt[moves[i] - 1] < cnt.size(); cnt[moves[i] - 1]++)
            if (board[cnt[moves[i] - 1]][moves[i] - 1] != 0) break;

        (cnt[moves[i] - 1] >= cnt.size()) ? tmp = 0 : tmp = board[cnt[moves[i] - 1]][moves[i] - 1];
        cnt[moves[i] - 1]++;

        if (tmp == 0) continue;
        else {
            if (!bracket.empty() && tmp == bracket.top()) {
                bracket.pop();
                answer+=2;
            }
            else
                bracket.push(tmp);
        }
    }
    return answer;
}
