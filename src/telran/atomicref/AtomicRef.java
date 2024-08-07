package telran.atomicref;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicRef 
{
    private final AtomicReference<IntPair> values = new AtomicReference<IntPair>(new IntPair(0, 0));

    private static class IntPair 
    {
        final int low;  
        final int up;
        private IntPair(int low, int up) 
        {
            this.low = low;
            this.up = up;
        }
    }

    

    public int getlow()
    {
        return values.get().low;
    }

    public void setlow(int low)
    {
        boolean ok=true;
    	while (ok)
        {
            IntPair oldv = values.get();
            if (low > oldv.up) throw new IllegalArgumentException("Can't set low to " + low + " > up");
            IntPair newv = new IntPair(low, oldv.up);
            ok=values.compareAndSet(oldv, newv);              
        }
    }

    public int getup()
    {
        return values.get().up;
    }

    public void setup(int up)
    {
    	boolean ok=true;
    	while (ok) 
    	{
            IntPair oldv = values.get();
            if (up < oldv.low) throw new IllegalArgumentException("Can't set up to " + up + " < low");
            IntPair newv = new IntPair(oldv.low, up);
            ok=values.compareAndSet(oldv, newv);
        }
    }
}
