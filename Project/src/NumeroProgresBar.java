import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NumeroProgresBar {
    
    private ExecutorService executor = Executors.newFixedThreadPool(3);

    public Future<List<Integer>> calculaate() {
        return executor.submit(() -> {
            List<Integer> numeros = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                numeros.add(i);
            }
            return numeros;
        });
    }

    public Future<List<Integer>> calculaate(int valorInicial, int valorFinal) {
        return executor.submit(() -> {
            List<Integer> numeros = new ArrayList<>();
            int suma = 0;
    
            while (suma < 100) {
                int diferencia = 100 - suma;
    
                if (diferencia <= valorFinal) {
                    // Si la diferencia es menor o igual al rango de valores aleatorios, usa la diferencia
                    numeros.add(suma);
                    suma += diferencia;
                } else {
                    // Genera un número aleatorio dentro del rango de valores aleatorios
                    int randomValue = (int) (Math.random() * (valorFinal - valorInicial + 1) + valorInicial);
                    numeros.add(suma);
                    suma += randomValue;
                }
            }
            numeros.add(suma);
    
            return numeros;
        });
    }

    public void shutdown () { executor.shutdown(); }
}
