package ua.dzms.useraccounting.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
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
        showAllUsers();
    }

    public void addUser(ActionEvent actionEvent) throws IOException {
        User newUser = new User();
        modalStage(newUser);
        showAllUsers();
    }

    public void editUser(ActionEvent actionEvent) throws IOException {
        if (userTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Select the Item");
            alert.show();
        } else {
            User editUser = userTable.getSelectionModel().getSelectedItem();
            modalStage(editUser);
            showAllUsers();
        }
    }

    private void modalStage(User user) throws IOException {
        Stage modalStage = new Stage();
        FXMLLoader loader = new FXMLLoader(System.class.getResource("/modal.fxml"));
        loader.setController(new ModalController(user));
        Parent root = loader.load();
        modalStage.setScene(new Scene(root));
        modalStage.initOwner(userTable.getScene().getWindow());
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.showAndWait();
    }
}
