package com.cc.database.hash;

/**
 * Created by wangpeiyu on 2017/7/22.
 */
public class HasherImpl implements IHasher {


    public String hash(String val, int field) {
        String code = "";
        switch (field){
            case IHasher.FIRSTNAME:
                code = getFirstNameHash(val);
                break;
            case IHasher.SECONDNAME:
                code = getSecondNameHash(val);
                break;
            case IHasher.PHONE:
                code = getPhoneHash(val);
                break;
            case IHasher.EMAIL:
                code = getEmailHash(val);
                break;
            case IHasher.COMPANY:
                code = getCompanyHash(val);
                break;
            case IHasher.DEPARTMENT:
                code = getDepartmentHash(val);
                break;
            case IHasher.POSITION:
                code = getPositionHash(val);
                break;
                default:
                    break;
        }
        return code;
    }


    private String getDepartmentHash(String val) {
        return  null;
    }


    private String getPositionHash(String val) {
        return null;
    }

    private String getCompanyHash(String val) {
        return null;
    }

    private String getEmailHash(String val) {
        return null;
    }

    private String getPhoneHash(String val) {

        return  null;
    }

    private String getSecondNameHash(String val) {

        return null;
    }


    /**
     *返回姓名中姓的hashcode
     * 确保每一个姓只有唯一确定的hashcode
     * @param name
     * @return
     */
    public  String getFirstNameHash(String name){
        String hashcode = "";
        char[] array = name.toCharArray();
        for(int i=0;i<array.length;i++){
            String strBinary = Integer.toBinaryString(array[i]);
            if(strBinary.length() == 7) {
                strBinary = 0 + strBinary;
            }
            if(strBinary.length() == 15){
                strBinary = 0 + strBinary;
            }
            hashcode = strBinary + hashcode;
            if(hashcode.length()==16){
                break;
            }
        }
        return hashcode;
    }
}
