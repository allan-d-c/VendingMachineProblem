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
	 * through multiple abstractions. The use of specific exceptions also can make unit
	 * tests crisper.
	 * 
	 * @author ac
	 *
	 */
	@SuppressWarnings("serial")
	public static class NoExactConversionException extends RuntimeException
	{
		public NoExactConversionException(int value)
		{			
			super(String.valueOf(value));
		}		
	}
	
	// Self-critique: most of the use of List<> in the type signatures below should be Collection<>

	/**
	 * Not uncommon to use static final instances to encapsulate special values, like
	 * an empty coin collection as below. This can improve the readability of client
	 * code and can help hide the collection type.
	 */
	public static final List<Coin> NOTHING = new ArrayList<Coin>();

	public static List<Coin> newEmpty()
	{
		return new ArrayList<Coin>();	
	}
	
	/**
	 * Return the low-level value of the sum of all of the given coins
	 * 
	 * @param input set of coins
	 * @return sum
	 */
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
	
	/**
	 * Take the given value total and return the set of coins that sum to that
	 * value, with priority given to the largest valued coins (minimizes the
	 * population of returned coins).
	 * 
	 * @param input desired value total
	 * @return list of coins forming input value
	 * @throws NoExactConversionException if the total cannot be realized
	 */
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
			throw new NoExactConversionException(total);
		
		return results;
	}
}
