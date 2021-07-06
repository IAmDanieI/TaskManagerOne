package com.example.TaskManagerOne;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;


public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private LinkedList<String> mWordList;
    private LayoutInflater mInflater;

    class  WordViewHolder extends RecyclerView.ViewHolder{
        public final TextView wordListItem;
        final WordListAdapter mAdapter;


        WordViewHolder(View itemView,WordListAdapter adapter){
            super(itemView);
            wordListItem = itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //get current item index
                    int mPosition = getLayoutPosition();
                    //retrieve from linked list
                    String element = mWordList.get(mPosition);
                    //update linked list content
                    mWordList.set(mPosition,"Clicked " + element);
                    mAdapter.notifyDataSetChanged();
                }
            });

        }
    }
    WordListAdapter(Context context, LinkedList<String> wordList){
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }
    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item,parent,false);

        return new WordViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        holder.wordListItem.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }
}
