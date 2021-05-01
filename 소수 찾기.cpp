#include <string>
#include <vector>
using namespace std;

bool visited[7];
bool che[10000001];
int maxn = -1;

//에라토스테네스의 체 만들기
void makeChe(int size) {
	che[0] = che[1] = true;

	for (int i = 2; i <= size / 2; i++) {
		if (che[i]) continue;
		else 
			for (int j = i * 2; j <= size; j += i)
				che[j] = true;
	}
}
//중복 체크
bool redundantCheck(vector<int> v, int t) {
	for (int i = 0; i < v.size(); i++)
		if (v[i] == t) return true;
	return false;
}
//백트래킹
void makeNum(vector<int> *v, vector<int> digits, int cur) {
	if (redundantCheck(*v, cur)) return;
	v->push_back(cur);
	if (maxn < cur) maxn = cur;

	for (int i = 0; i < digits.size(); i++) {
		if (!visited[i]) {
			visited[i] = true;
			makeNum(v, digits, cur * 10 + digits[i]);
			visited[i] = false;
		}
	}
}

int solution(string numbers) {
	vector<int> digits(numbers.length());
	vector<int> allNum;
	int answer = 0;

	//store digits
	for (int i = 0; i < numbers.length(); i++)
		digits[i] = numbers[i] - '0';

	//find num list and make che
	makeNum(&allNum, digits, 0);
	makeChe(maxn);

	//search
	for (int i = 0; i < allNum.size(); i++)
		if (!che[allNum[i]]) answer++;
	return answer;
}
