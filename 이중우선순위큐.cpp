#include <string>
#include <vector>
#include <queue>
#include <functional>
#include <unordered_map>
using namespace std;

vector<int> solution(vector<string> operations) {
	vector<int> answer;
	unordered_map<int, int> mp;
	priority_queue<int> maxQ;
	priority_queue<int, vector<int>, greater<int>> minQ;
	int data;

	for (int i = 0; i < operations.size(); i++) {
		data = atoi(operations[i].substr(2, operations[i].size() - 2).c_str());
	
		if (operations[i][0] == 'I') {
			if (mp.find(data) != mp.end())  mp[data]++;
			else  mp.insert(make_pair(data, 1));

			maxQ.push(data);
			minQ.push(data);
		}
		else if (operations[i][0] == 'D') {
			if (maxQ.empty()) continue;
	
			if (data == 1)  mp[maxQ.top()]--;
			else  mp[minQ.top()]--;

			while (!maxQ.empty() && mp[maxQ.top()] <= 0) maxQ.pop();
			while (!minQ.empty() && mp[minQ.top()] <= 0) minQ.pop();
		}
	}

	if (maxQ.empty()) answer.push_back(0);
	else answer.push_back(maxQ.top());

	if (minQ.empty()) answer.push_back(0);
	else answer.push_back(minQ.top());

	return answer;
}
