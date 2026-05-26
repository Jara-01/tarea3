package Tarea1;

import java.util.Scanner;

/**
 * Clase main interactivo que simula el uso de un expendedor.
 * Permite al usuario seleccionar una moneda, elegir un producto,
 * intentar comprarlo y decidir si consumirlo o si tomar el vuelto.
 */
public class MainInteractivo {

    /**
     *  Método principal que ejecuta el programa interactivo.
     *  @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        Moneda moneda = null;
        Expendedor expendedor = new Expendedor(3);
        Comprador comprador = null;

        boolean quedarte = true;

        System.out.println("Ves una expendedora\n1)Comprar algo\n2)Irte");
        System.out.print(">> ");

        if(sc.nextInt()==1){

            while(quedarte){

                System.out.println("¿Que moneda usaras?\n1) 100\n2) 500\n3) 1000\n4) 1500\n5)Probar sin usar monedas");
                System.out.print(">> ");


                switch (sc.nextInt()){
                    case 1:
                        moneda = new Moneda100();
                        break;
                    case 2:
                        moneda = new Moneda500();
                        break;
                    case 3:
                        moneda = new Moneda1000();
                        break;
                    case 4:
                        moneda = new Moneda1500();
                        break;
                    case 5:
                        moneda = null;
                        break;
                    default:
                        System.out.println("Opcion invalida");
                        continue;
                }


                boolean seleccionando = true;
                while (seleccionando) {
                    System.out.println("En la expendedora hay:\n1)Coca Cola\n2)Sprite" +
                                         "\n3)Fanta\n4)Snickers\n5)Super8\n6)Irte");
                    System.out.print(">> ");

                    int opcion = sc.nextInt();

                    if (opcion == 6) {
                        quedarte = false;
                        comprador = null;
                        break;

                    } else if (opcion < 1 || 6 < opcion) {
                        System.out.println("Ingreso invalido");
                        continue;

                    } else {
                        try {
                            comprador = new Comprador(moneda, opcion, expendedor);
                            moneda = null;
                            seleccionando = false;

                        } catch (NoHayProductoException e) {
                            System.out.println(e.getMessage());
                            comprador = null;

                        } catch (PagoInsuficienteException e) {
                            System.out.println(e.getMessage());
                            comprador = null;

                        } catch (PagoIncorrectoException e) {
                            System.out.println(e.getMessage());
                            comprador = null;
                        }
                    }
                }
                if(comprador == null){
                    continue;
                }

                if (comprador.queConsumiste() != null) {

                    System.out.println("Compraste algo\n¿Consumir?\n1)si\n2)no");
                    System.out.print(">> ");

                    if (sc.nextInt() == 1) {
                        System.out.println(comprador.queConsumiste());
                    }
                }

                System.out.println("¿Tomar el vuelto?\n1)si\n2)no");
                System.out.print(">> ");
                if (sc.nextInt() == 1) {
                    System.out.println("Sacaste: " + comprador.cuantoVuelto());
                }

                System.out.println("¿Volver a intentar?\n1)si\n2)no");
                System.out.print(">> ");
                if (sc.nextInt() == 2) {
                    quedarte = false;
                }
            }
        }
        sc.close();
    }
}