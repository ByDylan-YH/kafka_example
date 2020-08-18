package utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//往MySQL写入数据
public class WriteMySQLUtil {
    public void WriteMySQL(File file) {
        Connection conn = null;
        Statement stat;
        StringBuffer sb = new StringBuffer();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bingo?useSSL=false&characterEncoding=UTF-8", "root", "962464");

            stat = conn.createStatement();
            //这里要做一下转换,windows版SQL需要用2条反斜杠
            String[] ss = file.getAbsoluteFile().toString().split("\\\\");
            for (String s : ss) {
                sb.append(s).append("\\\\");
            }
            System.out.println(sb);
            stat.execute("load data local infile '" + sb + "' into table kafka fields terminated by '|'");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
