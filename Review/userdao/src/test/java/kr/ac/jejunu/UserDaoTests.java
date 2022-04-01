package kr.ac.jejunu;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.greaterThan;

public class UserDaoTests {
    String name = "hulk";
    String password = "1234";

    @Test
    public void findById() throws SQLException, ClassNotFoundException {
        Integer id = 1;

//        ConnectionMaker connectionMaker = new JejuConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();

        User user = userDao.findById(id);
 
        assertThat(user.getId(),is(id));
        assertThat(user.getName(),is(name));
        assertThat(user.getPassword(),is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
//        ConnectionMaker connectionMaker = new JejuConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(0));

        User insertedUser = userDao.findById((user.getId()));
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }
//    @Test
//    public void findByIdHalla() throws SQLException, ClassNotFoundException {
//        Integer id = 1;
//        ConnectionMaker connectionMaker = new HallaConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
//
//
//        User user = userDao.findById(id);
//
//        assertThat(user.getId(),is(id));
//        assertThat(user.getName(),is(name));
//        assertThat(user.getPassword(),is(password));
//    }
//
//    @Test
//    public void insertHalla() throws SQLException, ClassNotFoundException {
//        User user = new User();
//        user.setName(name);
//        user.setPassword(password);
//        ConnectionMaker connectionMaker = new HallaConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
//        userDao.insert(user);
//        assertThat(user.getId(), greaterThan(0));
//
//        User insertedUser = userDao.findById((user.getId()));
//        assertThat(insertedUser.getName(), is(name));
//        assertThat(insertedUser.getPassword(), is(password));
//    }
}


