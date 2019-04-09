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

public class SingleAnswerActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    Button next;
    CheckBox optionA, optionB, optionC, optionD;
    String answer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_answer);

        next = findViewById(R.id.next);
        optionA = findViewById(R.id.a);
        optionB = findViewById(R.id.b);
        optionC = findViewById(R.id.c);
        optionD = findViewById(R.id.d);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //going to next activity with answer

                if (!answer.equals(""))
                    startActivity(new Intent(SingleAnswerActivity.this, MultipleAnswerActivity.class).putExtra("nameAnswer", getIntent().getStringExtra("nameAnswer")).putExtra("singleAnswer", answer));
                else {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.root), "Please answer this question...", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });

        //performing check operation
        optionA.setOnCheckedChangeListener(this);
        optionB.setOnCheckedChangeListener(this);
        optionC.setOnCheckedChangeListener(this);
        optionD.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        //enabling only one checkbox
        optionA.setChecked(false);
        optionB.setChecked(false);
        optionC.setChecked(false);
        optionD.setChecked(false);

        buttonView.setChecked(isChecked);
        if (isChecked)
            answer = buttonView.getText().toString();
        else
            answer = "";
    }

    @Override
    public void onBackPressed() {
        //showing alert dialog box
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
