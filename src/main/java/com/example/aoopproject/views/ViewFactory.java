package com.example.aoopproject.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class ViewFactory {

    private static ViewFactory instance;

    private ViewFactory() {
    }

    public static ViewFactory getInstance() {
        if (instance == null) {
            instance = new ViewFactory();
        }
        return instance;
    }

    public void showAdminDashboard(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/Admin.fxml"));
            Scene scene = new Scene(loader.load());

            // Add the admin stylesheet
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/Admin.css")).toExternalForm());

            stage.setScene(scene);
            stage.setTitle("Admin Dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showStudentDashboard(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/student/Student.fxml"));
            Scene scene = new Scene(loader.load());

            // Add the student stylesheet
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/Student.css")).toExternalForm());

            stage.setScene(scene);
            stage.setTitle("Student Dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
