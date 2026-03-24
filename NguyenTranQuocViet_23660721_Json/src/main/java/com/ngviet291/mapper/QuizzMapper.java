/**
 * @ (#) QuizzMapper.java   1.0     23/3/2026
 * <p>
 * Copyright (c) 2026 IUH. All rights reserved
 */
package com.ngviet291.mapper;


import com.ngviet291.entity.Question;
import com.ngviet291.entity.Quizz;
import jakarta.json.Json;
import jakarta.json.stream.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author: Nguyen Tran Quoc Viet 
 * @version: 1.0
 * @created: 23/3/2026
 */

public class QuizzMapper {
    public  static List<Quizz> quizzList(File file){
        List<Quizz> quizzList = new ArrayList<>();
        try(
                JsonParser jsonParser= Json.createParser(new FileReader(file))
                ) {
               while (jsonParser.hasNext()){
                   JsonParser.Event event = jsonParser.next();
                   switch (event){
                       case START_OBJECT ->
                           quizzList.add(toQuizz(jsonParser));
                   }
               }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return quizzList;
    }

    private static Quizz toQuizz(JsonParser jsonParser) {
        Quizz quizz= new Quizz();
        String keyName = "";
        while (jsonParser.hasNext()){
            JsonParser.Event event = jsonParser.next();
            switch (event){
                case KEY_NAME -> keyName=jsonParser.getString();
                case VALUE_STRING -> {
                    if(keyName.equals("quiz_id")){
                        quizz.setQuiz_id(jsonParser.getString());
                    }
                    if(keyName.equals("name")){
                        quizz.setName(jsonParser.getString());
                    }
                }
                case VALUE_NUMBER -> {
                    if (keyName.equals("score")){
                        quizz.setScore(jsonParser.getInt());
                    }
                }
                case START_ARRAY -> {
                    quizz.setQuestions(questionsList(jsonParser));
                }
                case END_OBJECT -> {
                    return quizz;
                }
            }
        }
        return quizz;
    }

    private static List<Question> questionsList(JsonParser jsonParser) {
        List<Question> questions= new ArrayList<>();
        while (jsonParser.hasNext()){
            JsonParser.Event event= jsonParser.next();
            switch (event){
                case START_OBJECT -> {
                    questions.add(toQuestion(jsonParser));
                }
            }
        }
        return questions;
    }

    private static Question toQuestion(JsonParser jsonParser) {
        Question question = new Question();
        String keyName= "";
        while (jsonParser.hasNext()){
            JsonParser.Event event = jsonParser.next();
            switch (event){
                case KEY_NAME -> keyName= jsonParser.getString();
                case VALUE_STRING -> {
                    if(keyName.equals("question_id")){
                        question.setQuestion_id(jsonParser.getString());
                    }
                    if (keyName.equals("text")){
                        question.setText(jsonParser.getString());
                    }
                    if(keyName.equals("options")){
                        question.getOptions().add(jsonParser.getString());
                    }
                    if(keyName.equals("correct_answer")){
                        question.setCorrect_answer(jsonParser.getString());
                    }
                }
                case START_ARRAY -> {
                    question.setOptions(new ArrayList<>());
                }
            }
        }
        return question;
    }

    public static void main(String[] args) {
        File file = new File("src/main/resources/quizzes.json");
        System.out.println(quizzList(file));
    }
}
