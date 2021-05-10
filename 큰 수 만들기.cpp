#include <string>
#include <vector>

using namespace std;

string solution(string number, int k) {
	string answer = number;
	string::iterator it;

	for (int i = 0; i < k; i++) {
		it = answer.begin();
		while ((it + 1) != answer.end()) {
			if (*(it) < *(it + 1))
				break;
            it++;
		}
		answer.erase(it);
	}
	return answer;
}
