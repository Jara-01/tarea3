package Tarea1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Tarea 1 de Desarrollo Orientado a Objetos
 * @author Benjamín Antonio Rivas Sepulveda
 * @author Ignacio Moisés Lama Méndez
 * @author Joaquín Enrique Jara Troncoso
 */
public class Main {
    static public void main(String[] args) {
        /**
         * Se crea un expendedor con 2 productos de cada tipo.
         * Luego se hacen varias pruebas para demostrar compras correctas,
         * excepciones y el ordenamiento de monedas con Comparable.
         */
        Expendedor ex = new Expendedor(2);

        /**
         * Primera prueba: compra correcta.
         * Se compra una CocaCola con una moneda mayor al precio,
         * por lo tanto deberia entregar producto y vuelto.
         */
        try {
            Comprador c1 = new Comprador(new Moneda1500(), Precios.COCACOLA.getID(), ex);
            System.out.println("Consumiste: " + c1.queConsumiste());
            System.out.println("Vuelto: " + c1.cuantoVuelto());
        } catch (NoHayProductoException e) {
            System.out.println(e.getMessage());
        } catch (PagoInsuficienteException e) {
            System.out.println(e.getMessage());
        } catch (PagoIncorrectoException e) {
            System.out.println(e.getMessage());
        }

        /**
         * Segunda prueba: pago insuficiente.
         * Se intenta comprar un Snickers con una moneda que no alcanza.
         */
        try {
            Comprador c2 = new Comprador(new Moneda100(), Precios.SNICKERS.getID(), ex);
            System.out.println("Consumiste: " + c2.queConsumiste());
            System.out.println("Vuelto: " + c2.cuantoVuelto());
        } catch (NoHayProductoException e) {
            System.out.println(e.getMessage());
        } catch (PagoInsuficienteException e) {
            System.out.println(e.getMessage());
        } catch (PagoIncorrectoException e) {
            System.out.println(e.getMessage());
        }

        /**
         * Tercera prueba: intento de compra sin moneda.
         * En este caso debe levantarse la excepcion de pago incorrecto.
         */
        try {
            Comprador c3 = new Comprador(null, Precios.SPRITE.getID(), ex);
            System.out.println("Consumiste: " + c3.queConsumiste());
            System.out.println("Vuelto: " + c3.cuantoVuelto());
        } catch (NoHayProductoException e) {
            System.out.println(e.getMessage());
        } catch (PagoInsuficienteException e) {
            System.out.println(e.getMessage());
        } catch (PagoIncorrectoException e) {
            System.out.println(e.getMessage());
        }

        /**
         * Cuarta prueba: se intenta comprar un producto con un codigo invalido.
         * Como ese deposito no existe, se debe lanzar excepcion.
         */
        try {
            Comprador c4 = new Comprador(new Moneda1000(), 99, ex);
            System.out.println("Consumiste: " + c4.queConsumiste());
            System.out.println("Vuelto: " + c4.cuantoVuelto());
        } catch (NoHayProductoException e) {
            System.out.println(e.getMessage());
        } catch (PagoInsuficienteException e) {
            System.out.println(e.getMessage());
        } catch (PagoIncorrectoException e) {
            System.out.println(e.getMessage());
        }

        /**
         * Quinta prueba: se vacia un deposito para demostrar
         * el caso en que ya no quedan productos de ese tipo.
         * (Prueba añadida) Se agregó esta prueba vaciando un depósito
         * mediante compras repetidas del mismo producto,
         * con el fin de demostrar el caso de NoHayProductoException.
         * Esta prueba no está pedida literalmente en la pauta;
         * solo se exige demostrar el caso de que no haya producto,
         * pero se consideró su implementación para abarcar más situaciones.
         */
        try {
            Comprador c5 = new Comprador(new Moneda1000(), Precios.FANTA.getID(), ex);
            System.out.println("Consumiste: " + c5.queConsumiste());
            System.out.println("Vuelto: " + c5.cuantoVuelto());

            Comprador c6 = new Comprador(new Moneda1000(), Precios.FANTA.getID(), ex);
            System.out.println("Consumiste: " + c6.queConsumiste());
            System.out.println("Vuelto: " + c6.cuantoVuelto());

            Comprador c7 = new Comprador(new Moneda1000(), Precios.FANTA.getID(), ex);
            System.out.println("Consumiste: " + c7.queConsumiste());
            System.out.println("Vuelto: " + c7.cuantoVuelto());
        } catch (NoHayProductoException e) {
            System.out.println(e.getMessage());
        } catch (PagoInsuficienteException e) {
            System.out.println(e.getMessage());
        } catch (PagoIncorrectoException e) {
            System.out.println(e.getMessage());
        }

        /**
         * Sexta prueba: se crea una lista de monedas y se ordena
         * usando el compareTo implementado en la clase Moneda.
         */
        ArrayList<Moneda> monedas = new ArrayList<Moneda>();
        monedas.add(new Moneda500());
        monedas.add(new Moneda100());
        monedas.add(new Moneda1500());
        monedas.add(new Moneda1000());
        monedas.add(new Moneda100());

        Collections.sort(monedas);

        for (Moneda m : monedas) {
            System.out.println(m);
        }
    }
}
