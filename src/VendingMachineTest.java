import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class VendingMachineTest
{
	/**
	 * Normally we'd use Mockito or better yet Spock to manage this kind of bookkeeping.
	 * Doing it manually here just for illustrative purposes.
	 * 
	 * @author ac
	 *
	 */
	static class TestableUI extends ConsoleUI
	{
		int calledShowTotalPayments = 0;
		int lastTotalPayments = -1;
		
		@Override
		public void showTotalPaymentsAs(int paymentsSoFar) 
		{
			super.showTotalPaymentsAs(paymentsSoFar);
			
			calledShowTotalPayments++;
			lastTotalPayments = paymentsSoFar;
		}

		int calledGiveProductOut = 0;
		StockedProduct lastProduct = null;
		
		@Override
		public void giveProductOut(StockedProduct portion) 
		{
			super.giveProductOut(portion);
			
			calledGiveProductOut++;
			lastProduct = portion;			
		}

		int calledGiveRefundOut = 0;
		List<Coin> lastRefund = null;
		
		@Override
		public void giveRefundOut(List<Coin> refund) 
		{
			super.giveRefundOut(refund);
			
			calledGiveRefundOut++;
			lastRefund = refund;
		}
	}

	final Product CHIPS = new Product("Chips", 70);
	final Product NUTS = new Product("Planters Peanuts", 75);
	final Product MARS = new Product("Mars Bar", 90);
	final Product GRANOLA = new Product("Granola Bar", 100);
	
	TestableUI myUI;
	VendingMachine myMachine;

	@Before
	public void setup()
	{
		myUI = new TestableUI();		
		myMachine = new VendingMachine(myUI);		

		myMachine.addStock(CHIPS, 1);
		myMachine.addStock(NUTS, 2);
		myMachine.addStock(MARS, 0);
	}
	
	@Test
	public void vendingMachineIsntEmpty()
	{
		Collection<StockedProduct> stock = myMachine.whatsAvailable();
		assertFalse(stock.isEmpty());
	}
	
	@Test(expected=VendingMachine.InsufficientFundsException.class)
	public void choosingProductWithoutPayingAtAllIsAnError()
	{
		myMachine.choose(CHIPS);
	}
	
	@Test(expected=VendingMachine.InsufficientFundsException.class)
	public void choosingProductWithoutPayingEnoughIsAnError()
	{
		myMachine.acceptPayment(Coin.DIME, Coin.DIME);
		myMachine.choose(CHIPS);
	}
	
	@Test(expected=VendingMachine.OutOfStockException.class)
	public void choosingAnOutOfStockProductIsAnError()
	{
		myMachine.choose(MARS);
	}
	
	@Test(expected=VendingMachine.UnknownProductException.class)
	public void choosingAnUnknownProductIsAnError()
	{
		myMachine.choose(GRANOLA);
	}
	
	@Test
	public void choosingWithExactChangeWorks()
	{		
		myMachine.acceptPayment(Coin.QUARTER, Coin.QUARTER, Coin.DIME, Coin.DIME);
		myMachine.choose(CHIPS);
		
		assertEquals(1, myUI.calledGiveProductOut);
	}
	
	@Test
	public void choosingWithOverPaymentWorksAndGivesChange()
	{		
		myMachine.acceptPayment(Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.DIME);
		List<Coin> change = myMachine.choose(NUTS);
		
		assertEquals(1, myUI.calledGiveProductOut);
		assertEquals(10, Coinage.sumOf(change));
	}
	
	@Test
	public void payingThenCancellingReturnsMoney()
	{		
		myMachine.acceptPayment(Coin.QUARTER, Coin.QUARTER, Coin.DIME, Coin.DIME);
		List<Coin> refund = myMachine.cancel();
		assertEquals(70, Coinage.sumOf(refund));
	}
	
	@Test
	public void refundWithoutPayingGivesNothing()
	{		
		List<Coin> refund = myMachine.cancel();
		assertEquals(0, Coinage.sumOf(refund));
	}
}
