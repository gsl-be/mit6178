package exercises;

class Appliance {
    public int serialNumber;

    public static int NUMBER_OF_APPLIANCES;

    /**
     * Create a new Appliance.
     *
     * Each new appliance gets the next serial number.
     */
    public Appliance() {
        serialNumber = NUMBER_OF_APPLIANCES;
        NUMBER_OF_APPLIANCES++;
    }
}

/*
 * DONE Create a class to represent your favorite type of appliance.
 *
 * x Rename the class to something else
 * x Make the class inherit from `Appliance`
 * x Give it a method unique to that kind of appliance
 * x In main, call that method and print out the serial number of the appliance.
 */
class Freezer extends Appliance {
	/*
	 * Prints the name of the object being cooled
	 * @param String name - the name of the object being cooled
	 * @return void  
	 */
	protected void freezes (String name) {
		System.out.println(name + " is being frozen to -20C");
	}
}

public class BasicInheritance {
    public static void main(String[] args) {
    	Freezer littleFreezer = new Freezer();
    	littleFreezer.freezes("Mario");
    	System.out.println(littleFreezer.serialNumber);
    	
    	Freezer anotherFreezer = new Freezer();
    	System.out.println(anotherFreezer.serialNumber);
    	
    }
}
