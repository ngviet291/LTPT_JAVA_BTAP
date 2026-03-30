/*
 * @ (#) JsonStream.java     1.0    3/23/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package iuh.fit.mapper;


/*
 * @description
 * @author:NguyenTruong
 * @date:  3/23/2026
 * @version:    1.0
 */

import iuh.fit.entity.Category;
import iuh.fit.entity.Question;
import iuh.fit.entity.Quiz;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonStream {
   public static List<Quiz> read(File file){
       String current ="";
       String keyName ="";
       List<Quiz> quizzes=null;
       Quiz quiz =null;
       Question question =null;
       Category category=null;

       try(
               FileReader reader = new FileReader(file);
                JsonParser parser = Json.createParser(reader);

       ){
            while(parser.hasNext()){
                JsonParser.Event event = parser.next();
                switch (event){

                    case START_ARRAY -> {
                        if(current.equals("")){
                            quizzes = new ArrayList<>();
                            current ="quizzes";
                        }else if(keyName.equals("questions")){
                            current="questions";
                            quiz.setQuestions(new ArrayList<>());
                        } else if ("options".equals(keyName)) {
                            current="options";
                            question.setOptions(new ArrayList<>());
                        }
                    }

                    case START_OBJECT -> {
                        if(current.equals("quizzes")){
                            current = "quiz";
                            quiz = new Quiz();
                        }else if(current.equals("questions")){
                            question = new Question();
                            current="question";
                        }else if(keyName.equals("category")){
                            category= new Category();
                            current="category";
                        }
                    }
                    case  END_OBJECT -> {
                        if(current.equals("question")){
                            current="questions";
                            quiz.getQuestions().add(question);
                        }else if(current.equals("category")){
                            quiz.setCategory(category);
                            current="quiz";
                        }else if(current.equals("quiz")){
                            current="quizzes";
                            quizzes.add(quiz);
                        }
                    }
                    case END_ARRAY -> {
                        if(current.equals("questions")){
                            current="quiz";
                        }else if(current.equals("quizzes")){
                            return quizzes;
                        }else if(current.equals("options")){
                            current="question";
                        }
                    }
                    case KEY_NAME -> {
                        keyName=parser.getString();
                    }
                    case VALUE_NUMBER -> {
                        if(keyName.equals("score")){
                            quiz.setScore(parser.getInt());
                        }
                    }
                    case VALUE_STRING -> {
                        String value = parser.getString();
                        switch (keyName){
                            case "quiz_id" ->{
                                quiz.setQuiz_id(value);
                            }
                            case "name"->{
                                if(current.equals("quiz"))
                                quiz.setName(value);
                                else if(current.equals("category")){
                                    category.setName(value);
                                }
                            }
                            case "question_id"->{
                                question.setQuestion_id(value);
                            }
                            case "text"->{
                                question.setText(value);
                            }
                            case "options"->{
                                question.getOptions().add(value);
                            }
                            case "correct_answer"->{
                                question.setCorrect_answer(value);
                            }
                            case "category_id"->{
                                category.setCategory_id(value);

                            }

                        }
                    }

                }
            }
   } catch (Exception e) {
           throw new RuntimeException(e);
       }
       return null;
}
public static List<Quiz>ListQuizzes(String categoryId){
    List<Quiz> list = read(new File("json/quizzes.json"));
    List<Quiz>  listQuizzes = list.stream().filter(v->v.getCategory().getCategory_id().equals(categoryId)).toList();
   return listQuizzes;
   }
    public static void write(List<Quiz> quizzes,File file){
       try(
               FileWriter fileWriter = new FileWriter(file);
               BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
               ) {
                quizzes.forEach(v-> {
                    try {
                        bufferedWriter.write(v.toString());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }
    // sử sụng JsonGenerate
    public static void writeGenerate(File file, List<Quiz> quiz){
        try(
                FileWriter w = new FileWriter(file);
                JsonGenerator gen = Json.createGenerator(w);
        ) {
            gen.writeStartArray();

            quiz.forEach(v->{
                // start1
                gen.writeStartObject()
                        .write("quiz_id",v.getQuiz_id())
                        .write("name",v.getName())
                        .write("score",v.getScore());

                // questions
                gen.writeStartArray("questions");

                for(Question question:v.getQuestions()){
                    gen.writeStartObject()
                            .write("question_id",question.getQuestion_id())
                            .write("text",question.getText());

                    // options
                    gen.writeStartArray("options");
                    for(String s:question.getOptions()){
                        gen.write(s);
                    }
                    gen.writeEnd();

                    gen.write("correct_answer",question.getCorrect_answer());
                    gen.writeEnd();
                }

                gen.writeEnd();

                // category
                gen.writeStartObject("category")
                        .write("category_id",v.getCategory().getCategory_id())
                        .write("name",v.getCategory().getName());
                gen.writeEnd();

                gen.writeEnd();
                gen.writeEnd();
                // quiz
            });

            gen.writeEnd(); // array

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
// writeGenerate
    public static void writeFileTheoGenerate(File file,List<Quiz>list){
        try(
                FileWriter fileWriter = new FileWriter(file);
                JsonGenerator gen = Json.createGenerator(fileWriter);
                ) {

            gen.writeStartArray();
            //quiz
            list.forEach(quiz->{
               //start cuar quiz
                gen.writeStartObject().write("quiz_id",quiz.getQuiz_id())
                        .write("name",quiz.getName())
                        .write("score",quiz.getScore());
                //start arr question
                gen.writeStartArray("questions");
                quiz.getQuestions().forEach(ques->{
                    gen.writeStartObject().write("question_id",ques.getQuestion_id())
                            .write("text",ques.getText());
                    gen.writeStartArray("options");
                    ques.getOptions().forEach(v->{
                        gen.write(v);
                    });
                    gen.writeEnd();
                    gen.write("correct_answer",ques.getCorrect_answer());
                    gen.writeEnd();
                }

                );   gen.writeEnd();

                gen.writeStartObject("category").write("category_id",quiz.getCategory().getCategory_id())
                        .write("name",quiz.getCategory().getName());
                gen.writeEnd();
                gen.writeEnd();
                gen.writeEnd();
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public static void main(String[] args) {
//        File file = new File("json/quizzes.json");
//        List<Quiz> t =JsonStream.read(file);
//        t.forEach(v-> System.out.println(v.toString()));
        List<Quiz>listQuizzes= JsonStream.ListQuizzes("C003");
        listQuizzes.forEach(v-> System.out.println(v.toString()));
        //JsonStream.writeGenerate(new File("json/NguyenVanTruong23653651.json"),listQuizzes);
        JsonStream.writeFileTheoGenerate(new File("json/HoaiThuong.json"),listQuizzes);
    }
}

