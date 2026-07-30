import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class WordDictionary
{
    private final Set<String> words;
    
    public WordDictionary(String fileName) throws IOException
    {
        words = new HashSet<>();
        
        loadWords(fileName);
    }
    
    private void loadWords(String fileName) throws IOException
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            
            while ((line = reader.readLine()) != null)
            {
                String word = line.trim().toLowerCase();
                
                if (!word.isEmpty())
                {
                    words.add(word);
                }
            }
        }
    }
    
    public boolean contains(String word)
    {
        if (word == null)
        {
            return false;
        }
        
        return words.contains(word.toLowerCase());
    }
    
    public int size()
    {
        return words.size();
    }
    
    public ArrayList<String> getWordsOfLength(int length)
    {
        ArrayList<String> matchingWords = new ArrayList<>();
        
        for (String word : words)
        {
            if (word.length() == length)
            {
                matchingWords.add(word);
            }
        }
        
        return matchingWords;
    }
}