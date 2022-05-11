module jtagaca.taskanalytics_ {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.net.http;
    requires java.desktop;
    requires httpcore;
    requires com.fasterxml.jackson.databind;
    requires httpclient;
    requires okhttp3;
    requires json;
    requires com.google.gson;

    opens jtagaca.taskanalytics_ to javafx.fxml;
    exports jtagaca.taskanalytics_;
}