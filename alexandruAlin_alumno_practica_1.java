import java.util.Random;
import java.util.Scanner;

public class App {

    public static int bits_paridad(int[] arr) {

        int a = 1;

        String paridad = Integer.toBinaryString(arr.length);

        a = a + paridad.length();

        int total_array = arr.length + a;

        paridad = Integer.toBinaryString(total_array);

        int bits_paridad = paridad.length() + 1;

        return bits_paridad;

    }
    // metodo bits paridad

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
    }
    // metodo para printear el mensaje

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
        int[] mensaje2 = new int[mensaje.length];
        String[][] bits_de_paridad = new String[bits_paridad(arr) - 1][];
        int[] binarios = new int[mensaje.length];
        int a = 0;
        int b = 0;
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
                        mensaje2[i] = 0;
                    } else {
                        mensaje2[i] = 1;
                    }
                    binarios = new int[mensaje.length];
                    break;
                }
            }
        }
        a = 0;
        for (int i = 0; i < mensaje2.length; i++) {
            int h = 0;
            for (int k = 0; k < mensaje2.length; k++) {
                if (i == 0 || Math.pow(2, k) == i) {
                    h++;
                }
            }
            if (h == 0) {
                mensaje2[i] = arr[a];
                if (a != arr.length) {
                    a++;
                }

            }
        }

        return mensaje2;
    }
    // metodo para sacar la paridad de los bits

    public static int[] hamming(int[] mensaje, int[] arr, int total_array) {
        int[] x = paridad(rellenar_paridad(total_array, arr), arr);
        if (bit_global(x)) {
            x[0] = 0;
        } else {
            x[0] = 1;
        }
        return x;
    }

    public static final String ANSI_GREEN = "\u001B[32m";
    // verde

    public static final String ANSI_RED = "\u001B[31m";
    // rojo

    public static final String ANSI_RESET = "\u001B[0m";
    // reset

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);
        Random rnd = new Random();
        System.out.print("Introduce la duración del mensaje: ");
        int[] arr = new int[read.nextInt()];
        for (int i = 0; i < arr.length; i++) {
            int binario = rnd.nextInt(2);
            arr[i] = binario;
        } // for rellenar el array

        int total_array = arr.length + bits_paridad(arr);

        print_mensaje_correcto(hamming(rellenar_paridad(total_array, arr), arr, total_array));

    } // main
}