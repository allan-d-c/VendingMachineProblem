import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VendingMachine
{
	/** ------------------------------------------------------------------
	 * Note that a complete abstraction includes the errors, meaning throw
	 * exceptions that match the abstraction. This also helps with unit testing.
	 *
	 */
	@SuppressWarnings("serial")
	public static class VendingMachineException extends RuntimeException
	{
		public VendingMachineException(String... msgs)
		{
			super(Strings.join(msgs));
		}
	}

	@SuppressWarnings("serial")
	public static class UnknownProductException extends VendingMachineException
	{
		public UnknownProductException(String p)
		{
			super("ProductName", p);
		}
	}
	
	@SuppressWarnings("serial")
	public static class OutOfStockException extends VendingMachineException
	{
		public OutOfStockException(String p)
		{
			super("ProductName", p);
		}
	}
	
	@SuppressWarnings("serial")
	public static class InsufficientFundsException extends VendingMachineException
	{
		public InsufficientFundsException(StockedProduct p, int paid)
		{
			super("ProductName", p.getName(), "ProductPrice", String.valueOf(p.getPrice()), "Paid", String.valueOf(paid));
		}
	}
	
	/** --------------------------------------------------------------------------
	 * Interface for how this vending machine presents information to the consumer.
	 * 
	 */
	public interface VendingUI
	{
		void showTotalPaymentsAs(int paymentsSoFar);
		void giveProductOut(StockedProduct portion);
		void giveRefundOut(List<Coin> refund);
		void showWhatsAvailable(Collection<StockedProduct> result);		
	}
	
	// ------------------------------------------------------------------------
	
	VendingUI myUI;
	Map<String,StockedProduct> myStock = new HashMap<String,StockedProduct>();
	List<Coin> myPayments = Coinage.newEmpty();
	
	public VendingMachine(VendingUI ui)
	{
		myUI = ui;
	}
	
	public void addStock(Product p, int qty)
	{
		Preconditions.isTrue(qty >= 0);
		
		if (myStock.containsKey(p.getName()))
		{
			StockedProduct stock = myStock.get(p.getName());
			stock.add(qty);
		}
		else
		{
			StockedProduct stock = new StockedProduct(p, qty);
			myStock.put(stock.getName(), stock);
		}
	}
	
	public Collection<StockedProduct> whatsAvailable()
	{
		Collection<StockedProduct> result = myStock.values();
		myUI.showWhatsAvailable(result);
		return result;
	}
	
	public void acceptPayment(Coin... coins)
	{		
		myPayments.addAll(Arrays.asList(coins));

		int paymentsSoFar = Coinage.sumOf(myPayments);		
		myUI.showTotalPaymentsAs(paymentsSoFar);
	}
	
	public List<Coin> choose(Product product)
	{
		return choose(product.getName());
	}
	
	public List<Coin> choose(String productName)
	{		
		Preconditions.isSubstantial(productName);
		
		StockedProduct desired = myStock.get(productName);
		if (desired == null)
			throw new UnknownProductException(productName);
		
		if (!desired.isAvailable())
			throw new OutOfStockException(desired.getName());
		
		int paymentValue = Coinage.sumOf(myPayments);
		if (paymentValue < desired.getPrice())
			throw new InsufficientFundsException(desired, paymentValue);
		
		vend(desired);

		myPayments = Coinage.newEmpty();
		return refundAnyOverpayment(paymentValue, desired.getPrice());
	}
	
	private void vend(StockedProduct desired) 
	{
		StockedProduct portion = desired.split(1);
		myUI.giveProductOut(portion);
	}

	private List<Coin> refundAnyOverpayment(int paid, int cost)
	{
		Preconditions.isTrue(paid >= cost);		
		return Coinage.asCoins(paid - cost);
	}
	
	public List<Coin> cancel()
	{
		List<Coin> refund = myPayments;
		myPayments = Coinage.newEmpty();
		myUI.giveRefundOut(refund);
		return refund;
	}
}
