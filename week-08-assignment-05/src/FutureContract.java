import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class FutureContract implements VolatilityCalculator
{
    private final String contractName;
    private final String symbol;
    
    private final ArrayList<Double> prices;
    
    private final VolatilityCalculator volatilityCalculator;
    
    protected FutureContract(String contractName, String symbol, VolatilityCalculator volatilityCalculator)
    {
        this.contractName = validateText(contractName, "Contract Name");
        this.symbol = validateText(symbol, "Contract symbol");
        
        this.volatilityCalculator = Objects.requireNonNull(volatilityCalculator, "Volatility calculator cannot be null.");
        
        this.prices = new ArrayList<>();
    }
    
    protected FutureContract(FutureContract other, VolatilityCalculator volatilityCalculator)
    {
        Objects.requireNonNull(other, "The contract being copied cannot be null.");
        
        this.contractName = other.contractName;
        this.symbol = other.symbol;
        
        this.volatilityCalculator = Objects.requireNonNull(volatilityCalculator, "Volatility calculator cannot be null.");
        
        this.prices = new ArrayList<>(other.prices);
    }
    
    public void loadPricesFromFile(String filename) throws IOException
    {
        if (filename == null || filename.isBlank())
        {
            throw new IllegalArgumentException("Filename cannot be empty.");
        }
        
        ArrayList<Double> loadedPrices = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            int lineNumber = 0;
            while((line = reader.readLine()) != null)
            {
                String trimmedLine = line.trim();
                
                if (trimmedLine.isEmpty())
                {
                    continue;
                }
                
                try 
                {
                    double price = Double.parseDouble(trimmedLine);
                    
                    if (!Double.isFinite(price) || price <= 0.0)
                    {
                        throw new NumberFormatException("Price must be positive and finite.");
                    }
                    
                    loadedPrices.add(price);
                } catch (NumberFormatException exception)
                {
                    throw new IOException("Invalid price in " + filename + "at line " + lineNumber + ": \"" + line + "\"", exception);
                }
            }
        }
        
        if (loadedPrices.size() < 2)
        {
            throw new IOException(filename + " must contain at least two valid prices.");
        }
        
        prices.clear();
        prices.addAll(loadedPrices);
    }
    
    @Override
    public final double computeVolatility(List<Double> prices)
    {
        return volatilityCalculator.computeVolatility(prices);
    }
    
    public final double computeVolatility()
    {
        return computeVolatility(prices);
    }
    
    public final String getContractName()
    {
        return contractName;
    }
    
    public final String getSymbol()
    {
        return symbol;
    }
    
    public final int getPriceCount()
    {
        return prices.size();
    }
    
    public final List<Double> getPrices()
    {
        return Collections.unmodifiableList(prices);
    }
    
    @Override
    public String toString()
    {
        return contractName + " (" + symbol + ")";
    }
    
    private static String validateText(String value, String fieldName)
    {
        if (value == null || value.isBlank())
        {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        
        return value;
    }
}