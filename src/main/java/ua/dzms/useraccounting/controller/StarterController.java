package ua.dzms.useraccounting.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.dzms.useraccounting.entity.User;
import ua.dzms.useraccounting.service.Service;
import ua.dzms.useraccounting.service.impl.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StarterController implements Initializable {
    private ObservableList<User> users = FXCollections.observableArrayList();
    private Service userService = new UserService();

    @FXML
    private TableView<User> userTable;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private DatePicker fromDateField;
    @FXML
    private DatePicker toDateField;

    public void exit(ActionEvent actionEvent) {
        ((Stage) userTable.getScene().getWindow()).close();
    }

    public void showAllUsers() {
        users.clear();
        users.addAll(userService.getAll());
        userTable.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userTable.setItems(users);
    }

    public void addUser(ActionEvent actionEvent) throws IOException {
        User newUser = new User();

        Stage modalStage = new Stage();
        FXMLLoader loader = new FXMLLoader(System.class.getResource("/modal.fxml"));
        loader.setController(new ModalController(newUser));
        Parent root = loader.load();
        modalStage.setScene(new Scene(root));

        modalStage.setTitle(((Button) actionEvent.getSource()).getText() + "User");
        modalStage.showAndWait();
        if (modalStage.getUserData() != null){
            userService.addUser((User) modalStage.getUserData());
            showAllUsers();
        }
        modalStage.close();
    }
}
