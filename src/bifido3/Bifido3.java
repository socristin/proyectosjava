package bifido3;

import java.util.Scanner;   /*Se importa la clase scanner*/
import java.util.InputMismatchException; /*se importa la libreria para usar la excepcion del catch*/

public class Bifido3 {
/*VARIABLES*/
    public static int x = 0;
    public static char[][] matriz = new char[6][6];
    public static char[] mensaje;
    public static String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String men = "", cad = "";
    public static Scanner leer = new Scanner(System.in, "ISO-8859-1");
    public static String palabra = "";

    public static void main(String[] args) {
        matriz[1][1] = 'A';
        matriz[1][2] = 'B';
        matriz[1][3] = 'C';
        matriz[1][4] = 'D';
        matriz[1][5] = 'E';
        matriz[2][1] = 'F';
        matriz[2][2] = 'G';  /*Cada uno representa las coordenadas de cada carácter*/
        matriz[2][3] = 'H';
        matriz[2][4] = 'I';
        matriz[2][5] = 'K';
        matriz[3][1] = 'L';
        matriz[3][2] = 'M';
        matriz[3][3] = 'N';
        matriz[3][4] = 'O';
        matriz[3][5] = 'P';
        matriz[4][1] = 'Q';
        matriz[4][2] = 'R';
        matriz[4][3] = 'S';
        matriz[4][4] = 'T';
        matriz[4][5] = 'U';
        matriz[5][1] = 'V';
        matriz[5][2] = 'W';
        matriz[5][3] = 'X';
        matriz[5][4] = 'Y';
        matriz[5][5] = 'Z';
        menu();  /*Aquí se va a declarar el metodo menu*/
    }

    public static void menu() { /*se crea el metodo menu*/

        int opc = 0;  

        do {                                            
            System.out.println("********MENU*******");/*se muestra el mensaje de menu*/
            System.out.println("1. ENCRIPTAR\n" /*se muestra mensaje de encriptar*/
                    + "2. DESENCRIPTAR\n" /*mensaje desencriptar*/
                    + "3. SALIR"); /*mensaje salir*/
            try {    /*se crea un try para validar que se cumpla todo lo del while*/
                opc = leer.nextInt(); /*se lee la opcion*/

                switch (opc) { /*se abre un switch para hacer las opciones o casos*/
                    case 1:
                        System.out.println("INGRESA EL MENSAJE"); 
                        //String saltoDeLinea = leer.nextLine();
                        // men = leer.nextLine();
                        do {
                            palabra = leer.nextLine();  /*Se lee la palabra*/
                            if (!palabra.equalsIgnoreCase("FIN")) { /*si la palabra es diferente de fin*/
                                men = men + palabra + "\n"; 
                            }                                     
                        } while (!palabra.equalsIgnoreCase("FIN")); /*Se abre un while para saber si la palabra es diferente de fin si lo es se encripta el mensaje*/

                        System.out.println("MENSAJE ENCRIPTADO:\n" + Encriptar(men)); /*se muestra un mensaje que indica "mensaje encriptado" */
                        break; /*se cierra el case 1*/
                    case 2:
                        System.out.println("INGRESA EL MENSAJE"); 
                        //saltoDeLinea = leer.nextLine();
                        //cad = leer.nextLine();
                        do {  /*se abre ciclo para */
                            palabra = leer.nextLine();
                            if (!palabra.equalsIgnoreCase("FIN")) { /*condicion para saber que la palabra sea diferente de fin*/
                                cad = cad + palabra + "\n"; /*la cadena es igual a cadena mas la palabra*/
                            }

                        } while (!palabra.equalsIgnoreCase("FIN")); /*condicion que indica que la palabra sea diferente de fin*/
                        System.out.println("MENSAJE DESENCRIPTADO:\n" + Desencriptar(cad));/*se muestra un mensaje que indica "mensaje desencriptado"*/
                        break; /*se cierra el case 2*/

                    case 3:
                    default:
                        System.out.println("******FIN*******"); /*muestra un mensaje de fin*/
                }
            } catch (InputMismatchException e1) {
                System.out.println("ERROR, NO INTRODUCISTE UN NÚMERO ENTERO");/*muestra mensaje de error al no introducir un numero entero*/
                opc = 3; /*la opcion debe ser igual a 3*/
            }
        } while (opc != 3);/*condicion que indica que si la opcion que se introduce es diferente o igual a 3 sale del ciclo*/
    }
      /*METODO ENCRIPTAR*/
    public static String Encriptar(String t) { /*se crea el metodo encriptar*/
        /*VARIABLES*/
        String mens = t;
        String x = "";
        t = limpiar(t);
        String nt = "";
        String enc = "";

        int fila; /*variable qu eindica la fila de una matriz*/
        int col; /*variable que indica la columna de una matriz*/
        for (int i = 0; i < t.length(); i++) { /*se abre un for para obtener las posiciones de cada caracter */
            nt = nt + getposicion(t.charAt(i));
        }
        for (int i = 0; i < nt.length() / 2; i++) { /*se abre un for para revisiar filas y columnas de la matriz*/
            fila = Integer.parseInt(String.valueOf(nt.charAt(i)));
            col = Integer.parseInt(String.valueOf(nt.charAt(i + nt.length() / 2)));
            enc = enc + String.valueOf(matriz[fila][col]); /*variable donde se guardan las coordenadas*/
        }
        enc = construir(enc); /*une todas las coordenadas de lols caracteres*/

        for (int i = 0; i < enc.length(); i++) { /*se abre un for que revisa si existe una mayuscula o miniscula*/
            char c = enc.charAt(i);
            char s = mens.charAt(i);
            if (Character.isUpperCase(s)) { 
                x = x + c;
            } else {
                c = Character.toLowerCase(c);
                x = x + c;
            }
        }
        return x;
    }

