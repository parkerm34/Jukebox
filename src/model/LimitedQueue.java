package model;

/*
 * This class is a specific use of a queue, only allowing only the specified
 * number of elements in the list. This is done by overriding the add function
 * from the queue function already declared in java. If the size is over the
 * specified number, it will remove, or not allow the add to happen.
 */

import java.util.LinkedList;


public class LimitedQueue<E> extends LinkedList<E>
{
	private int limit;
	
	public LimitedQueue(int limit)
	{
		this.limit = limit;
	}
	
	@Override
	public boolean add(E o)
	{
		super.add(o);
		while(size() > limit)
			super.remove();
		return true;
	}
}
