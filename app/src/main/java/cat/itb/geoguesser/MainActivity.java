package cat.itb.geoguesser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imagen;
    TextView pregunta;
    Button boton1,boton2,boton3,boton4, botonInicio, pista;
    ProgressBar progressBar;
    int index;
    Question question;
    GeoViewModel viewModel;
    RelativeLayout relativeLayout;
    AlertDialog.Builder fin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fin = new AlertDialog.Builder(this);
        fin.setTitle("End of the quiz");

        viewModel = new ViewModelProvider(this).get(GeoViewModel.class);

        index = viewModel.getIndex();
        setContentView(R.layout.activity_main);
        imagen = findViewById(R.id.imagen);
        pregunta = findViewById(R.id.pregunta);
        boton1 = findViewById(R.id.boton1);
        boton2 = findViewById(R.id.boton2);
        boton3 = findViewById(R.id.boton3);
        boton4 = findViewById(R.id.boton4);
        botonInicio = findViewById(R.id.boton_inicio);
        pista = findViewById(R.id.boton_hint);
        progressBar = findViewById(R.id.progressBar);
        relativeLayout = findViewById(R.id.layout_botones);

        if (index>=0){
            imagen.setVisibility(View.VISIBLE);
            pregunta.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.VISIBLE);
            botonInicio.setVisibility(View.INVISIBLE);
            pista.setVisibility(View.VISIBLE);

            question = viewModel.getQuestion();
            progressBar.setProgress((index+1)*10);
            imagen.setImageResource(question.getImage());
            pregunta.setText((index+1)+" of 10\n"+question.getQuestion());
            boton1.setText(question.getOpcion1());
            boton2.setText(question.getOpcion2());
            boton3.setText(question.getOpcion3());
            boton4.setText(question.getOpcion4());
        }



        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);
        boton4.setOnClickListener(this);
        botonInicio.setOnClickListener(this);
        pista.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.boton1:
               if (question.getSolucion() == 1){
                   Toast.makeText(this,"Corect",Toast.LENGTH_SHORT).show();
                   viewModel.setScore(viewModel.getScore()+1);
               }
               else {
                   Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
                   viewModel.setScore(viewModel.getScore() - 0.5);
               }
               cambiarPregunta();
                break;
            case R.id.boton2:
                if (question.getSolucion() == 2){
                    Toast.makeText(this,"Corect",Toast.LENGTH_SHORT).show();
                    viewModel.setScore(viewModel.getScore()+1);
                }
                else {
                    Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
                    viewModel.setScore(viewModel.getScore() - 0.5);
                }
                cambiarPregunta();
                break;
            case R.id.boton3:
                if (question.getSolucion() == 3){
                    Toast.makeText(this,"Corect",Toast.LENGTH_SHORT).show();
                    viewModel.setScore(viewModel.getScore()+1);
                }
                else {
                    Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
                    viewModel.setScore(viewModel.getScore() - 0.5);
                }
                cambiarPregunta();
                break;
            case R.id.boton4:
                if (question.getSolucion() == 4){
                    Toast.makeText(this,"Corect",Toast.LENGTH_SHORT).show();
                    viewModel.setScore(viewModel.getScore()+1);
                }
                else {
                    Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
                    viewModel.setScore(viewModel.getScore() - 0.5);
                }
                cambiarPregunta();
                break;
            case R.id.boton_inicio:
                viewModel.mezclar();
                imagen.setVisibility(View.VISIBLE);
                pregunta.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.VISIBLE);
                pista.setVisibility(View.VISIBLE);
                botonInicio.setVisibility(View.INVISIBLE);
                index++;
                viewModel.setIndex(index);
                question = viewModel.getQuestion();
                progressBar.setProgress((index+1)*10);
                imagen.setImageResource(question.getImage());
                pregunta.setText((index+1)+" of 10\n"+question.getQuestion());
                boton1.setText(question.getOpcion1());
                boton2.setText(question.getOpcion2());
                boton3.setText(question.getOpcion3());
                boton4.setText(question.getOpcion4());
                break;
            case R.id.boton_hint:
                if (0<viewModel.getPistas()){
                    viewModel.setPistas(viewModel.getPistas()-1);
                    Toast.makeText(this,"button nº"+question.getSolucion(),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"Yo don't have more hints",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Toast.makeText(this,"No has pulsado bien",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void cambiarPregunta() {
        index++;
        if (index<viewModel.getQuestions().size()) {
            viewModel.setIndex(index);
            index = viewModel.getIndex();
            question = viewModel.getQuestion();
            progressBar.setProgress((index + 1) * 10);
            imagen.setImageResource(question.getImage());
            pregunta.setText((index + 1) + " of 10\n" + question.getQuestion());
            boton1.setText(question.getOpcion1());
            boton2.setText(question.getOpcion2());
            boton3.setText(question.getOpcion3());
            boton4.setText(question.getOpcion4());
        }else {
            fin.setMessage("Yor score is "+(viewModel.getScore()*10)+" of 100");
            fin.setPositiveButton("Ok,close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            fin.create();
            fin.show();

        }
    }
}