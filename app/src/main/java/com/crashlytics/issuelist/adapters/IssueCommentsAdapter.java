package com.crashlytics.issuelist.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.crashlytics.issuelist.R;
import com.crashlytics.issuelist.common.Utility;
import com.crashlytics.issuelist.models.IssueComments;

import java.util.List;

/**
 * Created by Venkat-Tesark on 25-04-2018.
 */
public class IssueCommentsAdapter extends RecyclerView.Adapter<IssueCommentsAdapter.ViewsHolder> {


    List<IssueComments> commentsList;

    public IssueCommentsAdapter(List<IssueComments> commentsList) {
        this.commentsList = commentsList;
    }

   /* @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_issue_comments, parent, false);

        return new ViewsHolder(view);
    }*/

    @NonNull
    @Override
    public ViewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_issue_comments, parent, false);

        return new ViewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewsHolder holder, int position) {

        IssueComments issueComments = commentsList.get(position);

        String title = "<b>" + issueComments.getUser().getLogin() + "</b>"
                + " commented on "
                +"<b>"+ Utility.with().dateFormat(issueComments.getUpdated_at()) + "</b>";

        holder.TxtIssueInfo.setText(Html.fromHtml(title));

        holder.TxtComments.setText(issueComments.getBody());



        Glide.with(holder.itemView.getContext()).load(issueComments.getUser().getAvatar_url()).into(holder.ImgAvatar);

    }


    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    public class ViewsHolder extends RecyclerView.ViewHolder {

        ImageView ImgAvatar;
        TextView TxtIssueInfo;
        TextView TxtComments;

        public ViewsHolder(View itemView) {
            super(itemView);

            ImgAvatar = itemView.findViewById(R.id.ImgUser);
            TxtIssueInfo = itemView.findViewById(R.id.TxtIssueInfo);
            TxtComments = itemView.findViewById(R.id.TxtIssueDes);

        }
    }
}

