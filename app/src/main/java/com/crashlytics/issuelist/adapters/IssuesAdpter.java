package com.crashlytics.issuelist.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.crashlytics.issuelist.activities.IssueDetailActivity;
import com.crashlytics.issuelist.common.Logs;
import com.crashlytics.issuelist.common.Utility;
import com.crashlytics.issuelist.models.IssuesData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Venkat-Tesark on 25-04-2018.
 */
public class IssuesAdpter extends RecyclerView.Adapter<IssuesAdpter.ViewsHolder> {

    private List<IssuesData> issueList;

    public IssuesAdpter(List<IssuesData> issueList) {

        this.issueList = issueList;
    }

    @NonNull
    @Override
    public ViewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_issues, parent, false);

        return new ViewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewsHolder holder, int position) {

        final Context context = holder.itemView.getContext();

        final IssuesData issue = issueList.get(position);

        holder.titleTextView.setText(issue.getTitle());

        holder.statusTextView.setText(issue.getState());

        holder.descriptionTextView.setText(issue.getBody());

        String userName = issue.getUser().getLogin();

        String updatedDate = issue.getUpdated_at();
        Logs.e(this,updatedDate);



        Glide.with(holder.itemView.getContext()).load(issue.getUser().getAvatar_url()).into(holder.ImgAvatar);

        String sourceString = "Updated at "
                + "<b>" + Utility.with().dateFormat(updatedDate) + "</b> "
                +  " by " + "<b>" + userName + "</b> ";

        holder.issueInfoTextView.setText(Html.fromHtml(sourceString));

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });*/
    }

    @Override
    public int getItemCount() {
        return issueList.size();
    }

    public class ViewsHolder extends RecyclerView.ViewHolder {

        ImageView ImgAvatar;
        TextView titleTextView;
        TextView descriptionTextView;
        TextView statusTextView;
        TextView issueInfoTextView;

        public ViewsHolder(final View itemView) {
            super(itemView);
            ImgAvatar = itemView.findViewById(R.id.ImgUser);
            titleTextView = itemView.findViewById(R.id.TxtTitle);
            descriptionTextView = itemView.findViewById(R.id.TxtIssueDes);
            statusTextView = itemView.findViewById(R.id.TxtStatus);
            issueInfoTextView = itemView.findViewById(R.id.TxtIssueInfo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int no = issueList.get(getPosition()).getNumber();

                    Intent intent = new Intent(itemView.getContext(), IssueDetailActivity.class);
                    intent.putExtra("Issue", (Serializable) issueList.get(getPosition()));
                    itemView.getContext().startActivity(intent);
                }
            });
        }


    }


}
