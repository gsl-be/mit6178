package exercises;

class Ship {
    private int length;

    public Ship(int length) {
        this.length = length;
    }

    public int getLength() { return length; }

    public boolean isSunk(int hits) {
        return hits >= length;
    }
}

/*
 * TODO Create a class for submarines.
 *
 * - Make it inherit from `Ship`
 * - Give it a constructor:
 *   - `length` is private, so you won't be able to initialize it directly.
 *   - Use `super()` to call `Ship's` constructor from your class's constructor
 * - Override the isSunk() method. Submarines should print out "SOS" before
 *   reporting that they are sunk.
 *   - Don't recompute line 13. Use `super`!
 */

class Submarines extends Ship {
	/*
	 * Submarine constructor
	 */
	public Submarines(int length) {
		super(length);
	}
	@Override public boolean isSunk(int hits) {
		boolean sunk = super.isSunk(hits);
		if(sunk) {
			System.out.println("SOS");
		}
		return sunk;
	}
}

public class OverrideAndSuper {
    public static void main(String[] args) {
        /*
         * TODO Create an instance of your new class and use it.
         *
         * - Create an instance of your new class.
         * - Call the isSunk() method with various arguments.
         */
    	Submarines nemo = new Submarines(3);
    	System.out.println(nemo.getLength());
    	System.out.println(nemo.isSunk(1));
    	System.out.println(nemo.isSunk(4));
    }
}
