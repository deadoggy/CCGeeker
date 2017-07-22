package com.cc.database.disk;

import java.util.List;

/**
 * Created by deadoggy on 17-7-22.
 */
public interface Operator {

    int LINE_REC_WIDTH = 100;

    Model get(long lineNumber);

    List<Model> get(List<Long> lineNumbers);

    long set(Model model);

    boolean update(Model model);

    boolean delete(long lineNumber);

}
