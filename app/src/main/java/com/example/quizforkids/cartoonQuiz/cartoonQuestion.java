package com.example.quizforkids.cartoonQuiz;

public class cartoonQuestion {
    private String img;
    private String Answer;
    private String option1;
    private String option2;
    private String option3;

    public cartoonQuestion(){}
    public cartoonQuestion(String img, String Answer,String option1,String option2,String option3) {
        this.img = img;
        this.Answer = Answer;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;


    }
    public String getImage(){
        return img;
    }
    public String getAnswer(){return Answer;}
    public String getOption1(){return option1;}
    public String getOption2(){return option2;}
    public String getOption3(){return option3;}

}

