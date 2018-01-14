package examples;
public class Example2 {
    public static void main(String[] args) {
        Animal myDog = new Dog("Fido", "Golden Retriever");
        myDog.breathe();
        // this line does not compile:
        // myDog.bark();
    }
}
