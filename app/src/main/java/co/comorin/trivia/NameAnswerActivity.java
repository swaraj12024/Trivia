package co.comorin.trivia;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NameAnswerActivity extends AppCompatActivity {


    EditText name;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_answer);

        name = findViewById(R.id.name);
        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //going to next activity with answer
                if (TextUtils.isEmpty(name.getText().toString().trim())) {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.root), "Please answer this question...", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    startActivity(new Intent(NameAnswerActivity.this, SingleAnswerActivity.class).putExtra("nameAnswer", name.getText().toString()));
                }

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
                }).create().show();
    }
}
