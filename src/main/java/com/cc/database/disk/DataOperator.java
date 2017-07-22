package com.cc.database.disk;

import javafx.scene.chart.PieChart;

import java.io.RandomAccessFile;
import java.util.List;

/**
 * Created by deadoggy on 17-7-22.
 */
public class DataOperator implements Operator {

    public static String DATA_FILE_NAME = "database";
    public static String LINE_RECORD_NAME = "line_record";
    private static DataOperator instance = null;
    private static Object lock = new Object();

    private RandomAccessFile dataAccessor = null;
    private RandomAccessFile lineRecAccessor = null;


    private DataOperator() throws Exception{
        this.dataAccessor = new RandomAccessFile(DATA_FILE_NAME, "rw");
        this.lineRecAccessor = new RandomAccessFile(LINE_RECORD_NAME, "rw");
    }

    public DataOperator getInstance() throws Exception{
        if(null != instance){
            return instance;
        }
        synchronized (lock){
            if(null == instance){
                instance = new DataOperator();
            }
            return instance;
        }

    }

    public Model get(long lineNumber){
        return null;
    }

    public List<Model> get(List<Long> lineNumbers){
        return null;
    }

    public long set(Model model){
        return -1;
    }

    public boolean update(Model model){
        return true;
    }

    public boolean delete(long lineNumber){
        return true;
    }

}


