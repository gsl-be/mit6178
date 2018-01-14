package examples;
class Person {
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override public String toString() {
        return "(Person named " + name + ", " + age + " years old)";
    }
}
public class Example5 {
    public static void main(String[] args) {
        Person me = new Person("Aaron", 19);
        String meAsAString = me.toString();
        System.out.println(meAsAString);
        System.out.println(me); // println() automagically calls .toString() on Objects
    }
}
