package co.comorin.trivia;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import co.comorin.trivia.utils.QAModel;

public class SummaryActivity extends AppCompatActivity {

    TextView name, singleAnswer, multipleAnswer;
    Button history, finish;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        name = findViewById(R.id.name);
        singleAnswer = findViewById(R.id.single_answer);
        multipleAnswer = findViewById(R.id.multiple_answer);
        history = findViewById(R.id.history);
        finish = findViewById(R.id.finish);


        //creating objects of answers and storing in shared preferences
        QAModel qaModel = new QAModel();
        qaModel.setNameAnswer(getIntent().getStringExtra("nameAnswer"));
        qaModel.setSingleAnswer(getIntent().getStringExtra("singleAnswer"));
        qaModel.setMultipleAnswer(getIntent().getStringExtra("multipleAnswer"));
        qaModel.setCurrentTime(android.text.format.DateFormat.format("dd-MMMM hh:mm a", new java.util.Date())+"");
        addInJSONArray(qaModel);


        //setting answers in textviews
        name.setText("Hello : " + getIntent().getStringExtra("nameAnswer"));
        singleAnswer.setText("Answer : " + getIntent().getStringExtra("singleAnswer"));
        multipleAnswer.setText("Answers : " + getIntent().getStringExtra("multipleAnswer"));


        //restarting test
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SummaryActivity.this, NameAnswerActivity.class));
            }
        });


        //showing all results
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SummaryActivity.this, HistoyActivity.class));
            }
        });
    }
    @Override
    public void onBackPressed() {

        //showing alert dialog
        new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("Sure, You want to quit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();;
    }



   // storing data in local storage
    private void addInJSONArray(QAModel qaModel){

        Gson gson = new Gson();
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("objectFile", Context.MODE_PRIVATE);

        String jsonSaved = sharedPref.getString("objectList", "");
        String jsonQAToAdd = gson.toJson(qaModel);

        JSONArray jsonArrayProduct= new JSONArray();

        try {
            if(jsonSaved.length()!=0){
                jsonArrayProduct = new JSONArray(jsonSaved);
            }
            jsonArrayProduct.put(new JSONObject(jsonQAToAdd));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //SAVE NEW ARRAY
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("objectList", jsonArrayProduct.toString());
        editor.apply();
    }
}
