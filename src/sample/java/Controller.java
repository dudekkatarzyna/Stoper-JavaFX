package sample.java;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class Controller  implements Runnable, Initializable {

    Thread t;
    int hcnt=0,mcnt=0,scnt,mscnt=0;
    String nstr="", mstr="";
    int cnt=0;

    public Controller(){
        t=new Thread(this);
        reset();
    }

    @FXML
    Button startButton, resetButton;
    @FXML
    Label bDisplay;
    @FXML
    Label lDisplay;
    @FXML
    TableView table;


     private LocalTime start;
     private LocalTime finish;

    public void startButton(){
            start=LocalTime.now();
            cnt++;
            if(cnt==1)
                t.start();
            else
                t.resume();
        }


    public void resetButton() throws IOException {

        finish=LocalTime.now();
        t.suspend();

      Platform.runLater(() -> {
          printToList();
          reset();
      });

    }
    public void reset(){
        hcnt=0;
        mcnt=0;
        scnt=0;
        mscnt=0;
        nstr="00:00:00";
        mstr="000";
        display();


    }

    @FXML
    public void display() {
        try {

            Platform.runLater(() -> {
                bDisplay.setText(nstr);
                lDisplay.setText(mstr);
            });
        } catch (Exception e) {
            System.out.println("display");
        }

    }

    public void setTimeCounter(){
        nstr="";
        if(hcnt<10)
            nstr="0"+hcnt;
        else
            nstr=""+hcnt;

        if(mcnt<10)
            nstr+=":0"+mcnt;
        else
            nstr+=":"+mcnt;

        if(scnt<10)
            nstr+=":0"+scnt;
        else
            nstr+=":"+scnt;
    }

    public void setMTimeCounter(){
        mstr="";
        if(mscnt<10)
            mstr="00"+mscnt;
        else if(mscnt>=10 && mscnt<100)
            mstr="0"+hcnt;
        else
            mstr=""+mscnt;
    }


    public void run(){

         try {
             while (true) {

                 mscnt++;
                 if (mscnt > 999) {
                     mscnt = 0;
                     scnt++;
                 }
                 if (scnt > 59) {
                     scnt = 0;
                     hcnt++;
                 }
                 if (mcnt > 59) {
                     mcnt = 0;
                     hcnt++;
                 }
                 if (hcnt > 99)
                     resetButton();


                 setTimeCounter();
                 setMTimeCounter();
                 Thread.sleep(1);
                 display();


             }
         }catch(Exception e){}
    }

        @FXML
        TableColumn nameColumn, startColumn,finishColumn,timeColumn;

    public void printToList() {

        String[] imie={"Paweł","Robert","Karol","Ania","Krzysztof","Adam","Kasia","Michał","Bartek"};

        Record rekord=new Record(imie[mscnt%9], start,finish,bDisplay.getText()+"."+lDisplay.getText()); //JAK TU PRZESŁAĆ JEBANE IMIE

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        finishColumn.setCellValueFactory(new PropertyValueFactory<>("finish"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        table.getItems().add(rekord);
        timeColumn.setSortType(TableColumn.SortType.ASCENDING);
        table.getSortOrder().clear();
        table.getSortOrder().add(timeColumn);


    }

    public void deleteAction(){
    ObservableList<Record> recordsSelected, allRecords;
    allRecords=table.getItems();
    recordsSelected=table.getSelectionModel().getSelectedItems();

    recordsSelected.forEach(allRecords::remove);
    }

    public void deleteAllAction(){
       ObservableList<Record> allRecords=table.getItems();
       allRecords.removeAll(allRecords);
    }
    public void resetDisplay(){
        reset();
        display();
    }

    @FXML
    private void closeApplication() {
        System.exit(0);
    }
    public void showAuthor() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("author.fxml"));

        loader.load();

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("O autorze");
        stage.setScene(new Scene(root, 350, 400));
        stage.setResizable(false);

        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