    public static String Desencriptar(String t) {// -se3 crea el mpetodo desencriptar
        //variables
        String mens = t;
        String x = "";
        t = limpiar(t);
        String nt = "";
        String tmp1 = "";
        String tmp2 = "";
        boolean b = true;
        for (int i = 0; i < t.length(); i++) {//Se crea un for que revisa la cadena y la posicion de cada caracter
            nt = nt + getposicion(t.charAt(i));
        }

        for (int i = 0; i < nt.length(); i++) {//For que divide las cordenadas para reagrupparlas
            if (b) {
                tmp1 = tmp1 + nt.charAt(i);
                b = false;
            } else {
                tmp2 = tmp2 + nt.charAt(i);
                b = true;
            }
        }
        nt = tmp1 + tmp2;//Cadena de coordenadas reagrupadas 
        tmp1 = "";
        int fila;
        int col;
        for (int i = 0; i < nt.length(); i += 2) {//For que revisa filas y columnas para obtener nuvas coordenadas 
            fila = Integer.parseInt(String.valueOf(nt.charAt(i)));
            col = Integer.parseInt(String.valueOf(nt.charAt(i + 1)));
            tmp1 = tmp1 + String.valueOf(matriz[fila][col]);//Variable que almacenara el conjunto de coordenasdas nuevas
        }
        tmp1 = construir(tmp1);//Mètodo que construye el mensaje 
        for (int i = 0; i < tmp1.length(); i++) {//For que revisa si hay catacteres mayusculas o minusculas 
            char c = tmp1.charAt(i);
            char s = mens.charAt(i);
            if (Character.isUpperCase(s)) {

                x = x + c;

            } else {
                c = Character.toLowerCase(c);
                x = x + c;
            }
        }
//        if (Encriptar(men) == Desencriptar(men)) {
//            return men;
//        }else{
//            Desencriptar(men);
//        }
        return x;
    }

    public static String getposicion(char c) {//Metodo para tener la j en la misma posicion que i en la matriz 
        String p = null;
        if (c == 'J') {//Si el caracter es J
            p = "24";//La coordenadas sera 24
        } else { //Si no
            for (int i = 0; i < 6; i++) {//Abrimos un for que revisa la coordenada del caracter 
                for (int j = 0; j < 6; j++) {
                    if (c == matriz[i][j]) {//Si c existe en la matriz
                        p = Integer.toString(i) + Integer.toString(j);
                        break;
                    }
                }
            }
        }
        return p;//Regresa caracter
    }

    public static String limpiar(String t) {
        t = t.toUpperCase();
        mensaje = t.toCharArray();
        String tmp1 = "";
        for (int i = 0; i < mensaje.length; i++) {
            x = abc.indexOf(mensaje[i]);

            if (x != -1) {//es una letra valida
                tmp1 = tmp1 + mensaje[i];

            }
        }
        return tmp1;
    }

    public static String construir(String t) {//Se escribe el metodo para convertir las coordenadas a texto
        String tt = "";
        int count = 0;
        for (int i = 0; i < mensaje.length; i++) {//Revisa si el texto existen en abc = abecedario y lo concatena
            x = abc.indexOf(mensaje[i]);
            if (x != -1) {

                tt = tt + t.charAt(count);

                count++;
            } else {
                tt = tt + mensaje[i];
            }

        }
        return tt;
    }

}
