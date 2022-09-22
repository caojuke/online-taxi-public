package com.msb.servicedriveruser.generator;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JukeSqlGenerator {
    private static Connection conn;

    private static final String username="root";
    private static final String password="A#nd007.";
    private static final String url="jdbc:mysql://127.0.0.1:3306/service_driver_user?characterEncoding=utf-8&serverTimezone=GMT%2B8";

    public static void main(String[] args) {
        ConnectMySqlByJDBC();
        createEntity("car");
    }
    //连接数据库MySQL的方法
    public static void ConnectMySqlByJDBC(){
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            conn = DriverManager.getConnection(url, username, password);
        }
        catch (Exception exception){
            System.out.println("connect to database failed! reason: "+exception.getMessage());
        }
        System.out.println("Database connection established !");
    }
    public static void createEntity(String tableName){
        //statement=conn.createStatement();//这个是普通statement，不是preparedStatement
        //statement.setString(1,para1);//设置参数
        //show table status where name='driver_car_binding_relationship'
        String sql="show full fields from "+tableName;
        ResultSet res=null;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);//提前传入sql语句
            res= statement.executeQuery();//此时不再需要传sql语句了
            while (res.next()) {
                String field = res.getString("Field");
                String type=res.getString("Type");
                String comment=res.getString("Comment");
                String jField=underlineToCamel2(field);
                String jType=sqlTypeToJavaType(type);
                String example=exampleValue(type);
                //打印dao
                //System.out.println("// "+type+", "+comment);
                //System.out.println("private \t"+jType+"\t\t"+jField+";");
                //打印json格式
                //System.out.println("\""+jField+"\":"+example);
                //打印insert语句在mapper需要的格式
                //System.out.print("#{"+ jField+"},");
                //打印update语句在mapper中需要的格式
                System.out.println(field+"="+"#{"+ jField+"},");

            }
        }
        catch (Exception exception){
            System.out.println("execute sql failed! reason: "+exception.getMessage());
        }
    }


    private static String underlineToCamel1(String field){
        char[] oldChars = field.toCharArray();
        char[] newChars=new char[field.length()];
        int j=1;
        newChars[0]=oldChars[0];
        for (int i = 1; i < oldChars.length; i++) {
            if(oldChars[i]=='_'){
                continue;
            }else if (oldChars[i-1]=='_'){
                newChars[j]=Character.toUpperCase(oldChars[i]);
                j++;
            }else {
                newChars[j]=oldChars[i];
                j++;
            }
        }

        return String.valueOf(newChars);
    }
    private static String underlineToCamel2(String field){
        //这个pattern匹配一个下划线加一个字符！
        String pattern="_[a-z]";
        Pattern regex=Pattern.compile(pattern);
        Matcher matches = regex.matcher(field);
        String result=field;
        //find方法查找匹配的位置，将下划线后边的字符换成大写
        while (matches.find()){
            result=result.replace("_"+field.charAt(matches.start()+1),String.valueOf(field.charAt(matches.start()+1)).toUpperCase());
        }
        return result;
    }
    private static String sqlTypeToJavaType(String type){
        if(type.contains("char"))return "String";
        if(type.contains("int"))return "Integer";
        if(type.contains("datetime"))return "LocalDateTime";
        if(type.contains("date"))return "LocalDate";
        return "Object";
    }
    private static  String exampleValue(String type){
        if(type.contains("char"))return "\"string\",";
        if(type.contains("int"))return "1,";
        if(type.contains("datetime"))return "\"2022-03-03T00:00:00\",";
        if(type.contains("date"))return "\"2022-03-03\",";
        return "Object";
    }
}
