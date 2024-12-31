package com.example.aoopproject.controllers;

import com.example.aoopproject.database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.aoopproject.views.ViewFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private VBox mainVBox;

    @FXML
    private ComboBox<String> userTypeComboBox;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label statusLabel;

    @FXML
    void handleLoginButtonAction(ActionEvent event) {
        String userType = userTypeComboBox.getValue();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (authenticate(username, password, userType)) {
            statusLabel.setText("Login Successful");
            statusLabel.setVisible(true);

            // Navigate to the appropriate dashboard
            Stage stage = (Stage) loginButton.getScene().getWindow();
            if ("Admin".equals(userType)) {
                ViewFactory.getInstance().showAdminDashboard(stage);
            } else {
                ViewFactory.getInstance().showStudentDashboard(stage);
            }
        } else {
            statusLabel.setText("Login Failed");
            statusLabel.setVisible(true);
        }
    }

    private boolean authenticate(String username, String password, String userType) {
        String table = userType.equals("Admin") ? "Users" : "Users"; // edit if there are separate table for admin and students
        String query = "SELECT * FROM " + table + " WHERE ID = ? AND Password = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next(); // return true if a record is found

        } catch (SQLException e) {
            // Log the exception (consider using a logging framework)
            System.err.println("Database error: " + e.getMessage());
            // Optionally, update the status label to inform the user
            statusLabel.setText("An error occurred. Please try again later.");
            statusLabel.setVisible(true);
        }

        return false;
    }

    @FXML
    public void initialize() {
        userTypeComboBox.getItems().addAll("Student", "Admin");
        statusLabel.setVisible(false);
    }
}