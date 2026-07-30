import java.util.ArrayList;
import java.util.List;

public final class WordUtils
{
    private static final char FIRST_LETTER = 'a';
    private static final char LAST_LETTER = 'z';
    
    private WordUtils()
    {
    }
    public static boolean differsByOneLetter(String first, String second)
    {
        if (first == null || second == null)
        {
            return false;
        }
        
        if (first.length() != second.length())
        {
            return false;
        }
        
        int differences = 0;
        
        for (int index = 0; index < first.length(); index++)
        {
            if (first.charAt(index) != second.charAt(index))
            {
                differences++;
                
                if (differences > 1)
                return false;
            }
        }
        
        return differences == 1;
    }
}