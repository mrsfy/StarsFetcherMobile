package com.mrsfy.starfetchermobile.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mrsfy.starfetchermobile.R;
import com.mrsfy.starfetchermobile.core.FixWebView;
import com.mrsfy.starfetchermobile.core.StudentIdBuilder;
import com.mrsfy.starfetchermobile.core.StudentIdChecker;
import com.mrsfy.starfetchermobile.core.enums.StudentIdBuildTypes;
import com.mrsfy.starfetchermobile.network.VolleySingleton;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private Button mGoButton;
    private String studentId;
    private MainActivity self = this;
    private Button mPreventFormButton;

    private Button mPlusButton;
    private Button mMinusButton;
    private Button mShuffleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        mGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goButtonOnClick();
            }
        });
        mPreventFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPreventFormDialog();
            }
        });
        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { plusOrMinusStudentId(StudentIdBuildTypes.INCREASE);     }
        });
        mMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { plusOrMinusStudentId(StudentIdBuildTypes.DECREASE);     }
        });
    }



    private void goButtonOnClick() {
        studentId = mEditText.getText().toString();
        //if (StudentIdChecker.check(studentId)) {

            studentId = mEditText.getText().toString();
            Intent intent = new Intent(self, ImageActivity.class);
            intent.putExtra("studentId", studentId);
            startActivity(intent);

            /*
            mGoButton.setEnabled(false);
            StringRequest stringRequest = new StringRequest("http://stars-fetcher.herokuapp.com/image.php?for_id=" + studentId,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Intent intent = new Intent(self, ImageActivity.class);
                            intent.putExtra("URL", response);
                            startActivity(intent);
                            mGoButton.setEnabled(true);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
            VolleySingleton.getInstance().getRequestQueue().add(stringRequest);
        }else{
            wrongStudentIdFormat();
        }*/
    }

    private void wrongStudentIdFormat() {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.mainRelativeLayout), "Wrong student id format", Snackbar.LENGTH_LONG)
                .setAction("REMOVE", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mEditText.setText("");
                    }
                });
        snackbar.show();
    }
    private void plusOrMinusStudentId(StudentIdBuildTypes type) {
        String currentId = mEditText.getText().toString();
        if (StudentIdChecker.check(currentId)){
            String id = new StudentIdBuilder()
                    .setCurrentId(currentId)
                    .setType(type)
                    .build();
            mEditText.setText(id);
            mEditText.setSelection(id.length());
        }else{
            wrongStudentIdFormat();
        }
    }
    private void initPreventFormDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Prevent Form");

        FixWebView webView = new FixWebView(this);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("http://stars-fetcher.herokuapp.com/form.php");
        webView.setWebViewClient(new WebViewClient(){
            // Note this!
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        builder.setView(webView);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    private void initViews() {
        mEditText = (EditText) (findViewById(R.id.editText));
        mGoButton = (Button) (findViewById(R.id.lets_stalk));
        mPreventFormButton = (Button) findViewById(R.id.do_it_button);
        mPlusButton = (Button) findViewById(R.id.sign_plus);
        mMinusButton = (Button) findViewById(R.id.sign_minus);
        mShuffleButton = (Button) findViewById(R.id.shuffle);
    }
}
