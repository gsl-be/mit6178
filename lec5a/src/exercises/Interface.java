package exercises;

interface Person {
    String getName();
    int getWage();
}

class Employee implements Person {
    private String name;
    private int wage;

    public Employee(String name, int wage) {
        this.name = name;
        this.wage = wage;
    }
    
    public String getName() {
    	return name;
    }
    
    public int getWage() {
    	return wage;
    }
}

class Manager implements Person {
    private String name;
    private int wage;

    public Manager(String name, int wage) {
        this.name = name;
        this.wage = wage;
    }
    
    public String getName() {
    	return name;
    }
    
    public int getWage() {
    	return wage;
    }
}

public class Interface {
    public static void main(String[] args) {
        /*
         * TODO Make this method compile. You may have to edit Person, Employee,
         * and/or Manager to get it to work.
         */

        Person ben = new Employee("Ben Bitdiddle", 15);
        Person alyssa = new Manager("Alyssa P. Hacker", 30);

        Person[] company = new Person[] { ben, alyssa };

        for (int i = 0; i < company.length; i++) {
            Person p = company[i];
            System.out.printf("%s makes $%d per hour.\n", p.getName(), p.getWage());
        }
    }
}