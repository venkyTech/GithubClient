package com.crashlytics.issuelist.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.crashlytics.issuelist.R;
import com.crashlytics.issuelist.adapters.IssueCommentsAdapter;
import com.crashlytics.issuelist.common.Logs;
import com.crashlytics.issuelist.common.Utility;
import com.crashlytics.issuelist.models.ErrorResponse;
import com.crashlytics.issuelist.models.IssueComments;
import com.crashlytics.issuelist.models.IssuesData;
import com.crashlytics.issuelist.services.RequestID;
import com.crashlytics.issuelist.services.ServerListener;
import com.crashlytics.issuelist.services.ServerRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Venkat-Tesark on 25-04-2018.
 */
public class IssueDetailActivity extends BaseActivity implements ServerListener {

    private RecyclerView recyclerView;
    private TextView title;
    private TextView noCommentsTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_info);
        initViews();

        if (getIntent() != null) {

            IssuesData issuesData=(IssuesData) getIntent().getSerializableExtra("Issue");

            title.setText(issuesData.getBody());
            getIssueList(""+issuesData.getNumber());
        }
    }

    private void initViews() {

        getSupportActionBar().setTitle("Issue Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        noCommentsTextView = findViewById(R.id.TxtNoComments);
        title = findViewById(R.id.TxtTitle);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
    }

    private void getIssueList(String no) {

        if (isConnected()) {
            Map<String, String> map = new HashMap<>();
            map.put("issueNo", no);

            ServerRequest.with(IssueDetailActivity.this)
                    .setCallBack(this)
                    .setProgressDialog(true)
                    .setProgressDialogVisible(true)
                    .setParams(map)
                    .setRequestID(RequestID.REQ_GET_ISSUE_COMMENTS)
                    .build();
        } else {

            showInternetAlert();
        }

    }

    private void showInternetAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(IssueDetailActivity.this);
        builder.setTitle("Alert");
        builder.setMessage("No internet!!! Do you want to enable connection");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 123);
            }
        });

        builder.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess(RequestID requestID, Object result) {

        if (requestID == RequestID.REQ_GET_ISSUE_COMMENTS) {

            if (result != null) {

                List<IssueComments> issueComments = (List<IssueComments>) result;

                if (issueComments != null && issueComments.size()>0) {

                    noCommentsTextView.setVisibility(View.GONE);
                    recyclerView.setAdapter(new IssueCommentsAdapter(issueComments));
                } else {

                    noCommentsTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void onError(RequestID requestID, Object result) {

        if (result != null) {

            switch (requestID) {

                case REQ_GET_ISSUE_COMMENTS:

                    ErrorResponse errorResponse = (ErrorResponse) result;

                    noCommentsTextView.setVisibility(View.VISIBLE);
                    if (errorResponse != null) {

                        Utility.with(IssueDetailActivity.this).showToast(errorResponse.getMessage());

                    }

                    break;
            }
        }
    }
}
