public final class CopperFuture extends FutureContract
{
    public CopperFuture()
    {
        super("Copper Future", "HG", new MonteCarloVolatility());
    }
    
    public CopperFuture(CopperFuture other)
    {
        super(other, new MonteCarloVolatility());
    }
}