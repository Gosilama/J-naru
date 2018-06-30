package com.gosilama.journal.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

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

        TextView entrySummaryTitle = itemView.findViewById(R.id.text_view_entry_title);
        TextView entrySummaryContent = itemView.findViewById(R.id.text_view_entry_summary);
        TextView entryCreationDate = itemView.findViewById(R.id.text_view_creation_date);
        TextView entryMenu = itemView.findViewById(R.id.text_view_options_menu);

        ViewHolder(View itemView) {
            super(itemView);
        }

        void bindItem(final Journal journal) {
            entrySummaryTitle.setText(journal.getJournalTitle());
            entrySummaryContent.setText(journal.getJournalEntry());
            entryCreationDate.setText(Utils.showReadableDate(journal.getDateCreated()));

            entryMenu.setOnClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editJournalEntry(journal);
                }
            });
        }

        @Override
        public void onClick(View v) {

            final int journalEntryPosition = getAdapterPosition();
            final Journal journal = journalArrayList.get(journalEntryPosition);

            if (v.getId() == R.id.text_view_options_menu) {
                PopupMenu popupMenu = new PopupMenu(context, entryMenu);

                popupMenu.inflate(R.menu.journal_item_menu);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_edit_entry:
                                editJournalEntry(journal);
                                break;
                            case R.id.menu_delete_entry:
                                deleteJournalEntry(journal.getId());
                                journalArrayList.remove(journalEntryPosition);
                                notifyItemRemoved(journalEntryPosition);
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }
        }

        void editJournalEntry(Journal journal) {
            Intent intent = new Intent(context, JournalActivity.class);
            intent.putExtra("EXTRA_JOURNAL", journal);

            context.startActivity(intent);
        }

        void deleteJournalEntry(int id) {
            JournalDbHandler journalDbHandler = new JournalDbHandler(context);
            journalDbHandler.deleteJournal(id);
        }
    }
}
