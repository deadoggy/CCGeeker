package com.cc.database.Disk;

import java.util.List;

/**
 * Created by deadoggy on 17-7-22.
 */
public class DataOperator implements Operator {

    public String DATA_FILE = "database";
    public String LINE_RECORD = "line_record";


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


