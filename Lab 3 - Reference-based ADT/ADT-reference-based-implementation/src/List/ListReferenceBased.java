package List;
// *********************************************************************************
// Lab 2
// Class: CS 111C		Date:	 4/6/16
//  The ListReferenceBased class is a reference based implementation of the ADT list.
//  The clas methods are not called within the methods we wrote.
// Methods replace and equals were added to this class as requested
// Method replace replaces each occurence of oldValue in the list with newValue.  it then return the number of items replaced
// Method equals is a boolean that returns true if aList has values in the same order as the current list, else returns false.
//
// **********************************************************************************


// Reference-based implementation of ADT list.
public class ListReferenceBased implements ListInterface {

  // reference to linked list of items
  private Node head;
  private int numItems; // number of items in list

 // definitions of constructors and methods
    public ListReferenceBased() {
      numItems = 0;
      head = null;
    }

    public boolean isEmpty() {
      return numItems == 0;
    }

    public int size() {
      return numItems;
    }

    private Node find(int index) {
    /* --------------------------------------------------
     Locates a specified node in a linked list.
     Precondition: index is the number of the desired
     node. Assumes that 1 <= index <= numItems+1
     Postcondition: Returns a reference to the desired
     node.
     --------------------------------------------------*/
      Node curr = head;
      for (int skip = 0; skip < index; skip++) {
        curr = curr.next;
      }
      return curr;
    }

    public Object get(int index) {
        // get reference to node, then data in node
        Node curr = find(index);
        Object dataItem = curr.item;
        return dataItem;
    }

    public void add(int index, Object item) {
      if (index >= 0 && index < numItems+1) {
        if (index == 0) {
          // insert the new node containing item at
          // beginning of list
          Node newNode = new Node(item, head);
          head = newNode;
        }
        else {
          Node prev = find(index-1);

          // insert the new node containing item after
          // the node that prev references
          Node newNode = new Node(item, prev.next);
          prev.next = newNode;
        }
        numItems++;
      }
    }
    
    public void remove(int index) {
      if (index >= 0 && index < numItems) {
        if (index == 0) {
          // delete the first node from the list
          head = head.next;
        }
        else {
          Node prev = find(index-1);
          // delete the node after the node that prev
          // references, save reference to node
          Node curr = prev.next;
          prev.next = curr.next;
        }
        numItems--;
      }
    }

    public void removeAll() {
      /* setting head to null causes list to be
       unreachable and thus marked for garbage
       collection */
      head = null;
      numItems = 0;
    }

    public int replace(Object oldValue, Object newValue) {
        // Precondition:  oldValue and newValue are valid values within the list
        // Postcondition: Replaces each occurence of oldValue in the list with newValue, returns number of items replaced
        //counter to determine how many items are replaced. increments each time an item is replaced

        int count = 0;
        //loop to search the list, perform replacement

        Node curr = head;

        while(curr != null){
            if(curr.item == oldValue){
                curr.item = newValue;
                count++;
            }
            curr = curr.next;
        }

        //System.out.println("Number of items replaced: " + count);
        return count;
    }

    public boolean equals(ListReferenceBased aList) {
    // Precondition:  Two lists are present
    // Postcondition: Returns  true if aList has values in the same order as the current list, else returns false

        boolean logichere = false;

        //when both lists have same size
        if(numItems == aList.numItems){

            Node curr = head;           //first list's head
            Node curr2 = aList.head;    //second list's head

            //while linklist is not empty, continues
            while(curr != null){
                if(curr.item != curr2.item){
                    logichere = false;
                    return logichere;
                }

                curr = curr.next;   //first list loop
                curr2 = curr2.next; //second list loop
            }

            logichere = true;
        }else{
            logichere = false;
        }

        //return boolean
        return logichere;
    }
}
