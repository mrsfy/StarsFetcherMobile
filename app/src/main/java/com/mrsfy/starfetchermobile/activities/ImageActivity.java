package com.mrsfy.starfetchermobile.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.mrsfy.starfetchermobile.R;
import com.mrsfy.starfetchermobile.network.VolleySingleton;

public class ImageActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Button mPrevious;
    private Button mNext;
    public TextView numberText;
    public String url;
    public String studentId;
    public Bitmap image;
    public Bitmap forbidden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Intent intent = getIntent();
        mImageView = (ImageView) findViewById(R.id.imageView);
        mPrevious = (Button) findViewById(R.id.previous_button);
        mNext = (Button) findViewById(R.id.next_button);
        numberText = (TextView) findViewById(R.id.number_displayer);

        studentId = intent.getStringExtra("studentId");
        sendRequest(studentId, mImageView);

        StringRequest request = new StringRequest("http://stars-fetcher.herokuapp.com/image.php?for_id=" + "21502043",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ImageRequest imageRequest = new ImageRequest(response, new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                forbidden = response;
                            }
                        }, 0, 0, Bitmap.Config.ARGB_8888,
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.i("image load error", error.toString());
                                    }
                                });
                        VolleySingleton.getInstance().getRequestQueue().add(imageRequest);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().getRequestQueue().add(request);

        mPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentId = Integer.toString(Integer.parseInt(studentId) - 1);
                sendRequest(studentId, mPrevious);
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentId = Integer.toString(Integer.parseInt(studentId) + 1);
                sendRequest(studentId, mNext);
            }
        });
    }

    public void sendRequest(final String id, final View v) {
        StringRequest request = new StringRequest("http://stars-fetcher.herokuapp.com/image.php?for_id=" + id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        sendImageRequest(response, v);
                        // Log.i("ID", studentId);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().getRequestQueue().add(request);
    }

    public void sendImageRequest(String address, final View v){
        ImageRequest imageRequest = new ImageRequest(address, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                numberText.setText(studentId);
                if ( response.sameAs(forbidden) ) {
                    if ( v.equals(mNext) ) {
                        studentId = Integer.toString(Integer.parseInt(studentId) + 1);
                        sendRequest(studentId, v);
                        Log.i("ID", studentId);
                    } else if ( v.equals(mPrevious) ) {
                        studentId = Integer.toString(Integer.parseInt(studentId) - 1);
                        sendRequest(studentId, v);
                        Log.i("ID", studentId);
                    } else {
                        mImageView.setImageBitmap(response);
                    }
                } else {
                    mImageView.setImageBitmap(response);
                }
                mImageView.setImageBitmap(response);
            }
        }, 0, 0, Bitmap.Config.ARGB_8888,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("image load error", error.toString());
                    }
                });
        VolleySingleton.getInstance().getRequestQueue().add(imageRequest);
    }


}
