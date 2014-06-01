
/**
 * This is a convenience class that helps support our programming-by-contract style
 * of coding. It does not use assert() so it incurs a minor run-time cost in production.
 * 
 * Note that we are ignoring i18n issues by embedding English strings.
 * 
 * Note there is a Google Guava class that can do some of this should we use that library.
 *
 */
public class Preconditions 
{
	static void isTrue(boolean condition)
	{
		if (!condition)
			throw new IllegalArgumentException("Precondition: is-true violation");
	}

	static void areTrue(boolean... conditions)
	{
		StringBuilder sb = new StringBuilder();
		
		for (boolean condition : conditions)
		{
			if (!condition)
			{
				sb.append("F");
				throw new IllegalArgumentException("Precondition: are-true violation " + sb);
			}
			
			sb.append("T ");
		}
	}

	public static void isNotNull(Object candidate)
	{
		if (candidate == null)
			throw new IllegalArgumentException("Precondition: is-not-null violation");
	}

	public static void areNotNull(Object... candidates)
	{
		StringBuilder sb = new StringBuilder();

		for (Object candidate : candidates)
		{
			if (candidate == null)
			{
				sb.append("0");
				throw new IllegalArgumentException("Precondition: are-not-null violation " + sb);
			}
			
			sb.append("!0 ");
		}
	}

	public static void isSubstantial(String name) 
	{
		if (name == null)
			throw new IllegalArgumentException("Precondition: null string is not substantial");
		if (name.isEmpty())
			throw new IllegalArgumentException("Precondition: empty string is not substantial");
	}
}
