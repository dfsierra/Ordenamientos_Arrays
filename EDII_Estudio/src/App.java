import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        // Creacion del array
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite el numero de digitos del vector");
        int n = sc.nextInt();
        System.out.println(n);
        int[] arr;
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Digite el numero " + (i+1) + ":");
            arr[i] = sc.nextInt();
        }

        // Imprimimos
        System.out.println("Antes: ");
        printArray(arr);
        System.out.println("\n");

        //insertionSort(arr);
        //shellSort(arr);
        //selectionSort(arr);
        //heapSort(arr);
        //bubbleSort(arr);
        // pivote al inicio
        //quickSortizq(arr, 0, arr.length-1);
        //quickSortByStackizq(arr);
        quickSortder(arr, 0, arr.length-1);
        //arr = mergingSort(arr);
        //radixSort(arr);
        System.out.println("\n");
        System.out.println("Despues: ");
        printArray(arr);
        



        /*System.out.println("Dato a buscar: ");
        int datab = sc.nextInt();

        System.out.println("1. Secuencial y 2. Binaria");
        int busquedad = sc.nextInt();

        switch (busquedad){
            case 1:
                //Busquedad secuencial
                int sec = busquedaSecuencial(arr, datab);
                if (sec!=-1) {
                    System.out.println(sec);
                } else {
                    System.out.println("No esta el dato");
                }
            break;

            case 2:
                //Busquedad binaria, debe estar ordenado
                binarySearch(arr, datab);
            break;

        }
        */
    }

    static void printArray(int arr[]) {
        int N = arr.length;

        for (int i = 0; i < N; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    static int busquedaSecuencial(int []arreglo,int dato){
        int posicion = -1;
         for(int i = 0; i < arreglo.length; i++){ //recorremos todo el arreglo
             if(arreglo[i] == dato){ //comparamos el elemento en el arreglo con el buscado
                posicion = i; //Si es verdadero guardamos la posicion
           break; //Para el ciclo
          }
        }
        return posicion;
    }
    static void binarySearch(int v[], int To_Find){
        int lo = 0, hi = v.length - 1;
        // This below check covers all cases , so need to check
        // for mid=lo-(hi-lo)/2
        while (hi - lo > 1) {
            int mid = (hi + lo) / 2;
            if (v[mid] < To_Find) {
                lo = mid + 1;
            }
            else {
                hi = mid;
            }
        }
        if (v[lo] == To_Find) {
            System.out.println("Found At Index " + lo );
        }
        else if (v[hi] == To_Find) {
            System.out.println("Found At Index " + hi );
        }
        else {
            System.out.println("Not Found" );
        }
    }
    /**
     * Ordenamiento por inserción
     *
     * 1. A partir del primer elemento, el elemento puede considerarse ordenado
     * 2. Saque el siguiente elemento y escanee de atrás hacia adelante en la secuencia de elementos ordenados
     * 3. Si el elemento (ordenado) es más grande que el nuevo elemento, mueva el elemento a la siguiente posición
     * 4. Repita el paso 3 hasta que encuentre la posición donde el elemento ordenado es menor o igual que el nuevo elemento
     * 5. Después de insertar el nuevo elemento en esa posición
     * 6. Repita los pasos 2~5
     * Matriz @param arr a ordenar
     */
    public static void insertionSort(int[] arr){
        for( int i=0; i<arr.length-1; i++ ) {
            for( int j=i+1; j>0; j-- ) {
                if( arr[j-1] <= arr[j] )
                    break;
                int temp = arr[j];      //operación de cambio
                arr[j] = arr[j-1];
                arr[j-1] = temp;
                System.out.println("Sorting:  " + Arrays.toString(arr));
            }
        }
    }

    /**
     * Ordenamiento de burbuja
     *
     * 1. Comparar elementos adyacentes. Si el primero es más grande que el segundo, cámbialos a ambos.
     * 2. Haga lo mismo para cada par de elementos adyacentes, desde el primer par al principio hasta el último par al final. Después de realizar este paso, el último elemento será el número más grande.
     * 3. Repita los pasos anteriores para todos los elementos excepto el último.
     * 4. Continúe repitiendo los pasos anteriores ①~③ para cada vez menos elementos, hasta que no haya un par de números para comparar.
     * Matriz @param arr a ordenar
     */
    public static void bubbleSort(int[] arr){
        for (int i = arr.length - 1; i > 0; i--) {      //El bucle exterior mueve el cursor.
            for(int j = 0; j < i; j++){    //El bucle interior atraviesa el cursor y los elementos después (o antes)
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    System.out.println("Sorting: " + Arrays.toString(arr));
                }
            }
        }
    }

    /**
     * Ordenamiento de selección
     *
     * 1. De la secuencia a ordenar, busque el elemento con la clave más pequeña;
     * 2. Si el elemento más pequeño no es el primer elemento de la secuencia que se ordenará, cámbielo por el primer elemento;
     * 3. De los N - 1 elementos restantes, busque el elemento con la palabra clave más pequeña y repita los pasos ① y ② hasta que finalice la clasificación.
     * Solo cuando el factor de incremento es 1, la secuencia completa se trata como una tabla y la longitud de la tabla es la longitud de la secuencia completa.
     * Matriz @param arr a ordenar
     */
    public static void selectionSort(int[] arr){
        for(int i = 0; i < arr.length-1; i++){
            int min = i;
            for(int j = i+1; j < arr.length; j++){    //Después de la selección, se ordenará la posición con el valor mediano más pequeño
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            if(min != i){
                int temp = arr[min];      //operación de cambio
                arr[min] = arr[i];
                arr[i] = temp;
                System.out.println("Sorting:  " + Arrays.toString(arr));
            }
        }
    }

    static int shellSort(int[] arr)
    {
        int n = arr.length;
 
        // Start with a big gap, then reduce the gap
        for (int gap = n/2; gap > 0; gap /= 2)
        {
            // Do a gapped insertion sort for this gap size.
            // The first gap elements a[0..gap-1] are already
            // in gapped order keep adding one more element
            // until the entire array is gap sorted
            for (int i = gap; i < n; i += 1)
            {
                // add a[i] to the elements that have been gap
                // sorted save a[i] in temp and make a hole at
                // position i
                int temp = arr[i];
 
                // shift earlier gap-sorted elements up until
                // the correct location for a[i] is found
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];
 
                // put temp (the original a[i]) in its correct
                // location
                arr[j] = temp;
                System.out.println("gap: " + gap);
                System.out.println("    Sorting:  " + Arrays.toString(arr));
            }
        }
        return 0;
    }

    /**
     * Clasificación Radix (LSD comienza desde el orden más bajo)
     *
     * La clasificación Radix es adecuada para:
     * (1) El rango de datos es pequeño, se recomienda que sea inferior a 1000
     * (2) Cada valor debe ser mayor o igual a 0
     *
     * ① Obtenga el número máximo en la matriz y obtenga el número de dígitos;
     * ②.arr es la matriz original, y cada bit se toma del bit más bajo para formar una matriz radix;
     * ③ Cuente y clasifique la raíz (usando la función de contar y clasificar para números de rango pequeño);
     * Matriz @param arr a ordenar
     */
    public static void radixSort(int[] arr){
        if(arr.length <= 1) return;

        //Obtenga el número más grande en la matriz y obtenga la cantidad de dígitos
        int max = 0;
        for(int i = 0; i < arr.length; i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        int maxDigit = 1;
        while(max / 10 > 0){
            maxDigit++;
            max = max / 10;
        }
        System.out.println("maxDigit: " + maxDigit);

        //Solicitar un espacio de cubo
        int[][] buckets = new int[10][arr.length];
        int base = 10;

        //De menor a mayor, recorra cada bit y asigne todos los elementos a los cubos
        for(int i = 0; i < maxDigit; i++){
            int[] bktLen = new int[10];        //Almacene la cantidad de elementos de almacenamiento en cada cubo

            //Asignación: asigna todos los elementos en cubos
            for(int j = 0; j < arr.length; j++){
                int whichBucket = (arr[j] % base) / (base / 10);
                buckets[whichBucket][bktLen[whichBucket]] = arr[j];
                bktLen[whichBucket]++;
            }

            /*Recopilación: pesque los datos en diferentes cubos uno por uno para prepararse para 
            //la próxima ronda de clasificación de alto nivel. Dado que los elementos cerca del fondo 
            del cubo tienen una clasificación más alta, deben pescarse primero desde el fondo del cubo.
            */
            int k = 0;
            for(int b = 0; b < buckets.length; b++){
                for(int p = 0; p < bktLen[b]; p++){
                    arr[k++] = buckets[b][p];
                }
            }

            System.out.println("Sorting: " + Arrays.toString(arr));
            base *= 10;
        }
    }

    /**
     * Combinar ordenación (recursivo)
     *
     * ① Combina cada dos números adyacentes en la secuencia para formar secuencias de piso (n/2), cada secuencia contiene dos elementos después de la clasificación;
     * ② Combine las secuencias anteriores nuevamente para formar secuencias de piso (n/4), cada una con cuatro elementos;
     * ③ Repita el paso ② hasta que todos los elementos estén ordenados.
     * Matriz @param arr a ordenar
     */

    public static int[] mergingSort(int[] arr){
        if(arr.length <= 1) return arr;

        int num = arr.length >> 1;
        int[] leftArr = Arrays.copyOfRange(arr, 0, num);
        int[] rightArr = Arrays.copyOfRange(arr, num, arr.length);
        System.out.println("split two array: " + Arrays.toString(leftArr) + " And " + Arrays.toString(rightArr));
        return mergeTwoArray(mergingSort(leftArr), mergingSort(rightArr));      //Dividir continuamente en la unidad más pequeña, luego ordenar y fusionar
    }
    private static int[] mergeTwoArray(int[] arr1, int[] arr2){
        int i = 0, j = 0, k = 0;
        int[] result = new int[arr1.length + arr2.length];  //Solicite espacio adicional para almacenar la matriz fusionada
        while(i < arr1.length && j < arr2.length){      //Elija la más pequeña de dos secuencias en una nueva matriz
            if(arr1[i] <= arr2[j]){
                result[k++] = arr1[i++];
            }else{
                result[k++] = arr2[j++];
            }
        }
        while(i < arr1.length){     //Los elementos sobrantes en la secuencia 1 se mueven a la nueva matriz
            result[k++] = arr1[i++];
        }
        while(j < arr2.length){     //Los elementos sobrantes en la secuencia 2 se mueven a la nueva matriz
            result[k++] = arr2[j++];
        }
        System.out.println("Merging: " + Arrays.toString(result));
        return result;
    }

    /**
     * Clasificación de montón
     *
     * 1. Primero construya la secuencia inicial K[1..n] en un montón superior grande, luego el primer elemento K1 es el más grande en este momento, y este montón es el área desordenada inicial.
     * 2. Luego intercambie el registro K1 con la palabra clave más grande (es decir, la parte superior del montón, el primer elemento) con el último registro Kn en el área desordenada, obteniendo así una nueva área desordenada K[1..n−1 ] y el área ordenada K[n], y satisfacen K[1..n−1].keys⩽K[n].key
     * 3. Después de intercambiar K1 y Kn, la parte superior del montón puede violar la propiedad del montón, por lo que K[1..n−1] debe ajustarse a un montón. Luego repita el paso ② hasta que solo haya un elemento en el montón. área desordenada.
     * Matriz @param arr a ordenar
     */
    /*public static void heapSort(int[] arr){
        for(int i = arr.length; i > 0; i--){
            max_heapify(arr, i);

            int temp = arr[0];      //El elemento superior (el primer elemento) se intercambia con Kn
            arr[0] = arr[i-1];
            arr[i-1] = temp;
        }
    }
    private static void max_heapify(int[] arr, int limit){
        if(arr.length <= 0 || arr.length < limit) return;
        int parentIdx = limit / 2;

        for(; parentIdx >= 0; parentIdx--){
            if(parentIdx * 2 >= limit){
                continue;
            }
            int left = parentIdx * 2;       //posición del nodo secundario izquierdo
            int right = (left + 1) >= limit ? left : (left + 1);    //La posición del nodo secundario derecho, si no hay un nodo derecho, el valor predeterminado es la posición del nodo izquierdo

            int maxChildId = arr[left] >= arr[right] ? left : right;
            if(arr[maxChildId] > arr[parentIdx]){   //Intercambie el valor máximo del nodo principal y los nodos secundarios izquierdo y derecho
                int temp = arr[parentIdx];
                arr[parentIdx] = arr[maxChildId];
                arr[maxChildId] = temp;
            }
        }
        System.out.println("Max_Heapify: " + Arrays.toString(arr));
    }
    */
    public static void heapSort(int arr[])
    {
        int N = arr.length;
 
        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--)
            heapify(arr, N, i);
 
        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
 
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
 
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    static void heapify(int arr[], int N, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2
 
        // If left child is larger than root
        if (l < N && arr[l] > arr[largest])
            largest = l;
 
        // If right child is larger than largest so far
        if (r < N && arr[r] > arr[largest])
            largest = r;
 
        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
 
            // Recursively heapify the affected sub-tree
            heapify(arr, N, largest);
        }
        System.out.println("Max_Heapify: " + Arrays.toString(arr));
    }
     


    /**
     * Clasificación rápida (recursiva)
     *
     * ① Elija un elemento de la secuencia, llamado "pivote".
     * ② Vuelva a ordenar la secuencia, todos los elementos más pequeños que el valor de referencia se colocan delante del punto de referencia, y todos los elementos más grandes que el valor de referencia se colocan detrás del punto de referencia (el mismo número puede ir a cualquier lado). Después de que termina esta división, el punto de referencia está en el medio de la secuencia. Esto se llama una operación de partición.
     * ③ Ordenar recursivamente (recursivamente) los subconjuntos de elementos más pequeños que el valor de referencia y los subconjuntos de elementos mayores que el valor de referencia.
     * Matriz @param arr a ordenar
     * @param borde inferior izquierdo
     * @param borde derecho alto
     */

    public static void quickSortizq(int[] arr, int low, int high){
        if(arr.length <= 0) return;
        if(low >= high) return;
        int left = low;
        int right = high;

        int temp = arr[left];   //Dig 1: guarde el valor del punto de referencia
        System.out.println("Pivote: " + temp);
        while (left < right){
            while(left < right && arr[right] >= temp){  //Hoyo 2: encuentre un elemento más pequeño que la referencia de atrás hacia adelante e insértelo en la posición de referencia Hoyo 1
                right--;
            }
            arr[left] = arr[right];
            while(left < right && arr[left] <= temp){   //Pozo 3: encuentre elementos más grandes que el punto de referencia de adelante hacia atrás y colóquelos en el pozo 2 que acaba de cavar
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;   //El valor de referencia se llena en el hoyo 3, listo para la clasificación rápida recursiva de divide y vencerás
        System.out.println("Sorting: " + Arrays.toString(arr));
        quickSortizq(arr, low, left-1);
        quickSortizq(arr, left+1, high);
    }

    /**
     * Clasificación rápida (no recursiva)
     *
     * ① Elija un elemento de la secuencia, llamado "pivote".
     * ② Vuelva a ordenar la secuencia, todos los elementos más pequeños que el valor de referencia se colocan delante del punto de referencia, y todos los elementos más grandes que el valor de referencia se colocan detrás del punto de referencia (el mismo número puede ir a cualquier lado). Después de que termina esta división, el punto de referencia está en el medio de la secuencia. Esto se llama una operación de partición.
     * ③ Empuje los límites (bajo y alto) de los dos intervalos después de la partición en la pila para almacenamiento, y recorra los pasos ① y ②
     * Matriz @param arr a ordenar
     */
    public static void quickSortByStackizq(int[] arr){
        if(arr.length <= 0) return;
        Stack<Integer> stack = new Stack<Integer>();

        //Los punteros izquierdo y derecho del estado inicial se colocan en la pila
        stack.push(0);
        stack.push(arr.length - 1);
        while(!stack.isEmpty()){
            int high = stack.pop();     //Pop out para particionar
            int low = stack.pop();

            int pivotIdx = partition(arr, low, high);

            //Guardar variables intermedias
            if(pivotIdx > low) {
                stack.push(low);
                stack.push(pivotIdx - 1);
            }
            if(pivotIdx < high && pivotIdx >= 0){
                stack.push(pivotIdx + 1);
                stack.push(high);
            }
            System.out.println("Sorting: " + Arrays.toString(arr));

        }
    }

    private static int partition(int[] arr, int low, int high){
        if(arr.length <= 0) return -1;
        if(low >= high) return -1;
        int l = low;
        int r = high;

        int pivot = arr[l];    //Dig 1: guarde el valor del punto de referencia
        System.out.println("Pivote: " + pivot);

        while(l < r){
            while(l < r && arr[r] >= pivot){  //Hoyo 2: encuentre un elemento más pequeño que la referencia de atrás hacia adelante e insértelo en la posición de referencia Hoyo 1
                r--;
            }
            arr[l] = arr[r];
            while(l < r && arr[l] <= pivot){   //Pozo 3: encuentre elementos más grandes que el punto de referencia de adelante hacia atrás y colóquelos en el pozo 2 que acaba de cavar
                l++;
            }
            arr[r] = arr[l];
        }
        arr[l] = pivot;   //El valor de referencia se llena en el hoyo 3, listo para la clasificación rápida recursiva de divide y vencerás
        return l;
    }


    // funcion que nos ayuda a intercambiar los elementos en i y en j
    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
  
    /* Funcion que utiliza el ultimo elemento como el pivote
       y lo pone en la posicion correcta dejando a todos los elementos
       menores que el pivote a su izquiera y los elementos mayores
       a la derecha
    */
    static int partition2(int[] arr, int low, int high)
    {
  
        // pivote
        int pivot = arr[high];
        System.out.println("Pivote: " + pivot);
  
        // Puntero que nos va a ayudar a encontrar la posicion
        // correcta del pivote para hacer la particion
        int i = (low - 1);
  
        for (int j = low; j <= high - 1; j++) {
  
            // Si el elemento actual es menor que el pivote
            if (arr[j] < pivot) {
  
                // Aumentamos el numero del puntero i
                i++;
                swap(arr, i, j);
            }
        }
        // intercambiamos la posicion del puntero i por la del pivote
        swap(arr, i + 1, high);
        return (i + 1);
    }

    static void quickSortder(int[] arr, int low, int high)
    {
        if (low < high) {
  
            // particionamos los elementos
            int pi = partition2(arr, low, high);
  
            // Ordenamos los elementos que estan antes y despues
            // del pivote sin incluirlo a el mismo
            System.out.println("Sorting: " + Arrays.toString(arr));
            quickSortder(arr, low, pi - 1);
            quickSortder(arr, pi + 1, high);
        }
    }




}
