package ua.dzms.useraccounting.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import ua.dzms.useraccounting.service.UserService;
import ua.dzms.useraccounting.service.ServiceLocator;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class StarterController implements Initializable {
    private ObservableList<User> users = FXCollections.observableArrayList();
    private UserService userService;

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

    public StarterController() {
        userService = ServiceLocator.getInstance().getService(UserService.class);
    }

    public void exit() {
        ((Stage) userTable.getScene().getWindow()).close();
    }

    public void showAllUsers() {
        clearFields();
        users.clear();
        users.addAll(userService.getAll());
        userTable.setItems(users);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showAllUsers();
    }

    public void addUser() {
        User newUser = new User();
        newModalStage(newUser);
        showAllUsers();
    }

    public void editUser() {
        if (isSelectedItem()) {
            User editUser = userTable.getSelectionModel().getSelectedItem();
            newModalStage(editUser);
            showAllUsers();
        }
    }

    public void deleteUser() {
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
                return (isContainsFirstName(user.getFirstName()) &&
                        isContainsLastName(user.getLastName()) &&
                        isBetweenDate(user.getDateOfBirth()));
            }
        };
        FilteredList<User> filteredList = new FilteredList<>(users, predicate);

        userTable.setItems(filteredList);
    }

    private void clearFields() {
        firstNameField.clear();
        lastNameField.clear();
        fromDateField.setValue(null);
        toDateField.setValue(null);
    }

    private void newModalStage(User user) {
        try {
            Stage modalStage = new Stage();
            FXMLLoader loader = new FXMLLoader(System.class.getResource("/fxml/modal.fxml"));
            loader.setController(new ModalController(user));
            Parent root = loader.load();
            modalStage.setScene(new Scene(root));
            modalStage.initOwner(userTable.getScene().getWindow());
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException("Error load loader in parent root");
        }
    }

    private boolean isContainsFirstName(String firstName) {
        return (firstName.toLowerCase()).contains((firstNameField.getText()).toLowerCase());
    }

    private boolean isContainsLastName(String lastName) {
        return (lastName.toLowerCase()).contains((lastNameField.getText()).toLowerCase());
    }

    private boolean isBetweenDate(LocalDate date) {
        boolean result = true;
        if (fromDateField.getValue() != null){
            result = date.isAfter(fromDateField.getValue());
        }
        if (toDateField.getValue() != null){
            result = date.isBefore(toDateField.getValue());
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
