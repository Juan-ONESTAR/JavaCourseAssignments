# Recursive Word Ladder

## 1. Student Information

**Student Name:** Juan
**Course Name:** CIS 287
**Assignment Name:** Programming Assignment: Recursive Word Ladder

---

## 2. Program Description

This program implements the Word Ladder game using recursive search and backtracking.

The user enters a starting word and an ending word. The program searches for a sequence of valid dictionary words connecting the two words.

Each word in the ladder must:

* Be the same length as the starting word.
* Exist in `words_small.txt`.
* Differ from the previous word by exactly one letter.

For example:

```text
fish
wish
wash
mash
mast
```

The program does not need to find the shortest possible word ladder. Any valid ladder is acceptable.

---

## 3. Assignment Requirements

The program was designed to meet the following requirements:

* Read valid words from `words_small.txt`.
* Keep the dictionary file in the same folder as the Java files.
* Use a relative file path instead of an absolute path.
* Ask the user for a starting word.
* Ask the user for an ending word.
* Remove leading and trailing spaces from input.
* Convert input to lowercase.
* Reject empty input.
* Verify that both words have the same length.
* Verify that both words exist in the dictionary.
* Ignore dictionary words of a different length during the search.
* Determine whether two words differ by exactly one letter.
* Use recursion to search for the ladder.
* Use backtracking when a path fails.
* Track visited words to prevent cycles.
* Display the complete ladder when one is found.
* Display an appropriate message when no ladder exists.
* Handle a missing or unreadable dictionary file.
* Use multiple classes with separate responsibilities.
* Use private instance variables for encapsulation.
* Use constructors in `WordDictionary` and `WordLadderSolver`.
* Demonstrate composition by giving `WordLadderSolver` a `WordDictionary` object.
* Avoid package declarations.
* Avoid external libraries.
* Avoid hard-coded completed ladders.
* Avoid replacing the recursive search with a queue-based breadth-first search.

---

## 4. Class Descriptions

### WordLadderApp

`WordLadderApp` is the driver class and contains the `main` method.

Its responsibilities include:

* Creating the dictionary object.
* Creating the solver object.
* Asking the user for input.
* Trimming input.
* Converting input to lowercase.
* Checking for empty input.
* Checking that the words have the same length.
* Checking that both words exist in the dictionary.
* Calling the word ladder solver.
* Displaying the result.
* Handling file-related errors.

The recursive word ladder algorithm is not placed inside `main`.

### WordDictionary

`WordDictionary` manages the collection of valid dictionary words.

It reads `words_small.txt` using Java file input classes such as `BufferedReader` and `FileReader`.

The dictionary words are stored in private collection fields so that other classes cannot directly modify them.

The class provides methods such as:

```java
contains(String word)
```

and:

```java
getWordsOfLength(int length)
```

The second method allows the solver to work only with dictionary words that have the correct length.

### WordLadderSolver

`WordLadderSolver` performs the recursive search.

It contains a reference to a `WordDictionary` object and uses the dictionary to retrieve candidate words.

It also maintains:

```java
HashSet<String> visited
```

and:

```java
ArrayList<String> ladder
```

The `visited` collection prevents the search from repeatedly visiting the same word.

The `ladder` collection represents the currently active path through the dictionary.

### WordUtils

`WordUtils` contains the static method:

```java
public static boolean differsByOneLetter(
        String firstWord,
        String secondWord)
```

The method returns `true` only when two words:

* Have the same length.
* Differ in exactly one character position.

For example:

```text
fish / wish  -> true
fish / fist  -> true
fish / wash  -> false
fish / fish  -> false
fish / table -> false
```

---

## 5. Compilation Instructions

Open a terminal or command prompt in the folder containing the assignment files.

Compile all Java files with:

```bash
javac *.java
```

The project does not use package declarations or external libraries.

---

## 6. Execution Instructions

Run the application with:

```bash
java WordLadderApp
```

The file `words_small.txt` must be located in the same directory as the Java source files.

---

## 7. Test Results

### Successful Test

**Input:**

```text
Starting word: fish
Ending word: mast
```

A valid result may be:

```text
fish
wish
wash
mash
mast
```

The exact ladder may be different because the assignment does not require the shortest possible ladder.

### Different-Length Test

**Input:**

```text
Starting word: fish
Ending word: stone
```

**Output:**

```text
The starting and ending words must have the same length.
```

### Invalid Dictionary Word Test

**Input:**

```text
Starting word: fish
Ending word: zzzz
```

**Output:**

