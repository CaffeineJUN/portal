package kr.ac.jejunu;

import javax.xml.transform.Result;
import java.sql.*;

public class UserDao {
    public User findById(Integer id) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
//        sql 작성
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from userinfo where id = ?"
        );
        preparedStatement.setInt(1, id);
//        sql 실행
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
//        User에 데이터매핑
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

//        자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
//        USEr리턴
        return user;
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        //        드라이버 로딩
        Connection connection = getConnection();
//        sql 작성
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into userinfo (name,password) values (?,?) ", Statement.RETURN_GENERATED_KEYS
        );
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        user.setId(resultSet.getInt(1));
//        자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        //        드라이버 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");
//        커넥션
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/portal?",
                "root",
                "hyeok!0560"
        );
    }
}

