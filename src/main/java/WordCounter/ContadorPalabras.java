package WordCounter;

import java.io.*;
import java.sql.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContadorPalabras {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.home") + "/archivo.txt"));
        int contarPalabras = 0;
        int contarCaracteres = 0;
        int contarParrafos = 0;
        int contarOraciones = 0;
        int sumaLongitud = 0;
        String linea = null;
        List<String> listaPalabras = new ArrayList<>();
        while ((linea = br.readLine()) != null) {
            if (linea.equals("")) {
                contarParrafos++;
            } else {
                contarCaracteres += linea.length();
                String palabras[] = linea.split("\\s+");
                contarPalabras += palabras.length;
                for (int i = 0; i < palabras.length; i++) {
                    sumaLongitud += palabras[i].length();
                    listaPalabras.add(palabras[i]);
                }
                String oracion[] = linea.split("[!?.:]+");
                contarOraciones += oracion.length;
            }
        }
        if (contarOraciones >= 1) {
            contarParrafos++;
        }
        float promedio = sumaLongitud / contarPalabras;
        System.out.println("El número de palabras es: " + String.valueOf(contarPalabras));
        System.out.println("La cantidad de párrafos es: " + String.valueOf(contarParrafos));
        System.out.println("La cantidad de oraciones es: " + String.valueOf(contarOraciones));
        System.out.println("La cantidad de caracteres es: " + String.valueOf(contarCaracteres));
        System.out.println("La longitud promedio de las palabras: " + String.valueOf(promedio));
        Map<String, Long> repetidos = new HashMap<>();
        for (String s : listaPalabras) {
            repetidos.merge(s, 1L, Long::sum);
        }

        Map<String, Long>  frecuentes =  new HashMap<>(repetidos);
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Las 10 palabras más frecuentes son: ");
        for (int i = 0; i < 10; i++) {
            Object maximo = Collections.max(frecuentes.entrySet(), Map.Entry.comparingByValue()).getKey();
            System.out.println( (i + 1) + ". " + maximo);
            frecuentes.remove(maximo);
        }
        Map<String, Long>  sinPreposiciones = new HashMap<>(repetidos);
        String preposiciones[] ={"a","ante","bajo","cabe","con","contra","de","desde","en","entre","hacia","hasta","para","por","segun","si","so","sobre","tras"};
        for ( int i =0; i < preposiciones.length; i++)
        {
            sinPreposiciones.remove(preposiciones[i]);
        }

        System.out.println("-----------------------------------------------------------------");
        System.out.println("Las 10 palabras más frecuentes sin considerar preposiciones son: ");
        for (int i = 0; i < 10; i++) {
            Object maximo = Collections.max(sinPreposiciones.entrySet(), Map.Entry.comparingByValue()).getKey();
            System.out.println( (i + 1) + ". " + maximo);
            sinPreposiciones.remove(maximo);
        }
    }
}