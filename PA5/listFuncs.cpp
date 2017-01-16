// Name: Deyu Ma
// Loginid: deyuma
// CSCI 455 PA5
// Fall 2016


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
	key = theKey;
	value = theValue;
	next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
	key = theKey;
	value = theValue;
	next = n;
}


//*************************************************************************
// put the function definitions for your list functions below

// implement lookup a key in the list
int* lookupList(ListType node, const string &key)
{
	while (node != NULL) {
		if (node->key == key)
			return &(node->value);
		else
			node = node->next;
	}
	return NULL;
}

// implement remove a key from the list
bool removeList(ListType &node, const string &key)
{
	while (node->next != NULL) {
		if (node->next->key == key)
		{
			node->next = node->next->next;
			return true;
		}
		else
		{
			node = node->next;
		}
	}
	if (node->key == key)
	{
		node = NULL;
		return true;
	}
	
	return false;
}

// implement insert a key and value into the list
void insertList(ListType &node, const string &key, const int &val)
{
	if (node != NULL)
		node = new Node(key, val, node);
	else
		node = new Node(key, val);
}
