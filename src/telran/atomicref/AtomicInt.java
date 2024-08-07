package telran.atomicref;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicInt
{
    
    private AtomicInteger low = new AtomicInteger(0);
    private AtomicInteger up = new AtomicInteger(0);

    public void setlow(int i) 
    {
    
        if (i > up.get()) throw new IllegalArgumentException("can't set low to " + i + " > up");
        low.set(i);
    }

    public void setup(int i)
    {
  
        if (i < low.get()) throw new IllegalArgumentException("can't set up to " + i + " < low");
        up.set(i);
    }

    public boolean isInRange(int i) 
    {
        return (i >= low.get() && i <= up.get());
    }

	public Integer getLow()
	{
		return low.get();
	}

	public Integer getUp() {
		return up.get();
	}

}

