package examples;
interface SoundMaker {
    void makeASound();
}
class Bird implements SoundMaker {
    public void makeASound() { System.out.println("tweet"); }
    public void catchWorms() {}
}
class Human implements SoundMaker {
    public void makeASound() { System.out.println("howdy"); }
    public void philosophize() {}
}
public class Example6 {
    public static void main(String[] args) {
        SoundMaker[] things = new SoundMaker[] { new Bird(), new Human() };
        for (int i = 0; i < things.length; i++) {
            SoundMaker soundMaker = things[i];
            soundMaker.makeASound();
            // these two lines don't compile
            // soundMaker.catchWorms();
            // soundMaker.philosophize();
        }
    }
}
