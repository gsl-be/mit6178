package examples;

class Cat {
    public static int numberOfCats = 0;

    public String name;
    private String favoriteFood;
    private int age;

    public Cat(String name, String favoriteFood, int age) {
        this.name = name;
        this.favoriteFood = favoriteFood;
        this.age = age;

        numberOfCats++;
    }

    public static String whatDoesTheCatSay() {
        return "meow";
    }

    public static String species() {
        return "Feline something";
    }

    public void introduceSelf(String toWhom) {
        System.out.printf("Hello, %s.\n", toWhom);
        System.out.printf("My name is %s, I am %d years old, and my favorite food is %s.\n",
                          this.name,
                          this.age,
                          this.favoriteFood);
        System.out.printf("I am a %s, and I go %s\n", species(), whatDoesTheCatSay());
        System.out.printf("There are %d other cats.\n", numberOfCats - 1);
    }

    public void whoAmI() {
        System.out.printf("My name is %s.\n", name);
    }

    public void greet(Cat otherCat) {
        System.out.printf("Hi, %s.  I'm %s.\n", otherCat.name, name);
    }
}

public class Example3 {

    public static void main(String[] args) {
        Cat someCat = new Cat("Bob", "Chicken", -1);
        Cat garfield = new Cat("Garfield", "Lasagna", 38);
        garfield.introduceSelf("Mrs President");
        System.out.println("There are " + Cat.numberOfCats + " cats");

        String sound = Cat.whatDoesTheCatSay();
        System.out.println(sound);
    }

}
