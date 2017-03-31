package com.github.jannled.fib;

public class Fibonacci 
{
	public static long recursive(long number)
	{
		if(number <= 1)
		{
			if(number == 1)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		else
		{
			return recursive(number - 1) + recursive(number - 2);
		}
	}
	
	public static long iterativ(long number)
	{
		if(number <= 1)
		{
			if(number == 0)
			{
				return 0;
			}
			else
			{
				return 1;
			}
		}
		else
		{
			long fib1 = 0;
			long fib2 = 1;
			for(long i=1; i<number; i++)
			{
				long neu = fib1 + fib2;
				fib1 = fib2;
				fib2 = neu;
			}
			return fib2;
		}
	}
}
