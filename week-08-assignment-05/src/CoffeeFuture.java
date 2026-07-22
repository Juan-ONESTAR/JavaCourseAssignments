public final class CoffeeFuture extends FutureContract
{
    public CoffeeFuture()
    {
        super("Coffee Future", "KC", new MonteCarloVolatility());
    }
    
    public CoffeeFuture(CoffeeFuture other)
    {
        super(other, new MonteCarloVolatility());
    }
}