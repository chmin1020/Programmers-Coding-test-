#include <string>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int solution(string name) {
	int answer = 0;
	int cur, cnt;
	int up, down;
	vector<bool> complete((int)name.length(), false);
	queue<pair<int, int>> q; //index, cnt

	q.push(make_pair(0, 0));
	complete[0] = true;
	while (!q.empty()) {
		up = down = cur = q.front().first;
		cnt = q.front().second;
		
		answer += cnt;
		answer += min(name[cur] - 'A', 'Z' + 1 - name[cur]);
		
		for (int i = 1; i <= (int)name.length() / 2; i++) {
			up++; down--;
			if (down < 0) down = (int)name.length() + down;
			
			if (up < (int)name.length() && name[up] != 'A' && !complete[up]) {
				q.push(make_pair(up, i));
				complete[up] = true; break;
			}
			if (name[down] != 'A' && !complete[down]) {
				q.push(make_pair(down, i));
				complete[down] = true; break;
			}
		}
		q.pop();
	}
	return answer;
}
