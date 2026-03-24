package com.ngphthinh;


import com.ngphthinh.mapper.QuizzMapper;

import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        File file= new File("src/main/resources/quizzes.json");
        System.out.println(QuizzMapper.quizzList(file));
        QuizzMapper.toFile(QuizzMapper.listQuizzes("Java Programming"),"src/main/resources/toQuizzes.json");
    }
}