import java.util.Random;

public class QuickSortComparacion {

    public static void main(String[] args) {
        int[] tamanios = {1000, 5000, 10000, 20000};

        for (int n : tamanios) {
            System.out.println("Tamaño: " + n);

            int[] aleatorio = generarAleatorio(n);
            int[] ordenado = generarOrdenado(n);
            int[] inverso = generarInverso(n);

            medir("Original-Aleatorio", aleatorio.clone(), true);
            medir("Original-Ordenado", ordenado.clone(), true);
            medir("Original-Inverso", inverso.clone(), true);

            medir("Medio-Aleatorio", aleatorio.clone(), false);
            medir("Medio-Ordenado", ordenado.clone(), false);
            medir("Medio-Inverso", inverso.clone(), false);

            System.out.println("-----------------------------------");
        }
    }

    // ========== QUICK SORT ORIGINAL ==========
    public static void quickSortOriginal(int[] arr, int low, int high) {
        while (low < high) {
            int pi = particionOriginal(arr, low, high);

            // Procesar primero el subarreglo más pequeño
            if (pi - low < high - pi) {
                quickSortOriginal(arr, low, pi - 1);
                low = pi + 1;
            } else {
                quickSortOriginal(arr, pi + 1, high);
                high = pi - 1;
            }
        }
    }

    private static int particionOriginal(int[] arr, int low, int high) {
        int pivote = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivote) {
                i++;
                intercambiar(arr, i, j);
            }
        }
        intercambiar(arr, i + 1, high);
        return i + 1;
    }

    // ========== QUICK SORT PIVOTE MEDIO ==========
    public static void quickSortMedio(int[] arr, int low, int high) {
        if (low < high) {
            int pi = particionMedio(arr, low, high);
            quickSortMedio(arr, low, pi - 1);
            quickSortMedio(arr, pi + 1, high);
        }
    }

    private static int particionMedio(int[] arr, int low, int high) {
        int medio = (low + high) / 2;
        intercambiar(arr, medio, high);

        int pivote = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivote) {
                i++;
                intercambiar(arr, i, j);
            }
        }
        intercambiar(arr, i + 1, high);
        return i + 1;
    }

    // ========== MEDICIÓN ==========
    private static void medir(String tipo, int[] arr, boolean original) {
        long inicio = System.nanoTime();

        if (original)
            quickSortOriginal(arr, 0, arr.length - 1);
        else
            quickSortMedio(arr, 0, arr.length - 1);

        long fin = System.nanoTime();
        System.out.println(tipo + ": " + (fin - inicio) + " ns");
    }

    // ========== GENERADORES ==========
    private static int[] generarAleatorio(int n) {
        Random r = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = r.nextInt(100000);
        return arr;
    }

    private static int[] generarOrdenado(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        return arr;
    }

    private static int[] generarInverso(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = n - i;
        return arr;
    }

    private static void intercambiar(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}