```text
The ending word does not exist in the dictionary.
```

### Empty Input Test

**Input:**

```text
Starting word:
```

**Output:**

```text
The starting word cannot be empty.
```

### Additional Successful Test Cases

The provided assignment dictionary supports examples such as:

```text
cold -> cord -> card -> ward -> warm
lead -> load -> goad -> gold
hit -> hot -> dot -> dog -> cog
stone -> atone -> alone -> clone
```

These ladders are not hard-coded into the Java program. They are found by searching the dictionary.

---

## 8. Recursion Explanation

The recursive method is located in `WordLadderSolver` and is named `search`.

Each call to `search` represents the program exploring one current word.

The method adds the current word to the active ladder and marks it as visited.

The base case occurs when:

```java
currentWord.equals(endWord)
```

is `true`.

At that point, the ending word has been reached and the method returns `true`.

If the current word is not the destination, the program examines candidate words of the correct length.

A candidate can be searched when:

* It has not already been visited.
* It differs from the current word by exactly one letter.

The recursive call occurs when the solver calls `search` again using a valid candidate as the new current word.

For example:

```text
fish
  |
 wish
  |
 wash
  |
 mash
  |
 mast
```

Each move to the next word is another recursive call.

The search progresses toward the base case by continuing to visit valid one-letter neighbors until the current word becomes equal to the ending word.

---

## 9. Backtracking Explanation

Backtracking occurs when the search chooses a word but cannot reach the ending word through that path.

For example, the active ladder might temporarily contain:

```text
fish
dish
dash
cash
```

If the recursive search cannot reach the destination from that branch, the current word is removed from the ladder using:

```java
ladder.remove(ladder.size() - 1);
```

The recursive method then returns `false`.

Control returns to the previous recursive call, which continues examining other possible candidate words.

Removing the failed word is important because the `ladder` collection should represent only the currently active path.

Backtracking allows the program to:

1. Make a choice.
2. Explore that choice recursively.
3. Undo the choice if it fails.
4. Try another possible choice.

---

## 10. Visited Words Explanation

The program uses a `HashSet<String>` named `visited` to record words that have already been explored.

Before recursively searching from a candidate, the program checks whether that candidate has already been visited.

This prevents cycles such as:

```text
cold
cord
cold
cord
cold
...
```

Without the visited collection, two words that differ by one letter could repeatedly lead back to each other and cause unnecessary or potentially endless recursive calls.

Using a `HashSet` also makes checking whether a word has already been visited efficient.

---

## 11. Object-Oriented Design Explanation

The program uses object-oriented design by separating different responsibilities into different classes.

### Encapsulation

The internal data used by classes such as `WordDictionary` and `WordLadderSolver` is stored in private instance variables.

Other classes interact with that data through constructors and methods instead of modifying the fields directly.

### Constructors

`WordDictionary` uses a constructor to receive the dictionary filename and load its data.

For example:

```java
WordDictionary dictionary =
        new WordDictionary("words_small.txt");
```

`WordLadderSolver` uses a constructor to receive the dictionary object:

```java
WordLadderSolver solver =
        new WordLadderSolver(dictionary);
```

### Composition

Composition is demonstrated because `WordLadderSolver` has a `WordDictionary` object.

The solver does not read the dictionary file itself. Instead, it relies on `WordDictionary` to perform that responsibility.

This creates a relationship where one object uses another object as part of its work.

### Separation of Responsibilities

The classes are divided as follows:

```text
WordLadderApp
    Controls the application and user interaction

WordDictionary
    Loads and manages valid words

WordLadderSolver
    Performs recursive searching and backtracking

WordUtils
    Compares words
```

This design prevents one class from being responsible for the entire program and makes the application easier to understand and maintain.

---

## 12. Gemini Use and Reflection

I used Google Gemini as an AI-assisted programming tool while reviewing this assignment.

I used Gemini primarily for two purposes:

1. To help prepare, construct, and review the organization of this README.
2. To compare my Java implementation against the assignment requirements and identify anything that might not meet the expected class structure, recursion requirements, input validation requirements, or automated-grading expectations.

I did not rely on Gemini as a replacement for understanding the program. I also did not use Gemini to generate my understanding of the Recursive Method or the programs Object Oriented design. I reviewed its responses and compared its suggestions against the assignment instructions before making changes.

### Gemini Prompt 1

