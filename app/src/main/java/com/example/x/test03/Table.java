package com.example.x.test03;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private int rows;
    private int columns;

    private List<Float> columnRL = new ArrayList<>();
    private List<Float> columnH = new ArrayList<>();
    private List<Float> rowRL = new ArrayList<>();
    private List<Float> rowH = new ArrayList<>();

    private ColumnTable columnTable;
    private RowTable rowTable;


    public Table(int columns, List<Float> columnRL, List<Float> columnH,
                 int rows, List<Float> rowRL, List<Float> rowH) {
        columnTable = new ColumnTable(columns, columnRL, columnH);
        rowTable = new RowTable(rows, rowRL, rowH);
    }

    public float getSWE() {
        return columnTable.getSWE();
    }

    public float getSNS() {
        return rowTable.getSNS();
    }
}
