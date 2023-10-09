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

    public Future<Integer> calculaate(int valorInicial, int valorFinal) {


        return null;
    }

    public void shutdown () { executor.shutdown(); }
}
