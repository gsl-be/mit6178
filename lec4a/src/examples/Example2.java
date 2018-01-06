package examples;

class Toaster {
    private String color;

    private int length;
    private int width;
    private int height;

    private double dialSetting; // 0 to 10
    private double wattage;

    public static int numberOfToasters = 0;
    public static int piecesOfToast = 0;
    public static final int NUMBER_OF_COMPARTMENTS = 2;
    public static String[] availableColors = new String[] { "chartreuse", "peach" };

    public Toaster(int length, int width, int height, double wattage) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.wattage = wattage;

        this.color = availableColors[Math.random() > 0.5 ? 0 : 1];
        this.dialSetting = Math.random() * 10.0;

        numberOfToasters++;
    }

    public void makeToast() {
        System.out.printf("I made a piece of toast with dimensions %d x %d x %d\n", length, width, height);
        System.out.printf("It took %f minutes.\n", dialSetting);

        double heatEnergy = wattage * (60 * dialSetting);
        System.out.printf("It took %f J of electricity.  Hope you're happy.\n", heatEnergy);

        piecesOfToast++;
    }

    public void describe() {
        System.out.printf("I am a %s-colored toaster.\n", color);
    }

    public void setDial(double dialSetting) {
        this.dialSetting = dialSetting;
    }
}

public class Example2 {
    public static void main(String[] args) {
        Toaster braveLittleToaster = new Toaster(3, 4, 5, 1000);

        braveLittleToaster.describe();
        braveLittleToaster.makeToast();
        braveLittleToaster.setDial(5);
        braveLittleToaster.makeToast();

        System.out.println(Toaster.numberOfToasters);
        System.out.println(Toaster.piecesOfToast);
    }
}
