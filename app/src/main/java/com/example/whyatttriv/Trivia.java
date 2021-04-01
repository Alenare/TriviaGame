package com.example.whyatttriv;

public class Trivia {
    private int id;
    private String topic;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;

    public Trivia(int newID,String newTopic, String oP1, String oP2, String oP3, String oP4, String ans){
        setID(newID);
        setTopic(newTopic);
        setOp1(oP1);
        setOp2(oP2);
        setOp3(oP3);
        setOp4(oP4);
        setAns(ans);
    }

    public void setID(int newID) {
        id=newID;
    }
    public void setTopic(String newTopic){topic = newTopic; }
    public void setOp1(String question){option1 = question; }
    public void setOp2(String question){option2 = question; }
    public void setOp3(String question){option3 = question; }
    public void setOp4(String question){option4 = question; }
    public void setAns(String correct){answer = correct; }

    public int getID(){
        return id;
    }
    public String getTopic(){return topic;}
    public String getOp1(){
    return option1;
    }
    public String getOp2(){
        return option2;
    }
    public String getOp3(){return option3;}
    public String getOp4(){return option4;}
    public String getAns(){return answer;}

}
