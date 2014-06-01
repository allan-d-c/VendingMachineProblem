/**
 * A utilities class (ugh) for parking string-related capabilities. Note there
 * are open source libraries to do stuff like this like Google Guava or Apache
 * Commons.
 * 
 * Note: due to laziness, not unit tested
 * 
 * @author ac
 *
 */
public class Strings 
{
	public static String join(String... msgs)
	{
		StringBuilder sb = new StringBuilder();
		for (String m : msgs)
		{
			sb.append(m).append(", ");
		}
		
		return sb.toString();
	}	
}