```text
I am completing a Java programming assignment called Recursive Word Ladder.

The assignment requires four classes:
- WordLadderApp
- WordDictionary
- WordLadderSolver
- WordUtils

The program must read words_small.txt, validate user input, use recursion with backtracking to find a word ladder, use a visited collection to prevent cycles, demonstrate encapsulation and composition, and handle file-related exceptions.

Please review the assignment requirements and help me create a checklist for my README.md. Do not write or replace my Java code. Focus on making sure the README explains the program, class responsibilities, recursion, backtracking, visited words, object-oriented design, compilation, execution, testing, and AI usage.
```

### Assistance Provided

Gemini helped identify the major topics that needed to be documented in the README. This included the class responsibilities, compilation and execution instructions, recursion, backtracking, visited-word tracking, object-oriented design, testing, and AI-use reflection.

I used this feedback to organize the README into sections that closely match the assignment requirements.

### Gemini Prompt 2

```text
Please review the following Java Word Ladder program against these requirements:

- Uses WordLadderApp, WordDictionary, WordLadderSolver, and WordUtils
- Reads words_small.txt using a relative path
- Uses recursion for the search
- Uses backtracking
- Uses a HashSet or similar visited collection to prevent cycles
- Checks that words differ by exactly one letter
- Ignores dictionary words of the wrong length
- Validates empty input, word length, and dictionary membership
- Handles an unreadable dictionary file
- Uses private instance variables
- Demonstrates composition because WordLadderSolver uses a WordDictionary object
- Does not use BFS or a queue
- Does not hard-code completed ladders
- Does not use package declarations or external libraries

Identify anything that does not meet the requirements or could cause problems with automated grading. Explain the issue rather than rewriting the whole assignment.

WordLadderApp.Java
WordDictionary.Java
WordLadderSolver.Java
WordUtils.java
```

### Assistance Provided

Gemini was used as a second review of the completed program. I compared its feedback against the assignment instructions to check that the program followed the required filenames, class responsibilities, recursive search structure, backtracking behavior, visited-word tracking, dictionary handling, and object-oriented design requirements.

The review was also useful for identifying requirements that could be important to an automated grading system rather than only checking whether the program produced the correct output.

### Gemini Suggestion I Changed, Corrected, or Rejected

One suggestion I did not follow was using a more complicated search algorithm to find the shortest possible ladder.

I kept the recursive depth-first search because the assignment specifically requires recursion with backtracking and states that the shortest possible ladder is not required.

Using a queue-based breadth-first search may find a shortest path, but it would not meet the recursive-search requirement of this assignment.

I chose to keep the implementation focused on the required recursive algorithm instead.

### Testing and Verification

I verified the program myself rather than assuming that AI feedback meant the implementation was correct.

I compiled the complete program using:

```bash
javac *.java
```

and ran it using:

```bash
java WordLadderApp
```

I tested several situations, including:

* A valid pair that produces a word ladder.
* Words with different lengths.
* A word that does not exist in the dictionary.
* Empty input.
* Uppercase and lowercase input.
* Input with leading or trailing spaces.
* Additional successful word pairs provided by the assignment.

I also reviewed the recursive method to confirm that movement from one word to another occurs through a recursive call and that a failed path removes its current word from the ladder before another path is attempted.

### My Explanation of the Recursive Method

The `search` method in `WordLadderSolver` is recursive.

Each call represents one current word in the search.

The method adds that word to the active ladder and records it as visited.

If the current word is equal to the ending word, the base case has been reached and the search succeeds.

Otherwise, the method examines valid candidate words. When it finds an unvisited candidate that differs from the current word by exactly one letter, it calls `search` again using that candidate as the new current word.

If that recursive path fails, the program backtracks and tries another candidate.

### My Explanation of the Object-Oriented Design

The application separates its responsibilities among four classes.

`WordLadderApp` controls the program and communicates with the user.

`WordDictionary` reads and manages dictionary words.

`WordLadderSolver` performs the recursive search.

`WordUtils` performs word comparison.

Private instance variables demonstrate encapsulation because other classes cannot directly modify the internal collections.

Composition is demonstrated because `WordLadderSolver` receives and stores a `WordDictionary` object and uses that object instead of taking responsibility for loading the dictionary itself.

---

## Repository Contents

The final repository contains:

```text
WordLadderApp.java
WordDictionary.java
WordLadderSolver.java
WordUtils.java
words_small.txt
README.md
```

The repository does not include compiled `.class` files or IDE-generated folders.

---

## OnlineGDB Link

```text
https://www.onlinegdb.com/s/as/390020
```

---

## GitHub Repository

```text
https://github.com/Juan-ONESTAR/JavaCourseAssignments/tree/main/week-10-assignment-06
```
