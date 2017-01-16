// Name: Deyu Ma
// Loginid: deyuma
// CSCI 455 PA5
// Fall 2016

// Table.cpp  Table class implementation


/*
* Modified 11/22/11 by CMB
*   changed name of constructor formal parameter to match .h file
*/

#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************

// implement constructor without parameter
Table::Table() {
	hashSize = HASH_SIZE;
	table=new ListType[hashSize] ;
	for (int i = 0; i < hashSize; i++) {
		table[i] = NULL;
	}
}

// implement constructor with hash size
Table::Table(unsigned int hSize) {
	hashSize = hSize;
	table = new ListType[hashSize];
	for (int i = 0; i < hashSize; i++) {
		table[i] = NULL;
	}
}

// implement lookup function and return the index in the table
int * Table::lookup(const string &key) {
	int code= hashCode(key);
	return lookupList(table[code], key);
}

// implement remove function
bool Table::remove(const string &key) {
	if (lookupList(table[hashCode(key)], key))
	{
		removeList(table[hashCode(key)], key);
		return true;
	}
	return false; 
}

// implement insert function
bool Table::insert(const string &key, int value) {
	if(lookupList(table[hashCode(key)],key))
		return false; 
	else
	{
		insertList(table[hashCode(key)], key, value);
		return true;
	}
}

// calculate the number of entries now
int Table::numEntries() const {
	int count = 0;
	for (int i = 0; i < hashSize;i++)
	{
		if (table[i] != NULL) {
			ListType p = table[i];
			while (p != NULL) {
				count++;
				p = p->next;
			}
		}
	}
	return count; 
}

// print out all the elements in the talbe
void Table::printAll() const {
	for (int i = 0; i < hashSize;i++)
	{
		if (table[i] != NULL) {
			ListType p = table[i];
			while (p != NULL) {
				cout << p->key << " " << p->value << endl;
				p = p->next;
			}
		}			
	}
}

// display the status of current hash table
void Table::hashStats(ostream &out) const {
	out << "number of buckets: " << hashSize << endl;
	out << "number of entries: " << numEntries() << endl;
	out << "number of non-empty buckets: " << numBuckets() << endl;
	out << "longest chain: " << findLongest() << endl;
}

// add definitions for your private methods here

// calculate the number of buckets
int Table::numBuckets() const {
	int count = 0;
	for (int i = 0; i < hashSize;i++)
	{
		if (table[i] != NULL)
			count++;
	}
	return count;
}

// find the longest link in the table
int Table::findLongest() const {
	int longest = 0;
	for (int i = 0; i < hashSize; i++) {
		int temp = 0;
		if (table[i] != NULL) {
			ListType p = table[i];
			while (p != NULL) {
				temp++;
				p = p->next;
			}
		}
		longest = temp > longest ? temp : longest;
	}
	return longest;
}
