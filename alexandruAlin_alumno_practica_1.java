import java.util.Random;
import java.util.Scanner;

public class App {

    // LAS PRIMERAS 107 LINEAS SON DE METODOS PARA HACER BONITO

    public static void print_mensaje_correcto(int[] xxx) {
        for (int i = 0; i < xxx.length; i++) {
            int w = 0;
            int h = 0;
            for (int k = 0; k < xxx.length; k++) {
                if (i == 0 || Math.pow(2, k) == i) {
                    System.out.print(ANSI_GREEN + xxx[i] + ANSI_RESET + " ");
                    w++;
                    h++;
                    break;
                }
            }
            if (w == 0) {
                System.out.print(xxx[i] + " ");
                h++;
            }
        }
        System.out.println("");
    }
    // metodo que printea con color verde los bits de paridad

    public static void print_error(int[] mensaje, int ubicacion_error) {
        for (int i = 0; i < mensaje.length; i++) {
            int w = 0;
            int h = 0;
            for (int k = 0; k < mensaje.length; k++) {
                if (i == 0 || Math.pow(2, k) == i) {
                    if (i == ubicacion_error) {
                        System.out.print(ANSI_RED + mensaje[i] + ANSI_RESET + " ");
                        w++;
                        h++;
                        break;
                    } else {
                        System.out.print(ANSI_GREEN + mensaje[i] + ANSI_RESET + " ");
                        w++;
                        h++;
                        break;
                    }
                }
            }
            if (w == 0)
                if (i == ubicacion_error) {
                    System.out.print(ANSI_RED + mensaje[i] + ANSI_RESET + " ");
                    h++;
                } else {
                    System.out.print(mensaje[i] + " ");
                    h++;
                }
        }
        System.out.println("");
    }
    // metodo que printea con color rojo los errores y verde los de paridad

    public static void print_mensaje(int[] mensaje) {

        for (int i = 0; i < mensaje.length; i++) {
            System.out.print(mensaje[i] + " ");
        }
        System.out.println("");
    }
    // metodo para imprimir el array

    public static void loading() {
        System.out.print("Creando el código con Hamming---------");
        for (int i = 0; i <= 100; i++) {
            if (i < 10) {
                System.out.print(i + "%");
                System.out.print("\b\b");
            } else if (i >= 10 && i <= 99) {
                System.out.print(i + "%");
                System.out.print("\b\b\b");
            }
            if (i == 100) {
                System.out.println(i + "%");
            }
            try {
                Thread.sleep(15);
            } catch (Exception e) {
            }
        }

    }
    // metodo que muestra un proceso de carga

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    // metodo para limpiar la pantalla
    
    public static final String ANSI_GREEN = "\u001B[32m";
    // verde

    public static final String ANSI_RED = "\u001B[31m";
    // rojo

    public static final String ANSI_RESET = "\u001B[0m";
    // reset

    // A PARTIR DE AQUI EMPIEZA EL CÓDIGO ÚTIL

    public static int bits_paridad(int[] arr) {

        int a = 1;

        String paridad = Integer.toBinaryString(arr.length);

        a = a + paridad.length();

        int total_array = arr.length + a;

        paridad = Integer.toBinaryString(total_array);

        int bits_paridad = paridad.length() + 1;

        return bits_paridad;

    }
    // metodo que saca la cantidad de paridad

    public static int[] rellenar_paridad(int duracion, int[] arr) {
        int[] mensajes = new int[duracion];
        int a = 0;
        int[] mensajes_opt = new int[mensajes.length - 1];
        for (int i = 0; i < mensajes.length; i++) {
            for (int k = 0; k < mensajes.length; k++) {
                if (i == 0) {
                    mensajes[i] = 9;
                    break;
                }
                if (Math.pow(2, k) == i) {
                    mensajes[i] = 9;
                    break;
                }
            } // for k
        } // for i

        for (int i = 0; i < mensajes.length; i++) {
            if (mensajes[i] == 0) {
                mensajes[i] = arr[a];
                if (a != arr.length) {
                    a++;
                }
            }
        }

        if (mensajes[mensajes.length - 1] == 9) {
            for (int i = 0; i < mensajes.length - 1; i++) {
                mensajes_opt[i] = mensajes[i];
            }
            return mensajes_opt;
        }

        return mensajes;
    }
    // metodo rellenar bits paridad

