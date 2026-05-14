package app;

import Service.ArticleService;
import Service.ReviewService;
import dto.ArticleDTO;
import dto.ListArticleDTO;
import dto.ReviewDTO;
import entity.Article;
import entity.Review;
import entity.ReviewSuggestion;
import network.CommandType;
import network.Request;
import network.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Scanner;

public class Client {
    static void main() {
        try (
                Socket socket = new Socket("DESKTOP-VIET",0721);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois= new ObjectInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in);
        ){

            while (true){
                System.out.println("1.tạo mới một phản biện (review) của một bài báo từ một người phản biện nào đó.");
                System.out.println("2. cập nhật một phản biện đã tồn tại.");
                System.out.println("3. Lấy danh sách các bài báo theo năm (year), tổ chức (institution name) và hình thức đề xuất phản biện (suggestion).");
                System.out.println("4. Tính trung bình số ngày phản biện (reviewDays) trên toàn bộ các phản biện trong hệ thống");
                int choice = scanner.nextInt();
                Request request = null;
                switch (choice){
                    case 1->{
                        ReviewDTO reviewDTO = ReviewDTO.builder().id("R0343").comment("asdasd").reviewer("asdasdaw")
                                .reviewDays(29).reviewDate(LocalDate.of(2026,05,14))
                                .articleDTO(ArticleDTO.builder().id("AR005").build()).build();
                        request = Request.builder().commandType(CommandType.ADD_REVIEW)
                                .data(reviewDTO).build();
                    }
                    case 2->{
                        ReviewDTO reviewDTO = ReviewDTO.builder().id("R006").comment("asdasd").reviewer("asdasdaw").reviewDays(29).reviewDate(LocalDate.of(2026,05,14)).build();
                        request = Request.builder().commandType(CommandType.UPDATE_REVIEW)
                                .data(reviewDTO).build();

                    }
                    case 3->{
                        ListArticleDTO listArticleDTO = ListArticleDTO.builder().year(2024).institutionName("Vietnam").reviewSuggestion(ReviewSuggestion.ACCEPT).build();
                        request= Request.builder().commandType(CommandType.LIST_ARTICLES_BY_YIS).data(listArticleDTO).build();
                    }
                    case 4->{
                        request = Request.builder().commandType(CommandType.CALCULATE_REVIEW_DAY).build();
                    }

                }
                oos.writeObject(request);
                oos.flush();
                Response response = (Response) ois.readObject();
                System.out.println(response);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
