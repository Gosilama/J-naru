package com.gosilama.journal.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gosilama.journal.R;
import com.gosilama.journal.activity.JournalActivity;
import com.gosilama.journal.data.JournalDbHandler;
import com.gosilama.journal.model.Journal;
import com.gosilama.journal.util.Utils;

import java.util.ArrayList;

public class JournalListAdapter extends RecyclerView.Adapter<JournalListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Journal> journalArrayList;

    public JournalListAdapter(Context context, ArrayList<Journal> journalArrayList) {
        this.context = context;
        this.journalArrayList = journalArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                        .from(context)
                        .inflate(R.layout.journal_item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(journalArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return journalArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView entrySummaryTitle = itemView.findViewById(R.id.entry_summary_title);
        TextView entrySummaryContent = itemView.findViewById(R.id.entry_summary_content);
        TextView entryCreationDate = itemView.findViewById(R.id.entry_creation_date);
        Button deleteButton = itemView.findViewById(R.id.delete_entry);

        ViewHolder(View itemView) {
            super(itemView);
        }

        void bindItem(final Journal journal) {
            entrySummaryTitle.setText(journal.getJournalTitle());
            entrySummaryContent.setText(journal.getJournalEntry());
            entryCreationDate.setText(Utils.showReadableDate(journal.getDateCreated()));

            deleteButton.setOnClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int RESULT_CODE = 1;

                    Intent intent = new Intent(context, JournalActivity.class);
                    intent.putExtra("EXTRA_JOURNAL_TITLE", journal.getJournalTitle());
                    intent.putExtra("EXTRA_JOURNAL_CONTENT", journal.getJournalEntry());

                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {

            int journalEntryPosition = getAdapterPosition();
            Journal journal = journalArrayList.get(journalEntryPosition);

            if (v.getId() == R.id.delete_entry) {
                deleteJournalEntry(journal.getId());
                journalArrayList.remove(journalEntryPosition);
                notifyItemRemoved(journalEntryPosition);
            }
        }

        void deleteJournalEntry(int id) {
            JournalDbHandler journalDbHandler = new JournalDbHandler(context);
            journalDbHandler.deleteJournal(id);
        }
    }
}
