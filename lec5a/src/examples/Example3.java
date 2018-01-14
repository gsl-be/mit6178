package examples;
class Vegetable {
    public String color() { return "green"; }
}
class Carrot extends Vegetable {
    @Override public String color() { return "orange"; }
}
public class Example3 {
    public static void main(String[] args) {
        Vegetable lettuce = new Vegetable();
        System.out.println(lettuce.color()); // green

        Carrot carrot = new Carrot();
        System.out.println(carrot.color()); // orange

        Vegetable carrotAsAVegetable = carrot;
        System.out.println(carrotAsAVegetable.color()); // orange
    }
}
