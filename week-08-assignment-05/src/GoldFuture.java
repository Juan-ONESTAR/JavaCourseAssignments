public final class GoldFuture extends FutureContract
{
    public GoldFuture()
    {
        super("Gold Future", "GC", new MonteCarloVolatility());
    }
    
    public GoldFuture(GoldFuture other)
    {
        super(other, new MonteCarloVolatility());
    }
}