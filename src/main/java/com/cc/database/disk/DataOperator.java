package com.cc.database.disk;

import javafx.scene.chart.PieChart;

import java.io.RandomAccessFile;
import java.util.ArrayList;
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

    public static DataOperator getInstance() throws Exception{
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

    public Model get(long lineNumber) {
        try{
            long[] recPos = searchLineInfo(lineNumber);
            byte[] record = new byte[(int)recPos[2]];

            dataAccessor.seek(recPos[1]);
            dataAccessor.read(record,0 , (int)recPos[2]);
            String val = new String(record);
            return StringToModel(val);

        }catch(Exception e){
            return null;
        }

    }

    public List<Model> get(List<Long> lineNumbers){
        List<Model> retList = new ArrayList<Model>();
        for(Long line: lineNumbers){
            retList.add(get(line));
        }
        return retList;
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

    private Model StringToModel(String val){
        String[] attributes = val.split("\\|-\\|");
        Model ret = new Model();
        ret.Name = attributes[0];
        ret.Phone = attributes[1];
        ret.Email = attributes[2];
        ret.Company = attributes[3];
        ret.Department = attributes[4];
        ret.Position = attributes[5];
        return ret;
    }

    private long[] searchLineInfo(long lineNumber) throws Exception{
        byte[] lineRecBytes = new byte[LINE_REC_WIDTH];

        long realLineNumber = lineNumber;

        boolean end = false;
        int direct = 0; // -1 向上， 0 正好， 1 向下
        do{
            //读取数据
            lineRecAccessor.seek(realLineNumber * LINE_REC_WIDTH );
            lineRecAccessor.read(lineRecBytes, 0, LINE_REC_WIDTH);
            //判断是正好， 向上还是向下
            String record = new String(lineRecBytes);
            String[] rs = record.split("\\|");
            Long recLine = Long.parseLong(rs[0]);
            if(recLine.compareTo(lineNumber)==0){ //找到了
                end = true;
            }else if(recLine < lineNumber){ //要向下走
                if(direct == -1){//之前向上
                    return null;
                }
                direct = 1;
                realLineNumber++;
            }else{//要向上走
                if(direct == 1){//之前向下走
                    return null;
                }
                direct = -1;
                realLineNumber--;
            }
        }while(!end);

        String[] retStr = new String(lineRecBytes).split("\\|");
        long[] ret = new long[3];
        ret[0] = Long.parseLong(retStr[0]);
        ret[1] = Long.parseLong(retStr[1]);
        ret[2] = Long.parseLong(retStr[2].substring(0, retStr[2].indexOf("\n")));

        return ret;
    }

    public static void main(String[] argv){
        try{
            DataOperator op = DataOperator.getInstance();
            List<Long> teLi = new ArrayList<Long>();
            for(long i=0; i<100; i++){
                teLi.add(i);
            }
            op.get(teLi);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}


