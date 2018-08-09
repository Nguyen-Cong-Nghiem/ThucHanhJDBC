package services;

import models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.sun.webkit.perf.WCFontPerfLogger.log;

public class ProductServiceImpl implements ProductService {
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static final String url="jdbc:mysql://localhost:3306/AwesomeBusiness";
    static final String user = "root";
    static final String pass = "Nghi3mgi4n";

    public ProductServiceImpl() {

    }
    @Override
    public List<Product> findAll() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        Class.forName(JDBC_DRIVER);
        log("Dang ket noi toi co so du lieu...");
        connection = DriverManager.getConnection(url, user, pass);
        if (connection!=null)
            System.out.println("Ket noi thanh cong");
        log("Tao lenh truy van du lieu");
        statement = connection.createStatement();
        String sql = "select id,code,name from product";
        log("Dang  truy van du lieu");
        ResultSet resultSet = statement.executeQuery(sql);
        List<Product> products = new ArrayList<>();
        log("Ket qua truy van");
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setCode(resultSet.getString("code"));
            product.setName(resultSet.getString("name"));

            products.add(product);
        }

        log("Hoan thanh thu thap ket qua. Dang dong cac ket noi...");
        resultSet.close();
        statement.close();
        connection.close();

        log("Thanh cong!");
        return products;
    }

    private void log(String s) {
        System.out.println("ProductServiceImpl" + s);
    }
}
