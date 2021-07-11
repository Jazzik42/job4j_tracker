package ru.job4j.tracker;

import java.io.InputStream;
import java.security.spec.ECField;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {

    private Connection cn;

    @Override
    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            Class.forName(properties.getProperty("driver-class-name"));
            String url = properties.getProperty("url");
            String name = properties.getProperty("username");
            String password = properties.getProperty("password");
            cn = DriverManager.getConnection(url, name, password);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        Item newItem = new Item();
        try (PreparedStatement pStatement = cn.prepareStatement(
                "insert into Tracker(name, created) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            Timestamp timestamp = Timestamp.valueOf(item.getCreated());
            pStatement.setString(1, item.getName());
            pStatement.setTimestamp(2, timestamp);
            pStatement.executeUpdate();
            try (ResultSet generatedKeys = pStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newItem.setId(generatedKeys.getInt(1));
                    newItem.setName(item.getName());
                    newItem.setCreated(item.getCreated());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newItem;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        try (PreparedStatement pStatement = cn.prepareStatement(
                "update from Tracker set name = ?, created = ? where id = ?"
        )) {
            Timestamp timestamp = Timestamp.valueOf(item.getCreated());
            pStatement.setString(1, item.getName());
            pStatement.setTimestamp(2, timestamp);
            pStatement.setInt(3, item.getId());
            result = pStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement pStatement = cn.prepareStatement(
                "delete from Tracker where id = ?")) {
            pStatement.setInt(1, id);
            result = pStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement pStatement = cn.prepareStatement(
                "select * from Tracker")) {
            try (ResultSet resultSet = pStatement.executeQuery()) {
                while (resultSet.next()) {
                    LocalDateTime localDateTime = resultSet.getTimestamp(3).toLocalDateTime();
                    result.add(new Item(resultSet.getString(2),
                            resultSet.getInt(1), localDateTime));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement pStatement = cn.prepareStatement(
                "select from Tracker where name = ?")) {
            pStatement.setString(1, key);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                LocalDateTime localDateTime = resultSet.getTimestamp(3).toLocalDateTime();
                result.add(new Item(
                        resultSet.getString(2),
                        resultSet.getInt(1),
                        localDateTime));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        try (PreparedStatement pStatement = cn.prepareStatement(
                "select from Tracker where id = ?")) {
            pStatement.setInt(1, id);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                LocalDateTime localDateTime = resultSet.getTimestamp(3).toLocalDateTime();
                item = new Item(
                        resultSet.getString(2),
                        resultSet.getInt(1),
                        localDateTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}