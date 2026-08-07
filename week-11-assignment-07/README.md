# Employee Hash Table

## Assignment

**CIS 287 - Employee Hash Table**

This project modifies the textbook hash table implementation so that it stores complete `Employee` objects instead of strings.

Each employee's name is used as the key for the hash function. The hash table uses an array of generic `LinkedList3<Employee>` objects, allowing collisions to be handled through separate chaining.

The program also provides a `get(String name)` method that searches for an employee by name and returns the matching `Employee` object. If no employee with the requested name exists, the method returns `null`.

---

## Project Files

### `Employee.java`

Defines the `Employee` class.

Each employee contains:

- A name
- A hire date

The employee's name is used as the key when the object is stored in the hash table.

A small `Date` helper class is also contained in this file so the project can represent employee hire dates while retaining the required project file structure.

### `LinkedList3.java`

Implements the generic `LinkedList3<T>` class based on Display 15.8 of the textbook.

The linked list can store objects of any specified type. For this assignment, the hash table uses:

```java
LinkedList3<Employee>
```

A generic `get(int index)` method was added so the hash table can examine Employee objects stored within a bucket while keeping the linked-list class generic.

### `EmployeeHashTable.java`

Implements the hash table.

The table contains an array of:

```java
LinkedList3<Employee>
```

The employee's name is passed to the hash function to determine which bucket should store the Employee object.

The main methods are:

```java
public void put(Employee employee)
```

Adds an employee to the appropriate hash-table bucket.

```java
public Employee get(String name)
```

Hashes the supplied name, searches the appropriate linked-list bucket, and returns the matching Employee object.

If the employee does not exist, the method returns `null`.

### `EmployeeHashTableApp.java`

Contains the `main` method and tests the hash table.

The driver program:

- Adds at least six employees
- Retrieves at least three existing employees
- Attempts to retrieve an employee who does not exist
- Demonstrates a hash collision
- Displays the contents of the hash table

---

## Collision Handling

This hash table uses **separate chaining** to handle collisions.

Each position in the hash-table array contains a `LinkedList3<Employee>`. If multiple employee names produce the same hash value, the Employee objects are stored in the same linked list instead of replacing one another.

For example, the names `Amy` and `May` both hash to bucket 5.

For `Amy`:

```text
A = 65
m = 109
y = 121

65 + 109 + 121 = 295
295 % 10 = 5
```

For `May`:

```text
M = 77
a = 97
y = 121

77 + 97 + 121 = 295
295 % 10 = 5
```

Therefore, both employees are stored in bucket 5.

The program then retrieves both employees individually to demonstrate that the collision was handled correctly.

---

## Test Employees

The program adds the following employees:

- Amy
- May
- John
- Sarah
- Carlos
- Nina

The program successfully retrieves:

- Amy
- May
- Carlos

It also attempts to retrieve:

- Jordan

Because Jordan was never added to the table, `get("Jordan")` returns `null`.

---

## Sample Output

```text
EMPLOYEE HASH TABLE
===================
Bucket 0:
  Nina - Hire Date: September 6, 2024
Bucket 1:
  [empty]
Bucket 2:
  Carlos - Hire Date: February 10, 2023
Bucket 3:
  [empty]
Bucket 4:
  [empty]
Bucket 5:
  Sarah - Hire Date: May 25, 2020
  May - Hire Date: July 1, 2022
  Amy - Hire Date: March 12, 2021
Bucket 6:
  [empty]
Bucket 7:
  [empty]
Bucket 8:
  [empty]
Bucket 9:
  John - Hire Date: November 18, 2019

RETRIEVAL TESTS
===============
Amy -> Amy - Hire Date: March 12, 2021
May -> May - Hire Date: July 1, 2022
Carlos -> Carlos - Hire Date: February 10, 2023
Jordan -> employee not found

COLLISION TEST
==============
Amy and May both hash to bucket 5, but both can be retrieved correctly.
Amy -> Amy - Hire Date: March 12, 2021
May -> May - Hire Date: July 1, 2022
```

---

## How to Compile and Run

The project does not use package declarations.

Compile all Java files with:

```bash
javac *.java
```

Run the program with:

```bash
java EmployeeHashTableApp
```

The program can also be compiled and executed using OnlineGDB.

---

## OnlineGDB

**OnlineGDB Link:**

`https://www.onlinegdb.com/s/as/390021`

---

## GitHub Repository

**GitHub Repository:**

`https://github.com/Juan-ONESTAR/JavaCourseAssignments/tree/main/week-11-assignment-07`

---

# Gemini Use and Reflection

## Gemini Prompt 1

**Prompt:**

> How should I come up with collision tests and edge cases for this program?

**What Gemini helped me with:**

Providing examples of unit tests for the program that can test edge cases and holes in it

---

## Gemini Prompt 2

**Prompt:**

> I am having an issue getting this program to run, what is wrong?

**What Gemini helped me with:**

It went through the program files I provided and corrected me on spelling and syntax errors. As well as correcting the structure of the program

---

## Suggestion I Changed, Corrected, or Rejected

I rejected Gemini when it attempted to provide a fully completed version of the program when I gave it the files to check for errors and issues I had when trying to run the program.
I was mostly confident in the program and only needed assistance checking syntax and spelling errors I may have done.

---

## How I Verified the Program

I verified the program by compiling and running all of the Java files together in OnlineGDB. I first confirmed that the program successfully added six different Employee objects to the hash table and displayed the contents of each bucket. I then tested the get(String name) method by retrieving three employees that were added to the table: Amy, May, and Carlos. I also searched for Jordan, who was not added, and confirmed that the program returned null and displayed that the employee was not found. To verify collision handling, I used Amy and May because both names hash to bucket 5 with the program's hash function. I confirmed that both Employee objects were stored in the same linked-list bucket and that each one could still be retrieved correctly. This showed that separate chaining was working as expected.

