package ua.dzms.useraccounting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.dbcp2.BasicDataSource;
import ua.dzms.useraccounting.dao.UserDao;
import ua.dzms.useraccounting.dao.jdbc.JdbcUserDao;
import ua.dzms.useraccounting.service.UserService;
import ua.dzms.useraccounting.service.ServiceLocator;
import ua.dzms.useraccounting.service.impl.UserServiceImpl;

import java.io.IOException;

public class Starter extends Application {

    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/useraccount");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        UserDao userDao = new JdbcUserDao(dataSource);
        UserService service = new UserServiceImpl(userDao);
        ServiceLocator serviceLocator = ServiceLocator.getInstance();
        serviceLocator.register(UserService.class, service);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(System.class.getResource("/fxml/starter.fxml"));
        primaryStage.setTitle("User Accounting");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}