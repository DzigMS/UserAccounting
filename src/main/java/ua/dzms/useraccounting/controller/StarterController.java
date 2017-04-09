package ua.dzms.useraccounting.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ua.dzms.useraccounting.entity.User;
import ua.dzms.useraccounting.service.Service;
import ua.dzms.useraccounting.service.impl.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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

    public void exit() {
        ((Stage) userTable.getScene().getWindow()).close();
    }

    public void showAllUsers() {
        clearField();
        users.clear();
        users.addAll(userService.getAll());
        userTable.setItems(users);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showAllUsers();
    }

    public void addUser() throws IOException {
        User newUser = new User();
        modalStage(newUser);
        showAllUsers();
    }

    public void editUser() throws IOException {
        if (isSelectedItem()) {
            User editUser = userTable.getSelectionModel().getSelectedItem();
            modalStage(editUser);
            showAllUsers();
        }
    }

    public void delete() {
        if (isSelectedItem()) {
            User removeUser = userTable.getSelectionModel().getSelectedItem();
            userService.removeUser(removeUser);
            showAllUsers();
        }
    }

    public void contains() {
        Predicate<User> predicate = new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return isContains(user);
            }
        };
        FilteredList<User> filteredList = new FilteredList<>(users, predicate);

        userTable.setItems(filteredList);
    }

    private void clearField() {
        firstNameField.clear();
        lastNameField.clear();
        fromDateField.setValue(null);
        toDateField.setValue(null);
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

    private boolean isContains(User user) {
        boolean result;
        result = (user.getFirstName().toLowerCase()).contains((firstNameField.getText()).toLowerCase());
        if (result) {
            result = (user.getLastName().toLowerCase()).contains((lastNameField.getText()).toLowerCase());
        } else {
            return false;
        }
        if (fromDateField.getValue() != null) {
            if (result) {
                result = (user.getDateOfBirth()).isAfter(fromDateField.getValue());
            } else {
                return false;
            }
        }
        if (toDateField.getValue() != null) {
            if (result) {
                result = (user.getDateOfBirth()).isBefore(toDateField.getValue());
            } else {
                return false;
            }
        }
        return result;
    }

    private boolean isSelectedItem() {
        if (userTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Select the Item");
            alert.show();
            return false;
        }
        return true;
    }
}
