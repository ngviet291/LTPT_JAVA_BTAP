package com.ngphthinh.mapper;

import com.ngphthinh.entity.Category;
import com.ngphthinh.entity.Question;
import com.ngphthinh.entity.Quizz;
import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuizzMapper {
    public static List<Quizz> quizzList(File file){
        List<Quizz> quizzes = new ArrayList<>();
        try (
                JsonParser jsonParser= Json.createParser(new FileReader(file))
                ){
            while (jsonParser.hasNext()){
                JsonParser.Event event = jsonParser.next();
                switch (event){
                    case START_OBJECT -> quizzes.add(toQuizz(jsonParser));
                }

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return quizzes;
    }

    private static Quizz toQuizz(JsonParser jsonParser) {
        Quizz quizz = new Quizz();
        String keyName = "";
        while (jsonParser.hasNext()){
            JsonParser.Event event = jsonParser.next();
            switch (event){
                case KEY_NAME -> keyName=jsonParser.getString();
                case VALUE_STRING -> {
                    if(keyName.equals("quiz_id")){
                        quizz.setId(jsonParser.getString());
                    }
                    if(keyName.equals("name")){
                        quizz.setName(jsonParser.getString());
                    }

                }
                case VALUE_NUMBER -> {
                    if(keyName.equals("score")){
                        quizz.setScore(jsonParser.getInt());
                    }
                }
                case START_ARRAY -> {
                    if(keyName.equals("questions")){
                        quizz.setQuestions(new ArrayList<>());
                    }
                }

                case START_OBJECT -> {
                    if(keyName.equals("category")){

                        quizz.setCategory(toCategory(jsonParser));
                    }
                    if(keyName.equals("questions")){
                        quizz.getQuestions().add(toQuestions(jsonParser));
                    }
                }
                case END_OBJECT -> {
                    return quizz;
                }
            }
        }
        return quizz;
    }

    private static Category toCategory(JsonParser jsonParser) {
        Category category = new Category();
        String keyName= "";
        while (jsonParser.hasNext()){
            JsonParser.Event event = jsonParser.next();
            switch (event){
                case KEY_NAME -> keyName= jsonParser.getString();
                case VALUE_STRING -> {
                    if(keyName.equals("category_id")){
                        category.setId(jsonParser.getString());
                    }

                    if(keyName.equals("name")){
                        category.setName(jsonParser.getString());
                    }
                }
                case END_OBJECT ->
                {
                    return category;
                }
            }
        }
        return category;
    }

    private static Question toQuestions(JsonParser jsonParser) {
        Question question= new Question();
        String keyName="";
        while (jsonParser.hasNext()){
            JsonParser.Event event= jsonParser.next();
            switch (event){
                case KEY_NAME -> keyName= jsonParser.getString();
                case VALUE_STRING -> {
                    if(keyName.equals("question_id")){
                        question.setId(jsonParser.getString());
                    }
                    if(keyName.equals("text")){
                        question.setText(jsonParser.getString());
                    }
                    if(keyName.equals("options")){
                        question.getOptions().add(jsonParser.getString());
                    }
                    if(keyName.equals("correct_answer")){
                        question.setCorrectAnswer(jsonParser.getString());
                    }
                }
                case START_ARRAY -> {
                    if(keyName.equals("options")){
                        question.setOptions(new ArrayList<>());
                    }
                }
                case END_OBJECT -> {
                    return question;
                }

            }

        }
        return question;
    }
    public static  void toFile(List<Quizz> quizzes,String fileName){
        try (
                JsonGenerator jsonGenerator= Json.createGenerator(new FileWriter(fileName))
                ){
                jsonGenerator.writeStartArray();
                for(Quizz quizz: quizzes){

                        jsonGenerator.writeStartObject();
                    jsonGenerator.write("quiz_id",quizz.getId());
                    jsonGenerator.write("name",quizz.getName());
                    jsonGenerator.write("score",quizz.getScore());

                    jsonGenerator.writeStartArray("questions");
                        for(Question question: quizz.getQuestions()){
                            jsonGenerator.writeStartObject();
                            jsonGenerator.write("question_id",question.getId());
                            jsonGenerator.write("text",question.getText());
                            jsonGenerator.writeStartArray("options");
                            for(String option: question.getOptions()){
                                jsonGenerator.write(option);
                            }
                            jsonGenerator.writeEnd();
                            jsonGenerator.write("correct_answer",question.getCorrectAnswer());

                            jsonGenerator.writeEnd();
                        }
                    jsonGenerator.writeEnd();
                        jsonGenerator.writeStartObject("category");
                        jsonGenerator.write("category_id",quizz.getCategory().getId());
                        jsonGenerator.write("name",quizz.getCategory().getName());
                        jsonGenerator.writeEnd();
                    jsonGenerator.writeEnd();



                }
                jsonGenerator.writeEnd();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Quizz> listQuizzes(String category){
        File file= new File("src/main/resources/quizzes.json");
        return quizzList(file).stream().filter(e->{return e.getCategory().getName().equals(category);}).toList();
    }
}