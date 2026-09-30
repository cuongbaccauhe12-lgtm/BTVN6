package com.example.buoi6.until;

import com.example.buoi6.entity.GiangVien;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties props = new Properties();
        props.put(Environment.DIALECT, "org.hibernate.dialect.SQLServer2016Dialect");
        props.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        props.put(Environment.URL, "jdbc:sqlserver://localhost:1444;databaseName=BTVNBuoi7;encrypt=true;trustServerCertificate=true;");
        props.put(Environment.USER, "sa");
        props.put(Environment.PASS, "123456789");   // đổi mật khẩu của bạn
        props.put(Environment.SHOW_SQL, "true");

        conf.setProperties(props);
        conf.addAnnotatedClass(GiangVien.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);
    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }
    public static void main(String[] args) {
        System.out.println(getFACTORY());
        System.out.println("Kết nối SQL thành công!");
    }
}