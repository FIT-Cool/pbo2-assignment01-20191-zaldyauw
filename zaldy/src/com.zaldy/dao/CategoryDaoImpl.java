package com.zaldy.dao;

import com.zaldy.entity.category;
import com.zaldy.util.DBHelper;
import com.zaldy.util.DaoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements DaoService<category> {

    @Override
    public List<category> showAll()  {
        List<category> categories = new ArrayList<>();
        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "SELECT * FROM category";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                category category = new category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
            rs.close();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public int addData(category object) {
        int result = 0;
        try {
            Connection connection = DBHelper.createMySQLConnection();
            String query = "INSERT INTO category(id, name) VALUES(?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,object.getId());
            ps.setString(2, object.getName());

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
    public int deleteData(category object) {
        return 0;
    }

    @Override
    public int updateData(category object) {
        return 0;
    }

}
