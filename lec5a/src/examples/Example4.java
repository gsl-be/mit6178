package examples;

class Parent {
    public int generation() { return 0; }
}
class Child extends Parent {
    @Override public int generation() { return super.generation() + 1; }
}
class Grandchild extends Child {
    @Override public int generation() { return super.generation() + 1; }
}
public class Example4 {
    public static void main(String[] args) {
        int generation = new Grandchild().generation();
        System.out.println(generation); // 2
    }
}
