module com.example.tictactoemultiplayer {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.tictactoemultiplayer to javafx.fxml;
    exports com.example.tictactoemultiplayer;
}