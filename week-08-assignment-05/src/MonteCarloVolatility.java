import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MonteCarloVolatility implements VolatilityCalculator
{
    private static final int DEFAULT_SIMULATION_COUNT = 10_000;
    
    private final int simulationCount;
    private final Random random;
    
    public MonteCarloVolatility()
    {
        this(DEFAULT_SIMULATION_COUNT, new Random());
    }
    
    public MonteCarloVolatility(int simulationCount, Random random)
    {
        if (simulationCount <0)
        {
            throw new IllegalArgumentException("Simulationcount must be greater than zero.");
        }
        
        this.simulationCount = simulationCount;
        this.random = Objects.requireNonNull(random, "Random-number generator cannot be null");
    }
    
    @Override
    public double computeVolatility(List<Double> prices)
    {
        validatePrices(prices);
        
        List<Double> historicalReturns = calculateReturns(prices);
        
        double historicalMean = calculateMean(historicalReturns);
        
        double historicalStandardDeviation = calculatePopulationStandardDeviation(historicalReturns, historicalMean);
        
        if (historicalStandardDeviation == 0.0)
        {
            return 0.0;
        }
        
        List<Double> simulatedReturns = simulateReturns(historicalMean, historicalStandardDeviation);
        
        double simulatedMean = calculateMean(simulatedReturns);
        
        return calculatePopulationStandardDeviation(simulatedReturns, simulatedMean);
    }
    
    private List<Double> calculateReturns(List<Double> prices)
    {
        List<Double> returns = new ArrayList<>(prices.size() - 1);
        
        for (int index = 1; index < prices.size(); index++)
        {
            double previousPrice = prices.get(index - 1);
            double currentPrice = prices.get(index);
            
            double dailyReturn = (currentPrice - previousPrice) / previousPrice;
            
            returns.add(dailyReturn);
        }
        
        return returns;
    }
    
    private List<Double> simulateReturns(double historicalMean, double historicalStandardDeviation)
    {
        List<Double> simulatedReturns = new ArrayList<>(simulationCount);
        
        for (int simulation = 0; simulation < simulationCount; simulation++)
        {
            double simulatedReturn = historicalMean + historicalStandardDeviation * random.nextGaussian();
            
            simulatedReturns.add(simulatedReturn);
        }
        
        return simulatedReturns;
    }
    
    private double calculateMean(List<Double> values)
    {
        double total = 0.0;
        
        for (double value : values)
        {
            total += value;
        }
        
        return total / values.size();
    }
    
    private double calculatePopulationStandardDeviation(List<Double> values, double mean)
    {
        double squaredDifferenceTotal = 0.0;
        
        for (double value : values)
        {
            double differenceFromMean = value - mean;
            
            squaredDifferenceTotal += differenceFromMean * differenceFromMean;
        }
        
        double variance = squaredDifferenceTotal / values.size();
        
        return Math.sqrt(variance);
    }
    
    private void validatePrices(List<Double> prices)
    {
        Objects.requireNonNull(prices, "Price list cannot be null.");
        
        if (prices.size() < 2)
        {
            throw new IllegalArgumentException("At least two prices are required to calculate volatility.");
        }
        
        for (int index = 0; index < prices.size(); index++)
        {
            Double price = prices.get(index);
            
            if (price == null)
            {
                throw new IllegalArgumentException("Price at index " + index + "cannot be null.");
            }
            
            if (!Double.isFinite(price))
            {
                throw new IllegalArgumentException("Price at index " + index + " must be greater than zero.");
            }
            
            if (price < 0.0)
            {
                throw new IllegalArgumentException("Price at index " + index + " must be greater than zero.");
            }
        }
    }
}