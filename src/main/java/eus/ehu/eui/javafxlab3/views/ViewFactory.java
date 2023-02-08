package eus.ehu.eui.javafxlab3.views;

import eus.ehu.eui.javafxlab3.JavaFXLab3AppLauncher;
import eus.ehu.eui.javafxlab3.controllers.VentanaFinalController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Optional;

public class ViewFactory {


    public static void mostrarVentanaFinal(int width, int height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXLab3AppLauncher.class.getResource("views/VistaVentanaFinal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        // Registra el gestor de eventos de pulsación de tecla
        var controller = (VentanaFinalController) fxmlLoader.getController();
        // TODO: registrar el gestor de eventos de pulsación de tecla
        Stage stage = new Stage();
        stage.setOnCloseRequest(e -> System.exit(0));
        stage.setTitle("Ventana final");
        stage.setScene(scene);
        stage.show();
    }

    public static void mostrarVentanaConfiguracion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFXLab3AppLauncher.class.getResource("views/VistaVentanaConfiguracion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Ventana configuracion");
        stage.setScene(scene);
        stage.show();
    }


    public static Optional<Pair<String, String>> mostrarVentanaLogin() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Ventana de acceso");
        dialog.setHeaderText("Introduce tus credenciales");

        // Establecer los tipos de botones
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Campos para el login
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Nombre de usuario");
        PasswordField password = new PasswordField();
        password.setPromptText("Contraseña");

        grid.add(new Label("Nombre:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Contraseña"), 0, 1);
        grid.add(password, 1, 1);

        // Activar desactivar boton de login en funcion de que se haya especificado el nombre de usuario
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Validar los datos
        username.textProperty().addListener((observable, oldValue, newValue) -> loginButton.setDisable(newValue.trim().isEmpty()));

        dialog.getDialogPane().setContent(grid);

        // Establecer el foco por defecto en el username
        Platform.runLater(() -> username.requestFocus());

        // Convertir los datos al formato requerido (Pair<String, String> si se introducen datos)
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            } else {
                return null;
            }
        });

        return dialog.showAndWait();
    }

    public static void mostrarMensajeError() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText("Error: no se ha iniciado la sesión. la aplicación finalizara");
        alert.showAndWait();
    }
}
