import java.io.PrintStream;
import java.util.Collection;
import java.util.List;


/**
 * Many developers tend to embed their UI in the vending machine class. This is
 * categorically bad practice, especially if you are trying to showcase your
 * design skills. 
 * 
 * Why in the world would you bind the console into your domain? There is no
 * room for sloppiness, as that is how your code base dies from a thousand cuts.
 * 
 * @author ac
 *
 */
public class ConsoleUI implements VendingMachine.VendingUI
{
	PrintStream out = System.out; // hook for easier testing
	
	@Override
	public void showTotalPaymentsAs(int paymentsSoFar) 
	{
		// Self-critique: avoid string concatenation like this. 
		// Use string formatting (better for memory usage and i18n)
		out.println("Total payments so far: " + paymentsSoFar);
	}

	@Override
	public void giveProductOut(StockedProduct portion) 
	{
		out.println("Vending: " + portion.getName());
	}

	@Override
	public void giveRefundOut(List<Coin> refund) 
	{
		if (refund.isEmpty())
			out.println("No refund");
		else
			printRefund(refund);				
	}

	private void printRefund(List<Coin> refund)
	{
		out.print("Refunding: ");
		
		for (Coin c : refund)
		{
			out.print(c.getName() +" ");									
		}
		
		out.println();
	}

	@Override
	public void showWhatsAvailable(Collection<StockedProduct> stock)
	{
		out.println("Available:");
		
		for (StockedProduct p : stock)
		{
			out.println("\t" + p.getName() + ": " + p.getPrice());
		}		
	}		
}
