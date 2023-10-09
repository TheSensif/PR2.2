import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class ControllerDesktop {

    @FXML
    Label labelTasca1;

    @FXML
    Button bTasca1;

    @FXML
    ProgressBar pBtasca1;

    NumeroProgresBar obj = new NumeroProgresBar();
    Future<List<Integer>> f0 = obj.calculaate();
    
    private volatile boolean isRunning = true; // Bandera para controlar el ciclo del hilo

    public void startTaca1() {
        switch (bTasca1.getText()) {
            case "Iniciar":
                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() {
                        try {
                            Platform.runLater(() -> {
                                bTasca1.setText("Atura");
                            });

                            List<Integer> numeros = f0.get(); // Obtiene la lista de números

                            for (Integer numero : numeros) {
                                if (!isRunning) {
                                    break; // Si la bandera indica detenerse, sal del ciclo
                                }
                                Thread.sleep(1000);
                                Double numeroProgres = (double) (numero / 100.0);
                                Platform.runLater(() -> {
                                    labelTasca1.setText(Integer.toString(numero) + "%");
                                    pBtasca1.setProgress(numeroProgres);
                                });
                            }

                            Platform.runLater(() -> {
                                if (isRunning) {
                                    bTasca1.setText("Iniciar");
                                } else {
                                    isRunning = true; // Restablece la bandera para futuras ejecuciones
                                }
                            });
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };

                Thread thread = new Thread(task);
                thread.setDaemon(true); // Establecer como daemon para que se cierre al cerrar la aplicación
                thread.start();
                break;
            case "Atura":
                bTasca1.setText("Iniciar");
                isRunning = false; // Establece la bandera para detener la tarea
                break;
        }
    }
}
