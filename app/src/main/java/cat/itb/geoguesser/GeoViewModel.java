package cat.itb.geoguesser;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;

public class GeoViewModel extends ViewModel {

    private int index = -1;
    private int pistas=3;
    private double score=0;

    private ArrayList<Question> questions = new ArrayList<Question>(
    ){{
        add(new Question("Frow which Country are this flag from?",R.mipmap.bandera_japon_foreground,2,"China","Japan","Spain","America"));
        add(new Question("What is this mountain?",R.mipmap.monte_fuji_foreground,1,"Mount Fuji","Teide","Kilimanjaro","Everest"));
        add(new Question("What is the capital of Japan?",R.mipmap.mapa_japon,3,"Kioto","Hokaido","Tokio","Nagasaki"));
        add(new Question("What is the name of this ladies?",R.mipmap.geisha_foreground,2,"Samurai","Geisha","Tatami","Onigiri"));
//        add(new Question(""))
    }};


    public Question getQuestion() {
        return questions.get(index);

    }

    public void mezclar(){
        Collections.shuffle(questions);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
         this.index = index;
    }

    public int getPistas() {
        return pistas;
    }

    public void setPistas(int pistas) {
        this.pistas = pistas;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
