package bai2.section1;

public class TestJoinThread {
    public static void main(String[] args) {
        new Thread(new YourTask()).start();
    }
}
