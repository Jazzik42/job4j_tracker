package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dump))) {
            br.lines().forEach(x ->
            {
                String[] data = x.split(";");
                users.add(new User(data[0],
                        data[1]));
            });
            return users;
        }
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection con = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password"))) {
            for (User user : users) {
                try (PreparedStatement pStatement = con.prepareStatement(
                        "insert into users(name, email) values(?, ?)")) {
                    pStatement.setString(1, user.getName());
                    pStatement.setString(2, user.getEmail());
                    pStatement.execute();
                }
            }

        }
    }

    public static void main(String[] args) throws Exception  {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("./app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}

