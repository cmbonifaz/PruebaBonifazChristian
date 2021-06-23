package test;

import dominio.Estudiante;
import java.util.Scanner;

public class TestEstudiante {

    static Scanner datos = new Scanner(System.in);

    public static void main(String[] args) {
        int nroHombres, ne;
        System.out.println("Cuantos estudiantes desea ingresar?: ");
        int n = datos.nextInt();
        Estudiante[] estudiantes = new Estudiante[n];
        llenaEstudiante(estudiantes);
        System.out.println("Listado de estudiantes ordenados de mayor a menor");
        ordenEst(estudiantes);
        for (int i = 0; i < estudiantes.length; i++) {
            System.out.println(estudiantes[i]);
        }
        nroHombres = porcentajegenero(estudiantes);
        System.out.println("El % de hombres X encima del promedio : " + porcentajePromedio(estudiantes, nroHombres, 'm'));
        System.out.println("El % de mujeres X encima del promedio : " + porcentajePromedio(estudiantes, nroHombres, 'f'));
        ne = notaAlta(estudiantes);
        System.out.println("El estudiante con la nota mas alta es: " + estudiantes[ne].getNombre());
        
    }

    public static void llenaEstudiante(Estudiante est[]) {
        String nombre;
        char sexo;
        double nota;
        for (int i = 0; i < est.length; i++) {
            datos.nextLine();
            System.out.println("Estudiante No " + (i + 1));
            System.out.println("Nombre: ");
            nombre = datos.nextLine();
            System.out.println("Sexo: <<m>> <<f>>");
            sexo = datos.next().charAt(0);
            System.out.println("Nota: ");
            nota = datos.nextDouble();

            est[i] = new Estudiante(nombre, sexo, nota);

        }
    }

    public static int porcentajegenero(Estudiante est[]) {
        int cuentahombres = 0, cuentamujeres = 0;
        for (int i = 0; i < est.length; i++) {
            if (est[i].getSexo() == 'm') {
                cuentahombres++;
            }

        }
        cuentamujeres = est.length - cuentahombres;
        System.out.println("Porcentaje Hombres= " + ((cuentahombres * 100 / est.length)));
        System.out.println("Porcentaje Mujeres= " + ((cuentamujeres * 100 / est.length)));
        return cuentahombres;
    }

    public static double porcentajePromedio(Estudiante est[], int homb, char sex) {
        int muj = est.length - homb;
        int cuenta = 0;
        double promedio = 0, porch, porcm;
        for (int i = 0; i < est.length; i++) {
            promedio += est[i].getNota();
        }
        promedio = promedio / est.length;
        for (int i = 0; i < est.length; i++) {
            if (est[i].getNota() > promedio && est[i].getSexo() == sex) {
                cuenta++;
            }

        }
        porch = homb * 100 / est.length;
        porcm = muj * 100 / est.length;
        if (sex == 'm' && homb > 0) {
            porch = cuenta * porch / homb;
            return porch;
        } else if (muj > 0) {
            porcm = cuenta * porcm / muj;
            return porcm;
        } else {
            return 0;
        }
    }

    public static int notaAlta(Estudiante est[]) {
        double mayor;
        int indice = 0;
        mayor = est[0].getNota();
        for (int i = 1; i < est.length; i++) {
            if (est[i].getNota() > mayor) {
                mayor = est[i].getNota();
                indice = i;
            }
        }
        return indice;

    }

    public static void ordenEst(Estudiante est[]) {
        String nombre;
        char sexo;
        double nota;

        for (int n = 0; n < est.length - 1; n++) {
            for (int j = 0; j < est.length - 1; j++) {
                if (est[j].getNota() < est[j + 1].getNota()) {
                    nombre = est[j + 1].getNombre();
                    sexo = est[j + 1].getSexo();
                    nota = est[j + 1].getNota();
                    est[j + 1] = est[j];
                    est[j] = new Estudiante(nombre, sexo, nota);
                }
            }
        }
    }
}
