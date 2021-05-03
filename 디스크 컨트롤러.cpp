#include <cstring>
#include <string>
#include <vector>
#include <queue>
using namespace std;
typedef pair<int,int> ii;

int solution(vector<vector<int>> jobs) {
	int answer = 0, cur = 0, pointer = 0;
    int entry_time[1001];
	priority_queue<ii> heap;
	queue<ii> tmp;

    memset(entry_time, 0, sizeof(entry_time));
	for (int i = 0; i < jobs.size(); i++){
		heap.push(make_pair(-jobs[i][1], jobs[i][0]));
        entry_time[jobs[i][0]]++;
    }
    
	while (!heap.empty()) {
        while(entry_time[pointer] <= 0) pointer++;
        if(cur < pointer) cur = pointer;
        
		if (cur < heap.top().second) {
			tmp.push(heap.top());
			heap.pop();
		}
		else {
			cur -= heap.top().first;
            answer += (cur - heap.top().second);
			entry_time[heap.top().second]--;
            heap.pop();

			while (!tmp.empty()) {
				heap.push(tmp.front());
				tmp.pop();
			}
		}
	}
	answer /= jobs.size();
	return answer;
}
