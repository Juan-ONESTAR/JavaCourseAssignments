public class EmployeeHashTableApp
{
    public static void main(String[] args)
    {
        EmployeeHashTable table = new EmployeeHashTable();
        
        Employee amy = new Employee("Amy", new Date("March", 12, 2021));
        
        Employee may = new Employee("May", new Date("July", 1, 2022));
        
        Employee john = new Employee("John", new Date("Novemeber", 18, 2019));
        
        Employee sarah = new Employee("Sarah", new Date("May", 25, 2020));
        
        Employee carlos = new Employee("Carlos", new Date("February", 10, 2023));
        
        Employee nina = new Employee("Nina", new Date("September", 6, 2024));
        
        table.put(amy);
        table.put(may);
        table.put(john);
        table.put(sarah);
        table.put(carlos);
        table.put(nina);
        
        System.out.println("Employee Hash Table");
        System.out.println("---------------------");
        
        table.displayTable();
        
        System.out.println();
        System.out.println("Retrieval Tests");
        System.out.println("---------------------");
        
        displayLookup(table, "Amy");
        displayLookup(table, "May");
        displayLookup(table, "Carlos");
        
        // Nonexistent Employee
        displayLookup(table, "Jordan");
        
        System.out.println();
        System.out.println("Collision Test");
        System.out.println("---------------------");
        System.out.println("Amy and May both hash to bucket 5, but both can be retrieved correctly");
        
        displayLookup(table, "Amy");
        displayLookup(table, "May");
    }
    
    private static void displayLookup(EmployeeHashTable table, String name)
    {
        Employee employee = table.get(name);
        
        if (employee == null)
        {
            System.out.println(name + " -> employee not found!");
        }
        else
        {
            System.out.println(name + " -> " + employee);
        }
    }
}