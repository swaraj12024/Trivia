package co.comorin.trivia.utils;

public class QAModel {
    private String nameAnswer, singleAnswer, MultipleAnswer, currentTime;


    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getNameAnswer() {
        return nameAnswer;
    }

    public void setNameAnswer(String nameAnswer) {
        this.nameAnswer = nameAnswer;
    }

    public String getSingleAnswer() {
        return singleAnswer;
    }

    public void setSingleAnswer(String singleAnswer) {
        this.singleAnswer = singleAnswer;
    }

    public String getMultipleAnswer() {
        return MultipleAnswer;
    }

    public void setMultipleAnswer(String multipleAnswer) {
        MultipleAnswer = multipleAnswer;
    }
}
