package vn.hackademics.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import vn.hackademics.database.SessionUtil;
import vn.hackademics.model.User;

public class UserDAO {

    public void addUser(User bean){
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        addUser(session,bean);
        tx.commit();
        session.close();
    }

    private void addUser(Session session, User bean){
        User user = new User();
        user.setEmail_us(bean.getEmail_us());
        user.setPassword_us(bean.getPassword_us());
        user.setFirst_name(bean.getFirst_name());
        user.setLast_name(bean.getLast_name());
        user.setDate_of_birth(bean.getDate_of_birth());
        user.setProfile_picture(bean.getProfile_picture());
        user.setName_school(bean.getName_school());
        session.save(user);
    }

    public List<User> getUser(int id_us){
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from User where id_us = :id_us");
        query.setParameter("id_us",id_us);
        List<User> userId =  query.list();
        session.close();
        return userId;
    }

    public List<User> getAllUser(){
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from User");
        List<User> users =  query.list();
        session.close();
        return users;
    }

    public int deleteUser(int id_us) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete User where id_us = :id_us");
        query.setParameter("id_us",id_us);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }

    public int updateUser(int id_us, User emp){
        if(id_us <=0) return 0;
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("update User set email_us = :email_us, password_us = :password_us,  first_name = :first_name, last_name = :last_name, date_of_birth = :date_of_birth, profile_picture = :profile_picture, name_school = :name_school where id_us = :id_us");
        query.setParameter("id_us",id_us);
        query.setParameter("email_us",emp.getEmail_us());
        query.setParameter("password_us",emp.getPassword_us());
        query.setParameter("first_name",emp.getFirst_name());
        query.setParameter("last_name",emp.getLast_name());
        query.setParameter("date_of_birth",emp.getDate_of_birth());
        query.setParameter("profile_picture",emp.getProfile_picture());
        query.setParameter("name_school",emp.getName_school());
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }

}