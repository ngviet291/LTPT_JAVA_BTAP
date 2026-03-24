package com.ngphthinh.mapper;

import com.ngphthinh.entity.Category;
import com.ngphthinh.entity.Question;
import com.ngphthinh.entity.Quizz;
import jakarta.json.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QuizzModal {
    public static List<Quizz> toQuizzesList(File file){
        List<Quizz> quizzes = null;
        try(
                JsonReader reader = Json.createReader(new FileReader(file));
                ) {
            quizzes = new ArrayList<>();
            for(JsonValue value: reader.readArray()){
                quizzes.add(toQuizz(value));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return quizzes;
    }

    private static Quizz toQuizz(JsonValue value) {
        Quizz quizz = new Quizz();
        JsonObject  object = value.asJsonObject();
        String quiz_id= object.getString("quiz_id");
        String name =  object.getString("name");
        int score = object.getInt("score");
        List<Question> questionList = object.getJsonArray("questions").getValuesAs(v -> toQuestion(v));
        JsonObject categoryObj = object.getJsonObject("category");
        Category category = Category.builder()
                .id(categoryObj.getString("category_id"))
                .name(categoryObj.getString("name"))
                .build();

        return quizz.builder()
                .id(quiz_id)
                .name(name)
                .score(score)
                .questions(questionList)
                .category(category)
                .build();
    }
    private static Question toQuestion(JsonValue value) {
        Question question = new Question();
        JsonObject  object = value.asJsonObject();
        String question_id = object.getString("question_id");
        String text= object.getString("text");
        List<String> options = object.getJsonArray("options").getValuesAs(JsonValue::toString);
        String correct_answer = object.getString("correct_answer");

        return  question.builder()
                .id(question_id)
                .text(text)
                .options(options)
                .correctAnswer(correct_answer)
                .build();
    }
    public static void toFile(List<Quizz> quizzes,String fileName){
        try (
                JsonWriter jsonWriter= Json.createWriter(new FileWriter(fileName))
                ){
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for(Quizz quizz: quizzes){
                JsonArrayBuilder quesionsArray= Json.createArrayBuilder();

                for(Question question:quizz.getQuestions()){
                    JsonArrayBuilder options= Json.createArrayBuilder();
                    for(String option:question.getOptions()){
                        options.add(option);
                    }
                    JsonObjectBuilder questionsObj = Json.createObjectBuilder()
                            .add("question_id", question.getId())
                            .add("text", question.getText())
                            .add("options", options)
                            .add("correct_answer", question.getCorrectAnswer());

                    quesionsArray.add(questionsObj.build());
                }

                JsonObjectBuilder categoryObj = Json.createObjectBuilder()
                        .add("category_id", quizz.getCategory().getId())
                        .add("name", quizz.getCategory().getName());


                JsonObjectBuilder quizzObj = Json.createObjectBuilder()
                        .add("quiz_id",quizz.getId())
                        .add("name",quizz.getName())
                        .add("score",quizz.getScore())
                        .add("questions",quesionsArray)
                        .add("category",categoryObj);
                arrayBuilder.add(quizzObj.build());
            }
            jsonWriter.writeArray(arrayBuilder.build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Quizz> listQuizzes(String category){
        File file = new File("src/main/resources/quizzes.json");
        return toQuizzesList(file).stream().filter(e->{return e.getCategory().getName().equals(category);}).toList();
    }
    public  static List<Quizz> listQuizzesByScore(int minScore){
        File file = new File("src/main/resources/quizzes.json");
        return toQuizzesList(file).stream().filter(e->e.getScore()>=minScore).toList();
    }
    public static Quizz findQuizMaxQuestions() {
        return toQuizzesList(new File("src/main/resources/quizzes.json"))
                .stream()
                .max(Comparator.comparingInt(q -> q.getQuestions().size()))
                .orElse(null);
    }

    public static void main(String[] args) {
        File file = new File("src/main/resources/quizzes.json");
        System.out.println(toQuizzesList(file));
//        toFile(listQuizzes("Java Programming"), "src/main/resources/toQuizzesModel.json");

        toFile(listQuizzesByScore(90), "src/main/resources/toQuizzesModel.json");

    }
}
