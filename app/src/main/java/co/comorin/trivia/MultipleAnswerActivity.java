package co.comorin.trivia;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MultipleAnswerActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    Button next;
    CheckBox optionA, optionB, optionC, optionD;
    String answer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_answer);

        next = findViewById(R.id.next);
        optionA = findViewById(R.id.a);
        optionB = findViewById(R.id.b);
        optionC = findViewById(R.id.c);
        optionD = findViewById(R.id.d);


        //performing check operation
        optionA.setOnCheckedChangeListener(this);
        optionB.setOnCheckedChangeListener(this);
        optionC.setOnCheckedChangeListener(this);
        optionD.setOnCheckedChangeListener(this);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //start summary activity with answers
                if (!answer.equals("")) {
                    Intent intent = new Intent(MultipleAnswerActivity.this, SummaryActivity.class);
                    intent.putExtra("nameAnswer", getIntent().getStringExtra("nameAnswer"));
                    intent.putExtra("singleAnswer", getIntent().getStringExtra("singleAnswer"));
                    intent.putExtra("multipleAnswer", answer);
                    startActivity(intent);
                } else {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.root), "Please answer this question...", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        //store answers according to checkboxes
        if (isChecked)
            answer = answer + buttonView.getText().toString() + ", ";
        else
            answer = answer.replace(buttonView.getText().toString() + ",", "");
    }

    @Override
    public void onBackPressed() {

        //showing Alert dialog
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
}
