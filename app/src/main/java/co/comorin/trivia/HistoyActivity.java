package co.comorin.trivia;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import co.comorin.trivia.adapters.HistoryAdapter;
import co.comorin.trivia.utils.QAModel;

public class HistoyActivity extends AppCompatActivity {


    RecyclerView historyRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histoy);


        //set data to recyclerView
        historyRecyclerView = findViewById(R.id.history_recycler);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyRecyclerView.setAdapter(new HistoryAdapter(this, getDataFromSharedPreferences()));

    }


    //get List of results
    private List<QAModel> getDataFromSharedPreferences(){
        Gson gson = new Gson();
        List<QAModel> qaModelList = new ArrayList<>();
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("objectFile", Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString("objectList", "");

        Type type = new TypeToken<List<QAModel>>() {}.getType();
        qaModelList = gson.fromJson(jsonPreferences, type);

        return qaModelList;
    }
}
