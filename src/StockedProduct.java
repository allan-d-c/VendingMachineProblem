
/**
 * A Stocked Product is a Product plus a number of instances. The Product is the
 * type and the quantity is the number of instances. Clients can add/remove to
 * adjust the quantity, which obviously is zero or more.
 * 
 * Note: due to extreme laziness, there are no unit tests covering this
 * 
 * @author ac
 *
 */
public class StockedProduct
{
	private Product myProduct;
	private int myQuantity;

	public StockedProduct(Product p, int quantity)
	{
		Preconditions.isNotNull(p);
		Preconditions.isTrue(quantity >= 0);
		
		myProduct = p;
		myQuantity = quantity;
	}
	
	public String getName()
	{
		return myProduct.getName();
	}
	
	public int getPrice()
	{
		return myProduct.getPrice();
	}
	
	public boolean isAvailable()
	{
		return (myQuantity > 0);
	}
	
	/**
	 * Take the current stock and split out "quantity" amount, leaving less of
	 * the original stock.
	 * 
	 * @param quantity is how much to split off
	 * @return a new stock with said amount
	 */
	public StockedProduct split(int quantity)
	{
		Preconditions.isTrue(quantity >= 0);
		
		if (quantity > myQuantity)
			throw new IllegalArgumentException();
			
		myQuantity -= quantity;
		
		return new StockedProduct(myProduct, quantity);
	}

	public void add(int qty) 
	{
		Preconditions.isTrue(qty >= 0);
		
		myQuantity += qty;
	}
}