    public static boolean bit_global(int mensaje[]) {
        int x = 0;
        for (int i = 1; i < mensaje.length; i++) {
            x = x + mensaje[i];
        }
        if (x % 2 == 0) {
            return true;
        } else {
            return false;
        }

    }
    // metodo calcular bit global

    public static int[] paridad(int mensaje[], int[] arr) {
        int[] binarios = new int[mensaje.length];
        int a = 0;
        int posicion = 0;

        for (int i = 0; i < mensaje.length; i++) {
            a = 0;
            for (int k = 0; k < mensaje.length; k++) {
                if (mensaje[i] == 9) {
                    int suma = 0;
                    String abinario = Integer.toBinaryString(i);// convierte el número potencia de 2 en binario
                    String x = "";
                    for (int y = abinario.length() - 1; y >= 0; y--) {
                        x += abinario.charAt(y); // se añade el caracter a la nueva string
                    } // for que recorre todos los caracteres de la string

                    for (int m = 0; m < x.length(); m++) {
                        if (x.charAt(m) == '1') {
                            posicion = m;
                        } // if es 1 se guarda la posicion
                    } // recorre el numero potencia de 2 en binario para guardar en qué posicion está
                      // el 1
                    for (int m = i; m < mensaje.length; m++) {
                        String y = ""; // convierte el número del array en binario
                        String z = Integer.toBinaryString(m);
                        for (int q = z.length() - 1; q >= 0; q--) {
                            y += z.charAt(q); // se añade el caracter a la nueva string
                        } // for que recorre todos los caracteres de la string
                        if (m == i) {
                            continue;
                        } // if es el mismo número
                        else {
                            for (int j = 0; j < y.length(); j++) {
                                if (y.charAt(j) == '1' && j == posicion) {

                                    binarios[a] = mensaje[m];
                                    a++;

                                } // if es un uno y es la misma posicion se guarda la posicion del número
                            } // recorre el binario
                        } // if no es el mismo número

                    } // recorre todos los números
                    for (int m = 0; m < mensaje.length; m++) {
                        suma = suma + binarios[m];

                    } // recorre todos los números con la misma posición para sumarlos
                    if (suma % 2 == 0) {
                        mensaje[i] = 0;
                    } else {
                        mensaje[i] = 1;
                    }
                    binarios = new int[mensaje.length];
                    break;
                }
            }
        }
        for (int i = 0; i < mensaje.length; i++) {
            int h = 0;
            for (int k = 0; k < mensaje.length; k++) {
                if (i == 0 || Math.pow(2, k) == i) {
                    h++;
                }
            }
            if (h == 0) {
                mensaje[i] = arr[a];
                if (a != arr.length) {
                    a++;
                }

            }
        }

        return mensaje;
    }
    // metodo para sacar la paridad de los bits

    public static int[] sender(int[] mensaje, int[] arr, int total_array) {
        int[] x = paridad(mensaje, arr);
        if (bit_global(x)) {
            x[0] = 0;
        } else {
            x[0] = 1;
        }
        return x;
    }

    public static int errores_noise() {
        Random rnd = new Random();

        int cantidad = 1;
        return cantidad;
    }
    // metodo para hacer la cantidad de errores

    public static int[] noise(int[] arr, int cantidad, int []ubicacion) {

        if (cantidad == 0) {
            return arr;
        }
        if (cantidad == 1) {
            if (arr[ubicacion[0]] == 1) {
                arr[ubicacion[0]] = 0;
            } else {
                arr[ubicacion[0]] = 1;
            }
        }
        if (cantidad == 2) {
            for (int i = 0; i < 2; i++) {
                if (arr[ubicacion[i]] == 1) {
                    arr[ubicacion[i]] = 0;
                } 
                else {
                    arr[ubicacion[i]] = 1;
                }
            }
        }

        return arr;
    }
    // metodo para hacer alteraciones en el codigo

