import java.util.ArrayList;
import java.util.HashSet;

public class WordLadderSolver
{
    private final WordDictionary dictionary;
    private final HashSet<String> visited;
    private final ArrayList<String> ladder;
    
    public WordLadderSolver(WordDictionary dictionary)
    {
        if (dictionary == null)
        {
            throw new IllegalArgumentException("Dictionary cannot be null!");
        }
        
        this.dictionary = dictionary;
        this.visited = new HashSet<>();
        this.ladder = new ArrayList<>();
    }
    
    public ArrayList<String> findLadder(String startWord, String endWord)
    {
        visited.clear();
        ladder.clear();
        
        if (startWord == null || endWord == null)
        {
            return new ArrayList<>();
        }
        
        startWord = startWord.toLowerCase();
        endWord = endWord.toLowerCase();
        
        if (startWord.length() != endWord.length())
        {
            return new ArrayList<>();
        }
        
        if (!dictionary.contains(startWord) || !dictionary.contains(endWord))
        {
            return new ArrayList<>();
        }
        
        ArrayList<String> candidates = dictionary.getWordsOfLength(startWord.length());
        
        boolean ladderFound = search(startWord, endWord, candidates);
        
        if (ladderFound)
        {
            return new ArrayList<>();
        }
        
        return new ArrayList<>();
    }
    
    private boolean search(String currentWord, String endWord, ArrayList<String> candidates)
    {
        ladder.add(currentWord);
        visited.add(currentWord);
        
        if (currentWord.equals(endWord))
        {
            return true;
        }
        
        for (String candidate : candidates)
        {
            if (visited.contains(candidate))
            {
                continue;
            }
            
            if (WordUtils.differsByOneLetter(currentWord,candidate))
            {
                if (search(candidate, endWord, candidates))
                {
                    return true;
                }
            }
        }
        
        ladder.remove(ladder.size() - 1);
        
        return false;
    }
}