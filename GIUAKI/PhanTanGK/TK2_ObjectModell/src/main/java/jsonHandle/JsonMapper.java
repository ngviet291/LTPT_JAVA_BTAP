package jsonHandle;

import entity.Category;
import entity.Question;
import entity.Quiz;
import jakarta.json.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonMapper {

    // Đọc JSON Array từ file
    public static JsonArray readFromFile(File file){
        try(JsonReader reader = Json.createReader(new FileReader(file))) {
            return reader.readArray();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi đọc file JSON", e);
        }
    }

    // Ghi JSON Array ra file
    public static void writeArr(File file, JsonArray jsonArray){
        try(JsonWriter writer = Json.createWriter(new FileWriter(file))) {
            writer.writeArray(jsonArray);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi ghi file JSON", e);
        }
    }

    // Đọc file -> List<Quiz>
    public static List<Quiz> readFile(File file){
        JsonArray arr = readFromFile(file);
        List<Quiz> quizzes = new ArrayList<>();

        for (JsonValue v : arr) {
            quizzes.add(convertToObject(v.asJsonObject()));
        }

        return quizzes;
    }

    // Convert JSON -> Object
    private static Quiz convertToObject(JsonObject jsonObject) {

        List<Question> questions = new ArrayList<>();

        JsonArray questionsArr = jsonObject.getJsonArray("questions");
        if (questionsArr != null) {
            for (JsonValue v : questionsArr) {
                JsonObject jo = v.asJsonObject();

                List<String> options = new ArrayList<>();
                JsonArray optionsArray = jo.getJsonArray("options");

                if (optionsArray != null) {
                    for (JsonValue a : optionsArray) {
                        options.add(((JsonString)a).getString());
                    }
                }

                Question question = Question.builder()
                        .question_id(jo.getString("question_id"))
                        .text(jo.getString("text"))
                        .options(options)
                        .correct_answer(jo.getString("correct_answer"))
                        .build();

                questions.add(question);
            }
        }

        JsonObject categoryOb = jsonObject.getJsonObject("category");

        return Quiz.builder()
                .quiz_id(jsonObject.getString("quiz_id"))
                .name(jsonObject.getString("name"))
                .score(jsonObject.getInt("score"))
                .questions(questions)
                .category(Category.builder()
                        .category_id(categoryOb.getString("category_id"))
                        .name(categoryOb.getString("name"))
                        .build())
                .build();
    }

    // Lọc theo category_id
    public static List<Quiz> quizList(String category_id){
        List<Quiz> quizzes = readFile(new File("json/quizzes.json"));

        List<Quiz> result = new ArrayList<>();
        for (Quiz q : quizzes) {
            if (q.getCategory().getCategory_id().equals(category_id)) {
                result.add(q);
            }
        }

        return result;
    }

    // Convert Object -> JSON
    public static JsonObject convertToObject(Quiz quiz){
//        JsonObjectBuilder builder = Json.createObjectBuilder();
//        JsonArrayBuilder jaQuestion = Json.createArrayBuilder();
//
//        for(Question q : quiz.getQuestions()){
//            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
//
//            for (String s : q.getOptions()){
//                arrayBuilder.add(s);
//            }
//
//            JsonObject joQues = Json.createObjectBuilder()
//                    .add("question_id", q.getQuestion_id())
//                    .add("text", q.getText())
//                    .add("options", arrayBuilder.build())
//                    .add("correct_answer", q.getCorrect_answer())
//                    .build();
//
//            jaQuestion.add(joQues);
//        }
//
//        JsonObject category = Json.createObjectBuilder()
//                .add("category_id", quiz.getCategory().getCategory_id())
//                .add("name", quiz.getCategory().getName())
//                .build();
//
//        return builder
//                .add("quiz_id", quiz.getQuiz_id()) // FIX
//                .add("name", quiz.getName())
//                .add("score", quiz.getScore())
//                .add("questions", jaQuestion)
//                .add("category", category)
//                .build();

        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for(Question question:quiz.getQuestions()){
                JsonArrayBuilder jaOptions = Json.createArrayBuilder();
                for(String i :question.getOptions()){
                    jaOptions.add(i);
                }
                JsonObject quizSub = Json.createObjectBuilder()
                        .add("question_id",question.getQuestion_id())
                        .add("text",question.getText())
                        .add("options",jaOptions.build())
                        .add("correct_answer",question.getCorrect_answer())
                        .build();
                arrayBuilder.add(quizSub);
            }
            JsonObject category = Json.createObjectBuilder()
                    .add("category_id",quiz.getCategory().getCategory_id())
                    .add("name",quiz.getCategory().getName())
                    .build();


        return
                builder.add("quiz_id",quiz.getQuiz_id())
                        .add("name",quiz.getName())
                        .add("score",quiz.getScore())
                        .add("questions",arrayBuilder.build())
                        .add("category",category)
                        .build();

    }

    // MAIN TEST
    public static void main(String[] args) {

        // Lấy quiz theo category
        List<Quiz> quizzes = quizList("C001");

        // In ra console
        System.out.println("=== DANH SÁCH QUIZ ===");
        for (Quiz q : quizzes) {
            System.out.println(q);
        }

        // Convert sang JSON Array
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Quiz quiz : quizzes) {
            arrayBuilder.add(convertToObject(quiz));
        }

        JsonArray jsonArray = arrayBuilder.build();

        // Ghi file
        writeArr(new File("json/deptrai.json"), jsonArray);


    }
}