    public static int[] paridad_noise(int[] mensaje) {

        int[] mensaje2 = mensaje;
        if(bit_global(mensaje2)){
            mensaje2[0] = 0;
        }
        else{
            mensaje2[0] = 1;
        }
        int[] binarios = new int[mensaje.length];
        int a = 0;
        int b = 0;
        int posicion = 0;

        for (int i = 0; i < mensaje2.length; i++) {
            a = 0;
            for (int k = 0; k < mensaje2.length; k++) {
                if (Math.pow(2, k) == i) {
                    int suma = 0;
                    String abinario = Integer.toBinaryString(i);// convierte el número potencia de 2 en binario
                    String x = "";
                    for (int y = abinario.length() - 1; y >= 0; y--) {
                        x += abinario.charAt(y); // se añade el caracter a la nueva string
                    } // for que recorre todos los caracteres de la string

                    for (int m = 0; m < x.length(); m++) {
                        if (x.charAt(m) == '1') {
                            posicion = m;
                        } // if es 1 se guarda la posicion
                    } // recorre el numero potencia de 2 en binario para guardar en qué posicion está
                      // el 1
                    for (int m = i; m < mensaje2.length; m++) {
                        String y = ""; // convierte el número del array en binario
                        String z = Integer.toBinaryString(m);
                        for (int q = z.length() - 1; q >= 0; q--) {
                            y += z.charAt(q); // se añade el caracter a la nueva string
                        } // for que recorre todos los caracteres de la string
                        if (m == i) {
                            continue;
                        } // if es el mismo número
                        else {
                            for (int j = 0; j < y.length(); j++) {
                                if (y.charAt(j) == '1' && j == posicion) {

                                    binarios[a] = mensaje2[m];
                                    a++;

                                } // if es un uno y es la misma posicion se guarda la posicion del número
                            } // recorre el binario
                        } // if no es el mismo número

                    } // recorre todos los números
                    for (int m = 0; m < mensaje2.length; m++) {
                        suma = suma + binarios[m];

                    } // recorre todos los números con la misma posición para sumarlos
                    if (suma % 2 == 0) {
                        mensaje2[i] = 0;
                    } else {
                        mensaje2[i] = 1;
                    }
                    binarios = new int[mensaje2.length];
                    break;
                }
            }
        }
        a = 0;

        return mensaje2;

    }

    public static int receiver(int[] mensaje, int[] mensaje_sin_error) {

        int[] error_loca = new int[mensaje.length];
        int a = 0;
        for (int i = 0; i < mensaje.length; i++) {
            for (int k = 0; k < mensaje.length; k++) {
                if (Math.pow(2, k) == i) {
                    if (mensaje[i] != mensaje_sin_error[i]) {
                        error_loca[a] = i;
                        a++;
                    }
                }
            }
        }
        a = 0;
        for (int i = 0; i < error_loca.length; i++) {
            a = error_loca[i] + a;
        }

        if (a == 0){
            if (mensaje[0] != mensaje_sin_error[0]){
                a = -1;
            }
        }
        else{
            if (mensaje[0] == mensaje_sin_error[0]){
                a = -2;
            }
        }

        return a;
    }
    // metodo para saber donde está el error

    public static void main(String[] args) throws InterruptedException {
        clearScreen();
        Scanner read = new Scanner(System.in);
        Random rnd = new Random();
        System.out.print("Introduce la duración del mensaje: ");
        int[] arr = new int[read.nextInt()];
        for (int i = 0; i < arr.length; i++) {
            int binario = rnd.nextInt(2);
            arr[i] = binario;
        } // for rellenar el array

        int total_array = arr.length + bits_paridad(arr);
        int cantidad = rnd.nextInt(3);
        int[] ubi = new int[total_array];
        if (cantidad == 1){
            ubi[0] = rnd.nextInt(total_array-1);
        }
        if (cantidad == 2){
            ubi[0] = rnd.nextInt(total_array-1);
            ubi[1] = rnd.nextInt(total_array-1);
        }
        int[] a = noise(sender(rellenar_paridad(total_array, arr), arr, total_array), cantidad, ubi);
        int error =receiver(
                paridad_noise(noise(sender(rellenar_paridad(total_array, arr), arr, total_array), cantidad, ubi)),
                noise(sender(rellenar_paridad(total_array, arr), arr, total_array), cantidad, ubi));

        System.out.println("Datos del mensaje: ");
        print_mensaje(arr);
        Thread.sleep(2000);
        loading();
        System.out.println("Código con hamming del sender: ");
        print_mensaje_correcto(sender(rellenar_paridad(total_array, arr), arr, total_array));
        System.out.println("Código recibido:");
        if(error == -2){
            print_mensaje_correcto(a);
            System.out.println("Han habido dos errores.");
        }
        else if (error == -1){
            print_error(a, 0);
            System.out.println("Ha habido un error en el bit global.");
        }
        else if (error == 0){
            print_mensaje_correcto(a);
            System.out.println("No ha habido ningún error.");
        }
        else{
            print_error(a, error);
            System.out.println("Ha habido un error en la posición "+error);
        }
    

    } // main
}