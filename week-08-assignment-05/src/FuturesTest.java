// This is main()

import java.io.IOException;

public class FuturesTest
{
    public static void main(String[] args)
    {
        GoldFuture gold = new GoldFuture();
        CopperFuture copper = new CopperFuture();
        CoffeeFuture coffee = new CoffeeFuture();
        
        testContract(gold, "gold_prices.txt");
        testContract(copper, "copper_prices.txt");
        testContract(coffee, "coffee_prices.txt");
    }
    
    private static void testContract(FutureContract contract, String filename)
    {
        try
        {
            contract.loadPricesFromFile(filename);
            
            double volatility = contract.computeVolatility();
            
            System.out.println("----------------------------");
            System.out.println("Contract: " + contract);
            System.out.println("Prices loaded: " + contract.getPriceCount());
            System.out.printf("Daily volatility: %.6f%n", volatility);
            System.out.printf("Daily volatility percentage: %.4f%%%n", volatility * 100);
        } catch (IOException exception)
        {
            System.err.println("Could not process " + ": " + exception.getMessage());
        } catch (IllegalArgumentException exception)
        {
            System.err.println("Could not calculate volatility for " + contract + ": " + exception.getMessage());
        }
    }
}