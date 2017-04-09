package ua.dzms.useraccounting.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ua.dzms.useraccounting.entity.User;
import ua.dzms.useraccounting.service.impl.UserService;

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
        if (user.getId() != 0){
            label.setText("Edit User");
            buttonOk.setText("Edit");
        }
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

        if (inputFirstName.getText() != null &&
                inputLastName.getText() != null &&
                inputDate.getValue() != null) {
            user.setFirstName(inputFirstName.getText());
            user.setLastName(inputLastName.getText());
            user.setDateOfBirth(inputDate.getValue());
            service();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText(buttonOk.getText() + " is done");
            alert.setTitle("DONE");
            alert.show();

            ((Stage) label.getScene().getWindow()).close();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Some field is empty");
            alert.setTitle("Error");
            alert.show();
        }
    }

    private void clickCancel() {
        ((Stage) label.getScene().getWindow()).close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText(null);
        alert.setContentText("Operation has been canceled");
        alert.show();
    }

    private void service(){
        UserService userService = new UserService();
        if (user.getId() != 0) {
            userService.editUser(user);
        } else {
            userService.addUser(user);
        }
    }
}
