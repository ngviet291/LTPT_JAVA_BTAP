package network;

import Service.ArticleService;
import Service.ReviewService;
import dto.ArticleDTO;
import dto.ListArticleDTO;
import dto.ReviewDTO;
import entity.Review;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandle implements Runnable {
    private Socket socket;
    public ClientHandle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois= new ObjectInputStream(socket.getInputStream());
                ){
            ArticleService articleService = new ArticleService();
            ReviewService reviewService = new ReviewService();
                while (true){
                    Request request = (Request) ois.readObject();
                    Response response = null;
                    switch (request.getCommandType()){
                        case ADD_REVIEW -> {
                            response=Response.builder().data(reviewService.addNewReview((ReviewDTO) request.getData())).build();
                        }
                        case UPDATE_REVIEW -> {
                            response = Response.builder().data(reviewService.updateReview((ReviewDTO) request.getData())).build();
                        }
                        case LIST_ARTICLES_BY_YIS -> {
                           ListArticleDTO listArticleDTO = (ListArticleDTO) request.getData();
                            response = Response.builder().data(articleService.listArticlesByYIS(listArticleDTO.getYear(), listArticleDTO.getInstitutionName(), listArticleDTO.getReviewSuggestion())).build();
                        }
                        case CALCULATE_REVIEW_DAY -> {
                            response =Response.builder().data(reviewService.calculateAverageReviewDays()).build();
                        }
                    }
                    oos.writeObject(response);
                    oos.flush();
                }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
