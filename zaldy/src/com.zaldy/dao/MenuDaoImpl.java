package com.zaldy.dao;

import com.zaldy.entity.category;
import com.zaldy.entity.menu;
import com.zaldy.util.DBHelper;
import com.zaldy.util.DaoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl implements DaoService<menu> {

    @Override
    public List<menu> showAll() {
        List<menu>  menus = new ArrayList<>();

        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "SELECT * FROM menu m join category c on category_id = c.id";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                menu menu = new menu();
                menu.setId(rs.getInt("id"));
                menu.setName(rs.getString("name"));
                menu.setPrice(rs.getDouble("price"));
                menu.setDescription(rs.getString("description"));
                menu.setRecomended(rs.getBoolean("recomended"));
                menu.setPhoto(rs.getString("photo"));
                menu.setCreated(rs.getDate("created"));
                category category = new category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                menu.setCategory(category);
                menus.add(menu);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }


    @Override
    public int addData(menu object) {
        int result = 0;
        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "INSERT INTO menu(id,name, price, description, recomended, photo, category_id) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.valueOf(object.getId()));
            ps.setString(2, object.getName());
            ps.setDouble(3, Double.valueOf(object.getPrice()));
            ps.setString(4, object.getDescription());
            ps.setBoolean(5, object.getRecomended());
            ps.setString(6, object.getPhoto());
            ps.setInt(7, Integer.valueOf(object.getCategory().getId()));
            if (ps.executeUpdate() != 0) {
                connection.commit();
                result = 1;
            } else {
                connection.rollback();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteData(menu object) {
        int result = 0;
        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "DELETE from menu WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,object.getId());
            if(ps.executeUpdate() != 0)
            {
                connection.commit();
                result = 1;
            }
            else
            {
                connection.rollback();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateData(menu object) {
        int result = 0;
        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "UPDATE menu SET name=? where id= ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(2,object.getId());
            ps.setString(1, object.getName());
            if (ps.executeUpdate() != 0) {
                connection.commit();
                result = 1;
            } else {
                connection.rollback();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
