package com.lzy.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class sqlSessionUtils {
    private static SqlSessionFactoryBuilder builder ;

    static public SqlSessionFactoryBuilder SqlSessionFactory()
    {
        if(builder!=null)
        {
              if(builder==null)
              {
                  builder = new SqlSessionFactoryBuilder();
              }

        }
        return builder;
    }

    static  public  SqlSession getSqlSession()
    {
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("application-mybatis.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.build(in).openSession();
    }
}
