package hr.fer.opp.onedayjob;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import hr.fer.opp.onedayjob.Activities.JobActivity;
import hr.fer.opp.onedayjob.Activities.TheMainActivity;
import hr.fer.opp.onedayjob.Models.*;
import hr.fer.opp.onedayjob.util.Util;

/**
 * Created by Alen Carin on 2.1.2018..
 */

public class FeedAdapter extends ArrayAdapter {
    private static final String TAG = "FeedAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<Posao> jobs;
    private final int MAX_LEN_OPISA = 58;
    private Posao currentJob;

    public FeedAdapter(@NonNull Context context, int resource, List<Posao> jobs) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.jobs = jobs;
    }

    @Override
    public int getCount() {
        return jobs.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(layoutResource, parent, false);

        TextView jobTitle = (TextView) view.findViewById(R.id.job_title);
        TextView jobDate = (TextView) view.findViewById(R.id.job_date);
        TextView jobDescription = (TextView) view.findViewById(R.id.job_description);
        Button jobButton = (Button) view.findViewById(R.id.job_button);

        currentJob = jobs.get(position);
        Log.d("poslovi", "getView: " + currentJob.getNaslov());

        jobTitle.setText(currentJob.getNaslov());

        String vrijemeString = new Date(currentJob.getVrijeme()).toString();
        jobDate.setText(Util.datumIz(vrijemeString));

        String opisPosla =currentJob.getOpis();
        String prikazaniOpis = opisPosla.length() > MAX_LEN_OPISA ? opisPosla.substring(0,MAX_LEN_OPISA-3) + "..." : opisPosla;
        jobDescription.setText(prikazaniOpis);

        jobButton.setText("Pregled");

        jobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Tu cu valjda napravit da otvara tocno taj posao
                Intent intent = new Intent(FeedAdapter.super.getContext(), JobActivity.class);
                Bundle bundle = new Bundle();

                Log.d("odabrani posao", "onClick: saljem posao" + currentJob.getNaslov());
                bundle.putSerializable("odabraniPosao", jobs.get(position));
                intent.putExtras(bundle);

                getContext().startActivity(intent);
            }
        });

        return view;
    }
}
