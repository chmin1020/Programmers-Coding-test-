#include <queue>
#include <vector>
using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    queue<int> index;
    int cnt = 0;
    
    for(int i =0; i< speeds.size(); i++)
        index.push(i);
    
    while(!index.empty()){
        for(int i = index.front(); i < speeds.size(); i++)
            progresses[i]+=speeds[i];
        if(progresses[index.front()] >= 100){
            answer.push_back(0);
            while(!index.empty() && progresses[index.front()] >= 100){
                answer[cnt]++; index.pop();
            }
            cnt++;
        }
    }
    return answer;
}
