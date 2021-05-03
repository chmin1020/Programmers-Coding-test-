#include <string>
#include <vector>
#include <algorithm>
#include <map>
#include <set>
using namespace std;

typedef pair<string, int> item;

vector<int> solution(vector<string> genres, vector<int> plays) {
	vector<int> answer;
	map<string, int> rank;

    //map으로 genre 개수 및 횟수 파악
	for (int i = 0; i < genres.size(); i++) {
		if (rank.find(genres[i]) == rank.end())
			rank.insert(make_pair(genres[i], plays[i]));
		else
			rank[genres[i]] += plays[i];
	}

	vector<item> genre_rank(rank.begin(), rank.end());
	vector<set<pair<int, int>>> album(genre_rank.size());
    
    //새 벡터로 장르를 횟수 내림차순 정렬
	sort(genre_rank.begin(), genre_rank.end(), [](item a, item b) {
		return a.second > b.second;
	});
    
     //각 장르별 음악 횟수, 고유 번호 정렬
	for (int i = 0; i < genres.size(); i++) {
		for (int j = 0; j < genre_rank.size(); j++) {
			if (genres[i] == genre_rank[j].first) {
				album[j].insert(make_pair(-plays[i], i)); //음수로 넣어서 정렬
				break;
			}
		}
	}

    //answer 벡터에 정답 입력
	for (int i = 0; i < genre_rank.size(); i++) {
		set<pair<int, int>>::iterator it = album[i].begin();
		for (int j = 0; j < 2 && it != album[i].end(); j++, it++) {
			answer.push_back((*it).second);
		}
	}
	return answer;
}
