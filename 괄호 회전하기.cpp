#include <string>
#include <stack>
using namespace std;

int solution(string s) {
	stack<char> st;
	bool flag = false;
	int answer = 0;
	int end = s.length() - 1;

	for (int i = 0; i < s.length(); i++) {
		for (int j = i, k = 0; k < s.length(); j = (j + 1) % s.length(), k++) {
			if (s[j] == '[' || s[j] == '(' || s[j] == '{')
				st.push(s[j]);
			else {
				if (st.empty()) {
					flag = true; break;
				}
				if (s[j] == ']' && st.top() != '[') {
					flag = true; break;
				}
				if (s[j] == ')' && st.top() != '(') {
					flag = true; break;
				}
				if (s[j] == '}' && st.top() != '{') {
					flag = true; break;
				}
				st.pop();
			}
		}
		if (st.empty() && !flag) answer++;
		flag = false; end = i;
		while (!st.empty()) st.pop();
	}
	return answer;
}
