module org.hugo.dein.jasperejercicio3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;


    opens org.hugo.dein.jasperejercicio3 to javafx.fxml;
    exports org.hugo.dein.jasperejercicio3;
}