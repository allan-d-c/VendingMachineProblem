/**
 * A utilities class for parking string-related capabilities. Note there
 * are open source libraries to do stuff like this like Google Guava or Apache
 * Commons.
 * 
 * Self-critique: in general, you want to avoid writing utility classes that are collections of
 * static methods - this is widely accepted as a "code smell" and impedes the
 * testability of client code.
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
