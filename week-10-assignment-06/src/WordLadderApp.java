import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class WordLadderApp
{
    private static final String DICTIONARY_FILE = "words_small.txt";
    
    public static void main(String[] args)
    {
        try
        {
            WordDictionary dictionary = new WordDictionary(DICTIONARY_FILE);
            
            System.out.println("Word Ladder Game");
            System.out.println("--------------------");
            System.out.println("Dictionary loaded successfully!");
            System.out.println("Words loaded: " + dictionary.size());
            System.out.println();
            
            try (Scanner scanner = new Scanner(System.in))
            {
                System.out.print("Enter the starting word: ");
                String startWord = scanner.nextLine().trim().toLowerCase();
                if (startWord.isEmpty())
                {
                    System.out.println();
                    System.out.println("The starting word cannot be empty!");
                    
                    return;
                }
                
                System.out.print("Enter the ending word: ");
                String endWord = scanner.nextLine().trim().toLowerCase();
                if (endWord.isEmpty())
                {
                    System.out.println();
                    System.out.println("The ending word cannot be empty!");
                    
                    return;
                }

                
                if (startWord.length() != endWord.length())
                {
                    System.out.println();
                    System.out.println("A word ladder cannot be created because the words " + "have different lengths.");
                    
                    return;
                }
                
                if (!dictionary.contains(startWord))
                {
                    System.out.println();
                    System.out.println("\"" + startWord + "\" is not in the dictionary.");
                    
                    return;
                }
                
                if (!dictionary.contains(endWordWord))
                {
                    System.out.println();
                    System.out.println("\"" + endWord + "\" is not in the dictionary.");
                    
                    return;
                }
                
                WordLadderSolver solver = new WordLadderSolver(dictionary);
                
                List<String> ladder = solver.findLadder(startWord, endWord);
                
                System.out.println();
                
                if (ladder.isEmpty())
                {
                    System.out.println("No word ladder exists between " + startWord.toUpperCase() + " and " + endWord.toUpperCase() +".");
                }
                else
                {
                    System.out.println("Word ladder found:");
                    printLadder(ladder);
                }
            }
           
        }
    }
    
     private static void printLadder(List<String> ladder)
    {
        for (int index = 0; index.size(); index++)
        {
            System.out.print(ladder.get(index).toUpperCase());
            
            if (index < ladder.size() - 1)
            {
                System.out.print(" -> ");
            }
        }
        
        System.out.println();
    }
}