package com.example.TaskManagerOne;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> mTasks = new LinkedList<>();
    private WordListAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFABAdd;
    private String mText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_wordList);
        mFABAdd = findViewById(R.id.fabAdd);
        mAdapter = new WordListAdapter(this,mTasks);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFABAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tasksNumber = mTasks.size();
                AlertDialog.Builder confirm = new AlertDialog.Builder(MainActivity.this);
                confirm.setTitle("Confirmation");
                confirm.setMessage("Create New Task?");
                confirm.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Task Not Created", Toast.LENGTH_SHORT).show();
                    }
                });
                confirm.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder createTask = new AlertDialog.Builder(MainActivity.this);
                        createTask.setTitle("Enter a Name");
                        final EditText input = new EditText(MainActivity.this);
                        input.setInputType(InputType.TYPE_CLASS_TEXT);
                        createTask.setView(input);
                        // Set up the buttons
                        createTask.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mText = input.getText().toString();
                                mTasks.addLast(mText);
                            }
                        });
                        createTask.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        createTask.show();
                        mRecyclerView.getAdapter().notifyItemInserted(tasksNumber);
                        mRecyclerView.smoothScrollToPosition(tasksNumber);
                    }
                });
                confirm.setCancelable(false);
                confirm.show();
            }
        });
    }
}