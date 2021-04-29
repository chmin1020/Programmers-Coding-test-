#include <string>
#include <vector>
#include <queue>
#include <functional>

using namespace std;

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> answer;
    priority_queue<int, vector<int>, greater<int>> q;

    for(int i = 0; i < commands.size(); i++){
        for(int start = commands[i][0]-1 ; start < commands[i][1]; start++)
            q.push(array[start]);
        for(int find =0 ; find < commands[i][2]-1; find++)
            q.pop();
        answer.push_back(q.top());
        while(!q.empty())
            q.pop();
    }
    return answer;
}
