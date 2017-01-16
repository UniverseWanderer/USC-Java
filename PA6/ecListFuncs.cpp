/*  Name:
*  loginid:
*  CS 455 Fall 2016, Extra Credit assignment
*
*  See ecListFuncs.h for specification of each function.
*/

#include <iostream>

#include <cassert>

#include "ecListFuncs.h"

using namespace std;
#define MIN -65535

bool isInOrder(ListType list) {
	ListType tempNode = list;
	int tempInt = MIN;
	while (tempNode != NULL)
	{
		if (tempNode->data >= tempInt)
		{	// still in order
			tempInt = tempNode->data;
			tempNode = tempNode->next;
		}
		else// not in order
			return false;
	}
	return true;  
}



void insertInOrder(ListType & list, Node *itemP) {
	assert(isInOrder(list));     // checks the preconditions
	assert(itemP->next == NULL);
	// add the rest of the code after this line
	ListType tempNode = list;
	while (tempNode != NULL)
	{
		if (itemP->data < tempNode->data)	// insert in the first place
		{
			itemP->next = tempNode;
			list = itemP;
			return;
		}
		else if (tempNode->next == NULL)		// find the end of the list
		{
			if (itemP->data < tempNode->data)	// insert before the end
			{
				itemP->next = tempNode;
				tempNode = itemP;
				return;
			}
			else								// insert after the end
			{
				tempNode->next = itemP;
				return;
			}
		}
		else if (itemP->data < tempNode->next->data) // insert in the middle of the list
		{
			itemP->next = tempNode->next;
			tempNode->next = itemP;
			return;
		}
		else
			tempNode = tempNode->next;
	}
	// empty list now, insert directly
	list = itemP;
}



void insertionSort(ListType &list) {
	// create a new list and insert every element in order
	ListType newList = NULL;
	Node * tempNode;
	while (list != NULL)
	{
		tempNode = list;
		list = list->next;
		tempNode->next = NULL;
		insertInOrder(newList, tempNode);
	}
	list = newList;
}




void splitEvenOdd(ListType &list, ListType &a, ListType &b) {
	ListType tempA = list;
	a = tempA;	// make a point to the first element
	if (list->next != NULL)
	{
		list = list->next;
		ListType tempB = list;		
		b = tempB;	// make b point to the second element

		list = list->next;
		while (list != NULL)
		{	// relink odd elememnt to a
			tempA->next = list;
			tempA = tempA->next;
			list = list->next;
			if (list != NULL)
			{	// relink even element to b
				tempB->next = list;
				tempB = tempB->next;
				list = list->next;
			}
		}
		tempA->next = NULL;
		tempB->next = NULL;
	}
	else
	{	// no more other elements
		list = NULL;
		return;
	}
}