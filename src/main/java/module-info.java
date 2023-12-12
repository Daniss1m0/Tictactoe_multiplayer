module com.example.tictactoemultiplayer {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    requires java.sql;

    opens ClientFiles to javafx.fxml;
    exports ClientFiles;
    exports ReplicatedClasses;
    opens ReplicatedClasses to javafx.fxml;
}