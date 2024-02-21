/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {

    // Points to the first node in this list
    private Node first;

    // The number of elements in this list
    private int size;
	
    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }

    /** Returns the number of elements in this list. */
    public int getSize() {
 	      return size;
    }

    /** Returns the first element in the list */
    public CharData getFirst() {
        return first.cp;
    }

    /** GIVE Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {
        CharData data = new CharData(chr);
        Node newNode = new Node(data);
        newNode.next = first;
        first = newNode;
        size++;
    }
    
    /** GIVE Textual representation of this list. */
    public String toString() {
        Node current = first;
        String text = "";
        while(current != null){
            text += current.toString();
            current = current.next;
        }
        return text;
    }

    /** Returns the index of the first CharData object in this list
     *  that has the same chr value as the given char,
     *  or -1 if there is no such object in this list. */
    public int indexOf(char chr) {
        if(size == 0){
            return -1;
        }else{
            int count = 0;
            Node current = first;
            while(current != null){
                count++;
                if(current.cp.chr == chr){
                    return count;
                }else{
                    current = current.next;
                }
            }
            return -1;
        }
    }

    /** If the given character exists in one of the CharData objects in this list,
     *  increments its counter. Otherwise, adds a new CharData object with the
     *  given chr to the beginning of this list. */
    public void update(char chr) {
        if(indexOf(chr) != -1){
            int count = 0;
            int index = indexOf(chr);
            Node current = first;
            while(current != null){
                count++;
                if(count == index){
                    current.cp.count++;
                    break;
                }else{
                    current = current.next;
                }
            }
        }else{
            addFirst(chr);
        }
    }

    /** GIVE If the given character exists in one of the CharData objects
     *  in this list, removes this CharData object from the list and returns
     *  true. Otherwise, returns false. */
    public boolean remove(char chr) {
        if(size == 0){
            return false;
        }else{
            int count = 0;
            int secondCount = 0;
            int inList = 0;
            Node current = first;
            while(current != null){
                count++;
                if(current.cp.chr == chr){
                    inList = 1;
                    break;
                }else{
                    current = current.next;
                }
            }
            if(inList == 1){
                current = first;
                while(current != null){
                    secondCount++;
                    if(secondCount == count - 1){
                        current.next = (current.next).next;
                        size--;
                        return true;
                    }else{
                        current = current.next;
                    }
                }
            }
            
            return false;
            
        }
    }

    /** Returns the CharData object at the specified index in this list. 
     *  If the index is negative or is greater than the size of this list, 
     *  throws an IndexOutOfBoundsException. 
     * @throws Exception */
    public CharData get(int index) throws IndexOutOfBoundsException {
        if(size == 0){
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        }else{
            int count = 0;
            Node current = first;
            while(current != null){
                count++;
                if(count == index){
                    return current.cp;
                }else{
                    current = current.next;
                }
            }
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        }
    }

    /** Returns an array of CharData objects, containing all the CharData objects in this list. */
    public CharData[] toArray() {
	    CharData[] arr = new CharData[size];
	    Node current = first;
	    int i = 0;
        while (current != null) {
    	    arr[i++]  = current.cp;
    	    current = current.next;
        }
        return arr;
    }

    /** Returns an iterator over the elements in this list, starting at the given index. */
    public ListIterator listIterator(int index) {
	    // If the list is empty, there is nothing to iterate   
	    if (size == 0) return null;
	    // Gets the element in position index of this list
	    Node current = first;
	    int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        // Returns an iterator that starts in that element
	    return new ListIterator(current);
    }
}
