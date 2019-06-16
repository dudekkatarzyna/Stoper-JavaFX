package sample.java;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class nameWindow implements Initializable {


    Stage stage = new Stage();
    public void name() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("author.fxml"));

        loader.load();

        Parent root = loader.getRoot();
        stage.setTitle("Nazwa pomiaru");
        stage.setScene(new Scene(root, 350, 400));
        stage.setResizable(false);

        stage.show();

    }


    @FXML
    Button addButton,cancelButton;
    @FXML
    static
    TextField nameField;

    private TableView recordList;
    private ObservableList<String> record;

    public void addButton(){

        System.out.println(nameField.getText());
        if(validate(nameField.getText())){

            record.add(nameField.getText());
            nameField.setText("");
            recordList.setItems(record);

            new Controller().printToList();

        }

    }
    public void setRecord(ObservableList<String> record) {
        this.record = record;
    }

    public void setRecordList(TableView recordList) {
        this.recordList = recordList;
    }

    private boolean validate(String text) {
        String regex = "^[a-zA-Z]+$";
        if (text.matches(regex)) {
           return true;
        }
        else
            return false;
    }

    public void  cancelButton(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
