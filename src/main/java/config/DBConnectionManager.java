package config;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionManager {
    // Метод для получения текущего соединения
    @Getter
    private static Connection connection;

    public static void init() {
        Properties properties = new Properties();

        try (InputStream input = DBConnectionManager.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                System.err.println("Извините, не удалось найти файл database.properties");
                return;
            }

            // Загружаем файл свойств
            properties.load(input);

            // Получаем свойства из файла
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            // Загрузка драйвера MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Установка соединения
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Соединение успешно установлено.");
        } catch (IOException ex) {
            System.err.println("Ошибка при загрузке файла свойств.");
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Не удалось загрузить драйвер MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных.");
            e.printStackTrace();
        }
    }

    // Метод для закрытия соединения
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Соединение закрыто.");
            } catch (SQLException e) {
                System.err.println("Ошибка при закрытии соединения.");
                e.printStackTrace();
            }
        }
    }
}

