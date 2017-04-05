package ua.dzms.useraccounting.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ua.dzms.useraccounting.entity.User;

import java.net.URL;
import java.util.ResourceBundle;


public class ModalController implements Initializable {
    private User user;
    @FXML
    private Label label;
    @FXML
    private TextField inputFirstName;
    @FXML
    private TextField inputLastName;
    @FXML
    private DatePicker inputDate;
    @FXML
    private Button buttonOk;
    @FXML
    private Button buttonCancel;

    public ModalController(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputFirstName.setText(user.getFirstName());
        inputLastName.setText(user.getLastName());
        inputDate.setValue(user.getDateOfBirth());

        buttonOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clickOk();
            }
        });
        buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clickCancel();
            }
        });
    }

    private void clickOk() {
        user.setFirstName(inputFirstName.getText());
        user.setLastName(inputLastName.getText());
        user.setDateOfBirth(inputDate.getValue());

        (label.getScene().getWindow()).setUserData(user);
        (label.getScene().getWindow()).hide();
    }

    private void clickCancel() {
        (label.getScene().getWindow()).hide();
    }
}
