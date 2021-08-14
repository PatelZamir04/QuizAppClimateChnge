package com.example.quizapp;

public class Questions {
    private String Question;
    private String Option1;
    private String Option2;
    private String Option3;
    private String Option4;
    private int Answer;


    //Constructors
    public Questions() {

    }

    public Questions(String pQuestion, String pOption1, String pOption2, String pOption3, String pOption4, int pAns) {
        Question = pQuestion;
        Option1 = pOption1;
        Option2 = pOption2;
        Option3 = pOption3;
        Option4 = pOption4;
        Answer = pAns;
    }

    //Accessors
    public String getQuestion() {
        return Question;
    }

    public String getOption1() {
        return Option1;
    }

    public String getOption2() {
        return Option2;
    }

    public String getOption3() {
        return Option3;
    }

    public String getOption4() {
        return Option4;
    }

    public int getAnswer() {
        return Answer;
    }

    //Mutators
    public void setQuestion(String question) {
        Question = question;
    }

    public void setOption1(String option1) {
        Option1 = option1;
    }

    public void setOption2(String option2) {
        Option2 = option2;
    }

    public void setOption3(String option3) {
        Option3 = option3;
    }

    public void setOption4(String option4) {
        Option4 = option4;
    }

    public void setAnswer(int answer) {
        Answer = answer;
    }
}
