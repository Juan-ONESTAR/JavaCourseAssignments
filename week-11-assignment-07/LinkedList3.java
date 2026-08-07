public class LinkedList3<T>
{
    private class Node<E>
    {
        private E data;
        private Node<E> link;
        
        public Node()
        {
            data = null;
            link = null;
        }
        
        public Node(E newData, Node<E> linkValue)
        {
            data = newData;
            link = linkValue;
        }
    }
    
    private Node<T> head;
    
    public LinkedList3()
    {
        head = null;
    }
    
    public void addToStart(T itemData)
    {
        head = new Node<T>(itemData, head);
    }
    
    public boolean deleteHeadNode()
    {
        if (head != null)
        {
            head = head.link;
            return true;
        }
        return false;
    }
    
    public int size()
    {
        int count = 0;
        Node<T> position = head;
        
        while (position != null)
        {
            count++;
            position = position.link;
        }
        return count;
    }
    
    public boolean contains(T item)
    {
        return find(item) != null;
    }
    
    private Node<T> find(T target)
    {
        Node<T> position = head;
        
        while (position != null)
        {
            if (position.data.equals(target))
            {
                return position;
            }
            
            position = position.link;
        }
        return null;
    }
    
    public T findData(T target)
    {
        Node<T> result = find(target);
        
        if (result == null)
        {
            return null;
        }
        return result.data;
    }
    
    public T get(int index)
    {
        if (index < 0)
        {
            throw new IllegalArgumentException(
                "Index cannot be negative!");
        }
        
        Node<T> position = head;
        int currentIndex = 0;
        
        while (position != null)
        {
            if (currentIndex == index)
            {
                return position.data;
            }
            
            position = position.link;
            currentIndex++;
        }
        
        throw new IndexOutOfBoundsException("Index: " + index);
    }
    
    public void outputList()
    {
        Node<T> position = head;
        
        while (position != null)
        {
            System.out.println(" " + position.data);
            position = position.link;
        }
    }
    
    public boolean isEmpty()
    {
        return head == null;
    }
    
    public void clear()
    {
        head = null;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object otherObject)
    {
        if (otherObject == null)
        {
            return false;
        }
        
        if (getClass() != otherObject.getClass())
        {
            return false;
        }
        
        LinkedList3<T> otherList = (LinkedList3<T>) otherObject;
        
        if (size() != otherList.size())
        {
            return false;
        }
        
        Node<T> position = head;
        Node<T> otherPosition = otherList.head;
        
        while (position != null)
        {
            if (!position.data.equals(otherPosition.data))
            {
                return false;
            }
            
            position = position.link;
            otherPosition = otherPosition.link;
        }
        return true;
    }
}