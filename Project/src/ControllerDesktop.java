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
    Label labelTasca1,labelTasca2,labelTasca3;

    @FXML
    Button bTasca1,bTasca2,bTasca3;

    @FXML
    ProgressBar pBtasca1,pBtasca2,pBtasca3;

    NumeroProgresBar obj = new NumeroProgresBar();
    
    private volatile boolean isRunning = true; // Bandera para controlar el ciclo del hilo
    private volatile boolean isRunning2 = true;
    private volatile boolean isRunning3 = true;

    public void startTaca1() {
        switch (bTasca1.getText()) {
            case "Iniciar":
                Future<List<Integer>> f1 = obj.calculaate();
                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() {
                        try {
                            Platform.runLater(() -> {
                                bTasca1.setText("Atura");
                            });

                            List<Integer> numeros = f1.get(); // Obtiene la lista de números

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

    public void startTaca2() {
        switch (bTasca2.getText()) {
            case "Iniciar":
                Future<List<Integer>> f2 = obj.calculaate(2,4);
                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() {
                        try {
                            Platform.runLater(() -> {
                                bTasca2.setText("Atura");
                            });

                            List<Integer> numeros = f2.get(); // Obtiene la lista de números

                            for (Integer numero : numeros) {
                                if (!isRunning2) {
                                    break; // Si la bandera indica detenerse, sal del ciclo
                                }
                                int tiempoEspera = (int) ((Math.random() * 3 + 3) * 1000);
                                Thread.sleep(tiempoEspera);
                                Double numeroProgres = (double) (numero / 100.0);
                                Platform.runLater(() -> {
                                    labelTasca2.setText(Integer.toString(numero) + "%");
                                    pBtasca2.setProgress(numeroProgres);
                                });
                            }

                            Platform.runLater(() -> {
                                if (isRunning2) {
                                    bTasca2.setText("Iniciar");
                                } else {
                                    isRunning2 = true; // Restablece la bandera para futuras ejecuciones
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
                bTasca2.setText("Iniciar");
                isRunning2 = false; // Establece la bandera para detener la tarea
                break;
        }
    }

    public void startTaca3() {
        switch (bTasca3.getText()) {
            case "Iniciar":
                Future<List<Integer>> f3 = obj.calculaate(4,6);
                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() {
                        try {
                            Platform.runLater(() -> {
                                bTasca3.setText("Atura");
                            });

                            List<Integer> numeros = f3.get(); // Obtiene la lista de números

                            for (Integer numero : numeros) {
                                if (!isRunning3) {
                                    break; // Si la bandera indica detenerse, sal del ciclo
                                }
                                int tiempoEspera = (int) ((Math.random() * 6 + 3) * 1000);
                                Thread.sleep(tiempoEspera);
                                Double numeroProgres = (double) (numero / 100.0);
                                Platform.runLater(() -> {
                                    labelTasca3.setText(Integer.toString(numero) + "%");
                                    pBtasca3.setProgress(numeroProgres);
                                });
                            }

                            Platform.runLater(() -> {
                                if (isRunning3) {
                                    bTasca3.setText("Iniciar");
                                } else {
                                    isRunning3 = true; // Restablece la bandera para futuras ejecuciones
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
                bTasca3.setText("Iniciar");
                isRunning3 = false; // Establece la bandera para detener la tarea
                break;
        }
    }

    public void stopExecutor() {
        obj.shutdown();
    }
}
