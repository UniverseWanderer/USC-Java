// Name: Deyu Ma
// Loginid: deyuma
// CSCI 455 PA5
// Fall 2016

/*
* grades.cpp
* A program to test the Table class.
* How to run it:
*      grades [hashSize]
*
* the optional argument hashSize is the size of hash table to use.
* if it's not given, the program uses default size (Table::HASH_SIZE)
*
*/

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

#define TRAILS 6

void doInsert(Table* & table);
void doChange(Table* & table);
void doLookup(Table* & table);
void doRemove(Table* & table);
void doHelp();

int main(int argc, char * argv[]) {

	// gets the hash table size from the command line

	int hashSize = Table::HASH_SIZE;

	Table * grades;  // Table is dynamically allocated below, so we can call
					 // different constructors depending on input from the user.

	if (argc > 1) {
		hashSize = atoi(argv[1]);  // atoi converts c-string to int

		if (hashSize < 1) {
			cout << "Command line argument (hashSize) must be a positive number"
				<< endl;
			return 1;
		}

		grades = new Table(hashSize);

	}
	else {   // no command line args given -- use default table size
		grades = new Table();
	}


	grades->hashStats(cout);

	// add more code here
	// Reminder: use -> when calling Table methods, since grades is type Table*

	bool endFlag = false;
	string cmd = "";
	
	while (!endFlag) {
		cout << "cmd>";
		cin >> cmd;
		
		// switch to different command functions
		if(cmd=="insert"){	
			doInsert(grades);			
		}
		else if (cmd == "change") {
			doChange(grades);
		}
		else if (cmd == "lookup") {
			doLookup(grades);
		}
		else if (cmd == "remove") {
			doRemove(grades);
		}
		else if (cmd == "print") {
			grades->printAll();
		}
		else if (cmd == "size") {
			cout << grades->numEntries() << endl;
		}
		else if (cmd == "stats") {
			grades->hashStats(cout);
		}
		else if (cmd == "help") {
			doHelp();
		}
		else if (cmd == "quit") {
			endFlag = true;
		}
		else {
			cout << "ERROR: invalid command(input 'help' for more information)" << endl;
		}
	}

	system("pause");
	return 0;
}

// implement Insert function
void doInsert(Table* & table) {
	string name;
	int score;
	cin >> name;
	cin >> score;
	if (table->lookup(name)) {
		cout << "Warning: Student already exists!" << endl;
		return;
	}
	table->insert(name, score);
}

// implement Change function
void doChange(Table* & table) {
	string name;
	int score;
	cin >> name;
	cin >> score;
	if (table->lookup(name)) {
		table->remove(name);
		table->insert(name, score);
		return;
	}
	cout << "Warning: Student not found!" << endl;
}

// implement Lookup function
void doLookup(Table* & table) {
	string name;
	cin >> name;
	if (int* temp = table->lookup(name))
		cout << name << " " << *temp << endl;
	else
		cout << "Warning: Student not found!" << endl;
}

// implement Remove function
void doRemove(Table* & table) {
	string name;
	cin >> name;
	if (table->lookup(name)) {
		table->remove(name);
		return;
	}
	cout << "Warning: Student not found!" << endl;
}

// implement Help function
void doHelp() {
	cout << "/***************************  HELP INFO  ****************************/" << endl;
	cout << "insert name score ----- Insert this name and score in the grade table." << endl;
	cout << "change name newscore -- Change the score for name." << endl;
	cout << "lookup name ----------- Lookup the name, and print out his or her score." << endl;
	cout << "remove name ----------- Remove this student. " << endl;
	cout << "print ----------------- Prints out all names and scores in the table." << endl;
	cout << "size ------------------ Prints out the number of entries in the table." << endl;
	cout << "stats ----------------- Prints out statistics about the hash table at this point." << endl;
	cout << "help ------------------ Prints out a brief command summary." << endl;
	cout << "quit ------------------ Exits the program." << endl;
}
