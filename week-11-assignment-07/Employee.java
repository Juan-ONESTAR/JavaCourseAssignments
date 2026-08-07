public class Employee
{
    private String name;
    private Date hireDate;
    
    public Employee()
    {
        name = "No name";
        hireDate = new Date("January", 1, 1000);
    }
    
    public Employee(String theName, Date theDate)
    {
        if (theName == null || theDate == null)
        {
            throw new IllegalArgumentException(
                "Employee name and hire date cannot be null!");
        }
        
        name = theName;
        hireDate = new Date(theDate);
    }
    
    public Employee(Employee originalObject)
    {
        if (originalObject == null)
        {
            throw new IllegalArgumentException(
                "Employee to copy cannot be null!");
        }
        
        name = originalObject.name;
        hireDate = new Date(originalObject.hireDate);
    }
    
    public String getName()
    {
        return name;
    }
    
    public Date getHireDate()
    {
        return new Date(hireDate);
    }
    
    public void setName(String newName)
    {
        if (newName == null)
        {
            throw new IllegalArgumentException(
                "Employee name cannot be null!");
        }
        
        name  = newName;
    }
    
    public void setHireDate(Date newDate)
    {
        if (newDate == null)
        {
            throw new IllegalArgumentException(
                "Employee hire date cannot be null!");
        }
        
        hireDate = new Date(newDate);
    }

    @Override
    public String toString()
    {
        return name + " - Hire Date: " + hireDate;
    }
    
    public boolean equals(Employee otherEmployee)
    {
        if (otherEmployee == null)
        {
            return false;
        }
        
        return name.equals(otherEmployee.name) && hireDate.equals(otherEmployee.hireDate);
    }
}

class Date
{
    private String month;
    private int day;
    private int year;
    
    public Date(String month, int day, int year)
    {
        setDate(month, day, year);
    }
    
    public Date(Date originalDate)
    {
        if (originalDate == null)
        {
            throw new IllegalArgumentException(
                "Date to copy cannot be null!");
        }
        
        month = originalDate.month;
        day = originalDate.day;
        year = originalDate.year;
    }
    
    public void setDate(String month, int day, int year)
    {
        if (month == null || month.isBlank())
        {
            throw new IllegalArgumentException(
                "Month cannot be null or blank!");
        }
        
        if (day < 1 || day > 31)
        {
            throw new IllegalArgumentException(
                "Day must be between 1 and 31!");
        }
        
        this.month = month;
        this.day = day;
        this.year = year;
    }
    
    @Override
    public String toString()
    {
        return month + " " + day + ", " + year;
    }
    
    @Override
    public boolean equals(Object otherObject)
    {
        if (this == otherObject)
        {
            return true;
        }
        
        if (!(otherObject instanceof Date))
        {
            return false;
        }
        
        Date otherDate = (Date) otherObject;
        
        return month.equals(otherDate.month) && day == otherDate.day && year == otherDate.year;
    }
}