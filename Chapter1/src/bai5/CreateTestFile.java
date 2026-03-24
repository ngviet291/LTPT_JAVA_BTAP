package bai5;

import java.io.*;

// Tạo file text lớn (~20MB) để test
public class CreateTestFile {
    public static void main(String[] args) {
        System.out.println("Đang tạo file test 20MB...");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("test_20mb.txt"))) {

            // Tạo 1 dòng dài khoảng 200 ký tự
            String line = "Đây là dòng số %d: Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                    "Ut enim ad minim veniam quis nostrud exercitation.\n";

            // Tạo khoảng 100,000 dòng = ~20MB
            for (int i = 1; i <= 100000; i++) {
                writer.write(String.format(line, i));

                // Hiển thị tiến trình
                if (i % 10000 == 0) {
                    System.out.println("Đã tạo: " + i + " dòng");
                }
            }

            System.out.println("\nHoàn thành! File 'test_20mb.txt' đã được tạo.");
            System.out.println("Bạn có thể mở file này bằng MySimpleNotepad");

        } catch (IOException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}
