// [2015-07-13] Challenge #223 [Easy] Garland words (https://www.reddit.com/r/dailyprogrammer/comments/3d4fwj/20150713_challenge_223_easy_garland_words/)
// Coded by Bruno Petrus

#include <iostream>
#include <string>
#include <fstream>
#include <vector>
#include <map>

// Prototype
int garland(std::string word);
void process(std::string filePath);
void sortedProcess(std::string filePath);

int main(int argc, char* argv[]) {
	
	/*std::cout << garland("programmer") << std::endl;
	std::cout << garland("ceramic") << std::endl;
	std::cout << garland("onion") << std::endl;
	std::cout << garland("alfalfa") << std::endl;*/
	std::cout << "Currently showing words with garland degree bigger than 3.\n";

	process("networdz-polski.txt");
	//sortedProcess("networdz-polski.txt");

	//Delay
	char temp;
	std::cin >> temp;
	return 0;
}

int garland(std::string word) {
	int deg = 0; // Degree of the word
	int fIndex = 0; // First index
	int sIndex = 1; // Second index
	int len = word.size(); // Length of the word

	while (sIndex < len) {
		if (word[fIndex] == word[sIndex]) { //If they are equal
			fIndex++;
			deg++;
		}
		else { // Reset
			fIndex = 0;
			deg = 0;
		}
		sIndex++;
	}

	return deg;
}

void process(std::string filePath) {
	std::ifstream file(filePath); // Open file
	if (file.fail()) {
		std::cerr << "Cannot open file:" << filePath << std::endl;
	}

	std::string temp;
	while (std::getline(file, temp)) {
		int gd = garland(temp);
		if (gd >= 3) {
			std::cout << temp << "    Garland degree is: " << garland(temp) << "\n";
		}
	}
}

void sortedProcess(std::string filePath) {
	std::ifstream file(filePath); // Open file
	if (file.fail()) {
		std::cerr << "Cannot open file:" << filePath << std::endl;
	}

	std::ofstream output("garlands.txt");

	std::string temp;
	std::map<int, std::string> map;
	while (std::getline(file, temp)) {
		int gd = garland(temp);
		if (gd >= 3) {
			std::pair<int, std::string> pair;
			map.insert(pair);
			std::cout << temp << "    Garland degree is: " << garland(temp) << "\n";
			output << temp << "    Garland degree is: " << garland(temp) << "\n";
		}
	}
}