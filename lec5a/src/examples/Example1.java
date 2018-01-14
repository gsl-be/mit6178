package examples;
class Animal {
    public String name;
    public Animal(String name) { this.name = name; }
    public void breathe() { System.out.println("in, out"); }
}
class Dog extends Animal {
    public String breed;
    public Dog(String name, String breed) {
        super(name);
        this.breed = breed;
    }
    public void bark() { System.out.println("woof!"); }
}
public class Example1 {
    public static void main(String[] args) {
        Dog fido = new Dog("Fido", "Golden Retriever");
        fido.breathe();
        fido.bark();
        System.out.println(fido.name);
        System.out.println(fido.breed);
    }
}
