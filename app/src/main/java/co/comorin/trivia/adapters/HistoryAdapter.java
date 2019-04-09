package co.comorin.trivia.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.comorin.trivia.HistoyActivity;
import co.comorin.trivia.R;
import co.comorin.trivia.utils.QAModel;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyHolder> {

    private Context context;
    private List<QAModel> historyList;
    public HistoryAdapter(Context context, List<QAModel> dataFromSharedPreferences) {
        this.context = context;
        historyList = dataFromSharedPreferences;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.history_card, viewGroup, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.MyHolder myHolder, int i) {


        //setting data in cards
        myHolder.time.setText(" Game " + (i+1) + "  " + historyList.get(i).getCurrentTime());
        myHolder.name.setText("Name : " + historyList.get(i).getNameAnswer());
        myHolder.singleAnswer.setText("Answer : " + historyList.get(i).getSingleAnswer());
        myHolder.multipleAnswer.setText("Answers : " + historyList.get(i).getMultipleAnswer());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

     class MyHolder extends RecyclerView.ViewHolder {

        TextView time, name, singleAnswer, multipleAnswer;

         MyHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.time);
            name = itemView.findViewById(R.id.name);
            singleAnswer = itemView.findViewById(R.id.single_answer);
            multipleAnswer = itemView.findViewById(R.id.multiple_answer);
        }
    }
}
