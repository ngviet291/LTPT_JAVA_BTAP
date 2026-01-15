package bai2.section4;

public class Main {
    public static void main(String[] args) {
        Storage storage= new Storage();
        Counter counter= new Counter(5, storage);
        Printer printer= new Printer(storage);
    }
}
