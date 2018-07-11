package com.example.x.test03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MyAdapter.onButtonClickListener {

    @BindView(R.id.rows_editText)
    EditText rowsEditText;
    @BindView(R.id.columns_EditText)
    EditText columnsEditText;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.test)
    TextView test;

    private int rows;
    private int columns;
    private MyAdapter adapter = new MyAdapter(this);
    private float levels[][];
    private int currColumn = 0;

    private List<Float> columnRL = new ArrayList<>();
    private List<Float> columnH = new ArrayList<>();
    private List<Float> rowRL = new ArrayList<>();
    private List<Float> rowH = new ArrayList<>();

    @OnClick(R.id.submit_button)
    void start() {
        rows = Integer.valueOf(rowsEditText.getText().toString());
        columns = Integer.valueOf(columnsEditText.getText().toString());

        levels = new float[rows][columns];

        adapter.setRows(rows);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.calculate_fab)
    void test() {
        getRlH();
        for (int i = 0; i < columns; i++) {
            test.append(columnRL.get(i) + "  ");
        }
        test.append("\n");
        for (int i = 0; i < columns; i++) {
            test.append(columnH.get(i) + "  ");
        }
        test.append("\n");

        for (int i = 0; i < rows; i++) {
            test.append(rowRL.get(i) + "  ");
        }
        test.append("\n");
        for (int i = 0; i < rows; i++) {
            test.append(rowH.get(i) + "  ");
        }
    }

    private void getRlH() {

        //for Columns
        for (int i = 0; i < columns; i++) {
            float currValue = 0;
            for (int j = 0; j < rows; j++) {
                currValue += levels[j][i];
            }
            columnRL.add(currValue);
        }

        for (int i = 0; i < columns; i++) {
            columnH.add(columnRL.get(i) / rows);
        }

        //for Rows
        for (int i = 0; i < rows; i++) {
            float currValue = 0;
            for (int j = 0; j < columns; j++) {
                currValue += levels[i][j];////////////////////
            }
            rowRL.add(currValue);
        }

        for (int i = 0; i < rows; i++) {
            rowH.add(rowRL.get(i) / columns);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onButtonClick(int row, float level) {
        levels[row][currColumn++] = level;
        if (currColumn == columns)
            currColumn = 0;
    }
}
