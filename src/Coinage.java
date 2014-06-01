import java.util.ArrayList;
import java.util.List;

/**
 * This class embodies all things related to dealing with multiple coins,
 * like creating a new collection and doing coin math.
 * 
 * Note: due to extreme laziness, we are foregoing unit testing this class.
 * 
 * @author ac
 *
 */
public class Coinage
{
	/**
	 * Abstractions often are sloppy and incomplete in that they completely ignore error
	 * flow. Completing the abstraction includes defining exceptions that make sense in
	 * the context of the abstraction. Don't just let low level exceptions leak out
	 * through multiple abstractions. 
	 * 
	 * @author ac
	 *
	 */
	@SuppressWarnings("serial")
	public static class NoExactConversionExcetion extends RuntimeException
	{
		public NoExactConversionExcetion(int value)
		{			
			super(String.valueOf(value));
		}		
	}
	
	public static final List<Coin> NOTHING = new ArrayList<Coin>();

	public static List<Coin> newEmpty()
	{
		return new ArrayList<Coin>();	
	}
	
	public static int sumOf(List<Coin> coins)
	{
		Preconditions.isNotNull(coins);
		
		int sum = 0;
		
		for (Coin c : coins)
		{
			sum += c.getValue();
		}
		
		return sum;
	}
	
	public final static List<Coin> asCoins(int total)
	{
		Preconditions.isTrue(total >= 0);
		
		List<Coin> results = newEmpty();
		
		for (Coin c : Coin.LARGETOSMALL)
		{
			int numCoins = total / c.getValue();
			
			for (int i = 0; i< numCoins; i++)
			{
				results.add(c);
			}
			
			total = total % c.getValue();
		}
		
		if (total != 0)
			throw new NoExactConversionExcetion(total);
		
		return results;
	}
}
