package com.crashlytics.issuelist.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crashlytics.issuelist.R;
import com.crashlytics.issuelist.adapters.IssuesAdpter;
import com.crashlytics.issuelist.common.Utility;
import com.crashlytics.issuelist.models.ErrorResponse;
import com.crashlytics.issuelist.models.IssuesData;
import com.crashlytics.issuelist.services.RequestID;
import com.crashlytics.issuelist.services.ServerListener;
import com.crashlytics.issuelist.services.ServerRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements ServerListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        getIssueList();
    }

    private void initView() {

        recyclerView = (RecyclerView) findViewById(R.id.IssueList);
        swipeRefreshLayout = findViewById(R.id.SwipeRefreshLayout);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                getIssueList();
            }
        });


    }

    private void getIssueList() {

        if (isConnected()) {
            Map<String, String> map = new HashMap<>();
            map.put("state", "all");

            ServerRequest.with(MainActivity.this)
                    .setCallBack(this)
                    .setProgressDialog(false)
                    .setProgressDialogVisible(true)
                    .setParams(map)
                    .setRequestID(RequestID.REQ_GET_ISSUES)
                    .build();
        } else {

            showInternetAlert();
        }

    }

    private void showInternetAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
    public void onSuccess(RequestID requestID, Object result) {

        swipeRefreshLayout.setRefreshing(false);
        if (requestID == RequestID.REQ_GET_ISSUES) {

            List<IssuesData> issuesData = (List<IssuesData>) result;

            if (issuesData != null) {

                recyclerView.setAdapter(new IssuesAdpter(issuesData));
            }
        }

    }

    @Override
    public void onError(RequestID requestID, Object result) {

        swipeRefreshLayout.setRefreshing(false);
        if (result != null) {

            switch (requestID) {

                case REQ_GET_ISSUES:

                    ErrorResponse errorResponse = (ErrorResponse) result;

                    if (errorResponse != null) {

                        Utility.with(MainActivity.this).showToast(errorResponse.getMessage());

                    }

                    break;
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123) {

            getIssueList();
        }
    }
}
