package com.itouba.api.iservice;

import java.io.File;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//用了javacsv这个插件包
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CSVReadAndWrite {
    // 写cvs文件
    public static void writeToCsv(String dirPath, String ym, String d,
                                  String[] cvsfile) {
        ExecutorService pool = Executors.newFixedThreadPool(100);
        for (int j = 0; j < cvsfile.length; j++) {
            Thread thread = new MDownThread(dirPath, cvsfile[j], ym, d);
            pool.execute(thread);

        }
        pool.shutdown();
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 我导入的是201504这一个月的数据，所以是1-30，按天导入
    public static void main(String[] args) throws Exception {
        String ym = "201504";
        String d;

        for (int i = 1; i <= 30; i++) {
            Date t1 = new Date();
            String dirPath = "E:\\" + ym + "/" + ym;
            d = String.format("%02d", i);
            dirPath += d + "/";
            File file = new File(dirPath);
            String cvsfile[];
            cvsfile = file.list();
            if (cvsfile == null || cvsfile.length <= 0) {
                continue;
            }
            System.out.println(dirPath + "开始导入！");
            // 写入文件
            writeToCsv(dirPath, ym, d, cvsfile);
            ExecutorService pool2 = Executors.newFixedThreadPool(3);
            for (int j = 0; j < cvsfile.length; j++) {
                if (!new File("E:/" + ym + "/" + ym + d + "/"
                        + cvsfile[j]).exists()) {
                    writeToCsv(dirPath, ym, d, cvsfile);
                }
                Thread thread2 = new LoadMysqlThread(dirPath, cvsfile[j], ym, d);
                pool2.execute(thread2);
            }
            pool2.shutdown();
            try {
                pool2.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Date t2 = new Date();
            System.out.println(dirPath + "导入成功,用时："
                    + (t2.getTime() - t1.getTime()) / 1000 + "s");

        }

    }

}
//线程写csv文件
class MDownThread extends Thread {
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    public String dirPath;
    public String cvsfile;
    public String ym;
    public String d;
    public int dj;

    public MDownThread(String dirPath, String cvsfile, String ym, String d) {
        this.dirPath = dirPath;
        this.cvsfile = cvsfile;
        this.ym = ym;
        this.d = d;
        this.dj = dj;
    }

    @Override
    public void run() {
        try {
            // 生成CsvReader对象，以，为分隔符，utf-8编码方式
            CsvReader r = new CsvReader(dirPath + cvsfile, ',',
                    Charset.forName("utf-8"));
            CsvWriter wr = new CsvWriter("E:\\" + ym + "\\"
                    + ym + d + "\\" + cvsfile, ',', Charset.forName("utf-8"));
            // System.out.println(dirPath+cvsfile[j]);
            // 逐条读取记录，直至读完
            while (r.readRecord()) {
                String regex = "(\\d+)";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(cvsfile);
                String stockID = "";
                while (matcher.find()) {
                    stockID = matcher.group(1);
                }
                String date = sdf1.format(sdf.parse(ym + d + r.get(0)));
                File f = new File("E:\\shamrock\\project\\" + ym + "\\" + ym
                        + d + "\\" + cvsfile);
                if (!f.getParentFile().exists()) {
                    if (!f.getParentFile().mkdirs()) {
                        return;
                    }
                }
                String[] contents = { stockID, date, r.get(1), r.get(2),
                        r.get(3) };
                wr.writeRecord(contents);

            }

            r.close();
            wr.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

class LoadMysqlThread extends Thread {
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    public String dirPath;
    public String cvsfile;
    public String ym;
    public String d;
    public int dj;

    public LoadMysqlThread(String dirPath, String cvsfile, String ym, String d) {
        this.dirPath = dirPath;
        this.cvsfile = cvsfile;
        this.ym = ym;
        this.d = d;
        this.dj = dj;
    }
    //线程导入数据库
    @Override
    public void run() {
        try {

            Connection conn = null;
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/tickdata";
            Class.forName(driver);
            conn = DriverManager.getConnection(url, "root", "123456");
            PreparedStatement pstmt = conn
                    .prepareStatement(" LOAD DATA INFILE 'E:/project/"
                            + ym
                            + "/"
                            + ym
                            + d
                            + "/"
                            + cvsfile
                            + "'   INTO TABLE d201505 FIELDS TERMINATED BY ',' ENCLOSED BY '\"' ESCAPED BY '\\\\' LINES TERMINATED BY '\\n' (stockID,date,price,buysell,volume);");
            pstmt.execute();
            conn.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

