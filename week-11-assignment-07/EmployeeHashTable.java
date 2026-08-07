public class EmployeeHashTable
{
    private static final int SIZE = 10;
    
    private LinkedList3<Employee>[] hashArray;
    
    @SuppressWarnings("unchecked")
    public EmployeeHashTable()
    {
        hashArray = (LinkedList3<Employee>[]) new LinkedList3<?>[SIZE];
        
        for (int i = 0; i < SIZE; i++)
        {
            hashArray[i] = new LinkedList3<Employee>();
        }
    }
    
    private int computeHash(String name)
    {
        int hash = 0;
        
        for (int i = 0; i < name.length(); i++)
        {
            hash += name.charAt(i);
        }
        
        return hash % SIZE;
    }
    
    public void put(Employee employee)
    {
        if (employee == null)
        {
            throw new IllegalArgumentException(
                "Employee cannot be null!");
        }
        
        String name = employee.getName();
        int hash = computeHash(name);
        LinkedList3<Employee> list = hashArray[hash];
        
        if (findEmployeeInBucket(list, name) == null)
        {
            list.addToStart(employee);
        }
    }
    
    public Employee get(String name)
    {
        if (name == null)
        {
            return null;
        }
        
        int hash = computeHash(name);
        LinkedList3<Employee> list = hashArray[hash];
        
        return findEmployeeInBucket(list, name);
    }
    
    private Employee findEmployeeInBucket(LinkedList3<Employee> list, String name)
    {
        int listSize = list.size();
        
        for (int i = 0; i < listSize; i++)
        {
            Employee employee = list.get(i);
            
            if (employee.getName().equals(name))
            {
                return employee;
            }
        }
        
        return null;
    }
    
    public void displayTable()
    {
        for (int i = 0; i < SIZE; i++)
        {
            System.out.println("Bucket " + i + ":");
            
            if (hashArray[i].isEmpty())
            {
                System.out.println("  [empty]");
            }
            else
            {
                hashArray[i].outputList();
            }
        }
    }
}