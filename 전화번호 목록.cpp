#include <string>
#include <vector>
#include <set>

using namespace std;

bool solution(vector<string> phone_book) {
    set<string> numbers;
    
    for(int i = 0 ; i < phone_book.size(); i++)
        numbers.insert(phone_book[i]);
    
    for(set<string>::iterator it = numbers.begin(); it != numbers.end(); it++){
        for(int i = 1; i < (*it).length(); i++)
            if(numbers.find((*it).substr(0,i)) != numbers.end()) return false;
    }
    return true;
}
