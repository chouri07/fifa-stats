package com.example.casa.booksearch;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by a024994 on 20/01/2018.
 */

public class proc extends MainActivity {



    TextView mTitleText;
    TextView mAuthorText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proc);


        mTitleText = (TextView)findViewById(R.id.mTitleText);
        mAuthorText = (TextView)findViewById(R.id.mAuthorText);

        //Intent i = getIntent();
        //mTitleText.setText(i.getStringExtra("titulo"));
        //mAuthorText.setText(i.getStringExtra("author"));







    }

    public void searchBooks(View view) {
        Intent i = getIntent();

        // Get the search string from the input field.
        String queryString = i.getStringExtra("titulo").toString();

        // Hide the keyboard when the button is pushed.
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        // Check the status of the network connection.
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If the network is active and the search field is not empty, start a FetchBook AsyncTask.
        if (networkInfo != null && networkInfo.isConnected() && queryString.length()!=0) {
            new FetchBook(mTitleText, mAuthorText,i.getStringExtra("titulo")).execute(queryString);
        }
        // Otherwise update the TextView to tell the user there is no connection or no search term.
        else {
            if (queryString.length() == 0) {
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_search_term);
            } else {
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_network);
            }
        }
    }

}
