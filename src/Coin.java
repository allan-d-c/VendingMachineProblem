
/**
 * Coin class embodies a name (ignoring i18n issues) and a value. The value is a 
 * fine-grained unit that is common to all coins. The US coins are below and the 
 * value is based on the penny.
 * 
 * We are not worrying about non-US coins and are trying to minimize our assumption
 * that we are using US coins; we don't want client code to assume US coins.
 * 
 * With apologies, due to laziness/lack of time, we are foregoing unit testing 
 * of this and other low level classes.
 * 
 * @author ac
 *
 */
public class Coin
{
	// Self-critique: probable internationalization issues here. Resource bundles!	 

	public final static Coin PENNY = new Coin("Penny", 1);
	public final static Coin NICKEL = new Coin("Nickel", 5);
	public final static Coin DIME = new Coin("Dime", 10);
	public final static Coin QUARTER = new Coin("Quarter", 25);
	
	// Highest value to low - used in converting value into coins
	//
	public final static Coin[] LARGETOSMALL = { QUARTER, DIME, NICKEL, PENNY };
	
	private String myName;
	private int myValue;
	
	// Private constructor is a common idiom used to control the population
	// of instances. This is usually coupled with a builder or with a fixed
	// number of final instances, as is done above.
	//
	private Coin(String name, int price)
	{
		myName = name;
		myValue = price;
	}
	
	public String getName()
	{
		return myName;
	}
	
	public int getValue()
	{
		return myValue;
	}
}
