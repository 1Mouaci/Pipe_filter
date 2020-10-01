package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;

public class Controller {

    static int a;
    static int b;
    static int r;

    static String s;

    public TextField op1;
    public TextField op2;
    public Label res;
    public Label err;


    public void sommeCLique() {

        if (op1.getText().compareTo("") != 0 && op2.getText().compareTo("") != 0) {
            err.setVisible(false);
            IntegerStringConverter converter = new IntegerStringConverter();
            Pipe p1 = new BlockingQueue();
            Pipe p2 = new BlockingQueue();
            Pipe p3 = new BlockingQueue();

            a = converter.fromString(op1.getText());
            b = converter.fromString(op2.getText());
            s = (op1.getText() + " " + op2.getText());

            p1.dataIN(s);
            Filter somme = new Somme(p1, p2);
            Filter writeTrace = new WriteTrace(p2, p3);

            Thread th1 = new Thread(somme);
            Thread th2 = new Thread(writeTrace);

            th1.start();
            th2.start();

            res.setText(p3.dataOUT());
        } else {
            err.setText("Veuillez Remplire les deux champs");
            err.setVisible(true);
        }
    }

    public void traceClique() {
        IntegerStringConverter converter = new IntegerStringConverter();
        Pipe p1 = new BlockingQueue();
        Pipe p2 = new BlockingQueue();
        p1.dataIN("");
        Filter trace = new Trace(p1, p2);
        Thread th1 = new Thread(trace);
        th1.start();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(Main.stage);
        alert.setTitle("Trace");
        alert.setHeaderText("trace de 10 dernier elements");
        alert.setContentText(p2.dataOUT());
        alert.showAndWait();

    }

    public void closeClique() {
        Main.stage.close();
    }

    public void produitCLique() {

        if (op1.getText().compareTo("") != 0 && op2.getText().compareTo("") != 0) {
            err.setVisible(false);
            IntegerStringConverter converter = new IntegerStringConverter();
            Pipe p1 = new BlockingQueue();
            Pipe p2 = new BlockingQueue();
            Pipe p3 = new BlockingQueue();

            s = (op1.getText() + " " + op2.getText());

            p1.dataIN(s);
            Filter produit = new Produit(p1, p2);
            Filter writeTrace = new WriteTrace(p2, p3);

            Thread th1 = new Thread(produit);
            Thread th2 = new Thread(writeTrace);

            th1.start();
            th2.start();

            res.setText(p3.dataOUT());
        } else {
            err.setText("Veuillez Remplire les deux champs");
            err.setVisible(true);
        }
    }

    public void factCLique() {

        if (op1.getText().compareTo("") != 0) {
            err.setVisible(false);
            IntegerStringConverter converter = new IntegerStringConverter();
            Pipe p1 = new BlockingQueue();
            Pipe p2 = new BlockingQueue();
            Pipe p3 = new BlockingQueue();

            s = (op1.getText());

            p1.dataIN(s);
            Filter fact = new Fact(p1, p2);
            Filter writeTrace = new WriteTrace(p2, p3);

            Thread th1 = new Thread(fact);
            Thread th2 = new Thread(writeTrace);

            th1.start();
            th2.start();

            res.setText(p3.dataOUT());
        }else {
            err.setText("Veuillez Remplire le champ nombre 1");
            err.setVisible(true);
        }
    }
}


