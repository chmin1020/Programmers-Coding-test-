#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int solution(vector<int> priorities, int location) {
    int answer = 0;
    int ranks[priorities.size()];
    queue<pair<int,int>> q;
    
    for(int i = 0; i < priorities.size(); i++){
        ranks[i] = priorities[i];
        q.push(make_pair(priorities[i], i));
    }
    sort(ranks, ranks + priorities.size());
    
    for(int i = priorities.size()-1; i >= 0;){
        if(q.front().first == ranks[i]){
            answer++; i--;
            if(q.front().second == location) break;
        }
        else{
            q.push(q.front());
        }
        q.pop();
    }
    
    return answer;
}
