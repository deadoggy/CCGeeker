package com.cc.database.Disk;

import java.util.List;

/**
 * Created by deadoggy on 17-7-22.
 */
public interface Operator {

    Model get(long lineNumber);

    List<Model> get(List<Long> lineNumbers);

    long set(Model model);

    boolean update(Model model);

    boolean delete(long lineNumber);

}
