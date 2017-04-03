package ua.dzms.useraccounting.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.dzms.useraccounting.entity.User;

public class StarterController {
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
        ((Stage)userTable.getScene().getWindow()).close();
    }
}
