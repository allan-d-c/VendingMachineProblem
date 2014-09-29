
/**
 * This abstraction embodies an item that has a name and a price. For simplicity, the
 * price is just a basic integer. In a real solution, you'd have to consider using
 * currency abstractions.
 *  
 *  Note: due to extreme laziness, there are no unit tests covering this class
 * 
 * @author ac
 *
 */
public class Product
{
	private String myName;
	private int myPrice;
	
	public Product(String name, int price)
	{
		// Note use of preconditions is part of "Programming By Contract" (google Bertand Meyer)
		
		Preconditions.isSubstantial(name);
		Preconditions.isTrue(price >= 0);
		
		myName = name;
		myPrice = price;
	}
	
	public String getName()
	{
		return myName;
	}
	
	// Self-critique: there is a connection between the int price and the int value in coins.
	// This could be made clearer.

	public int getPrice()
	{
		return myPrice;
	}
}
