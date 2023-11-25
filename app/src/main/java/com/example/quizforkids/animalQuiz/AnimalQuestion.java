package com.example.quizforkids.animalQuiz;


public class AnimalQuestion {
    private String img;
    private String Answer;

    public AnimalQuestion(){}
    public AnimalQuestion(String questionText, String img, String Answer) {
        this.img = img;
        this.Answer = Answer;
    }
    public String getImage(){
        return img;
    }
    public String getAnswer(){return Answer;}
}
