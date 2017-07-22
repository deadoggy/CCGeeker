package com.cc.database.hash;

/**
 * Created by deadoggy on 17-7-22.
 */
public interface Hasher {


    int FIRSTNAME = 0;
    int SECONDNAME = 1;
    int PHONE = 3;
    int EMAIL = 4;
    int COMPANY = 5;
    int DEPARTMENT = 6;
    int POSITION = 7;


    String hash(String val, int field);

}
