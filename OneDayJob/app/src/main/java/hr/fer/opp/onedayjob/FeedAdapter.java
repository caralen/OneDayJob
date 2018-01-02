package hr.fer.opp.onedayjob;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import hr.fer.opp.onedayjob.Models.*;

/**
 * Created by Alen Carin on 2.1.2018..
 */

public class FeedAdapter extends ArrayAdapter {
    private static final String TAG = "FeedAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<Posao> jobs;

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
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(layoutResource, parent, false);

        TextView jobTitle = (TextView) view.findViewById(R.id.job_title);
        TextView jobDate = (TextView) view.findViewById(R.id.job_date);
        TextView jobDescription = (TextView) view.findViewById(R.id.job_description);
        Button jobButton = (Button) view.findViewById(R.id.job_button);

        Posao currentJob = jobs.get(position);

        jobTitle.setText(currentJob.getNaslov());
        //jobDate.setText((CharSequence) currentJob.getVrijeme());
        jobDescription.setText(currentJob.getOpis());
        jobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Tu cu valjda napravit da otvara tocno taj posao
            }
        });

        return view;
    }
}
