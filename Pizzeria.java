
package com.upchiapas.pizzeria;

import com.upchiapas.pizzeria.models.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Pizzeria {

    public static void main(String[] args) {
        ArrayList<Cliente> listaCli = new ArrayList<>();
        ArrayList<OrdenCompra> ordenes = new ArrayList<>();
        CatalogoPizza catalogo = new CatalogoPizza();
        Scanner consola = new Scanner(System.in);
        int opcion;
        while (opcion <9) {
            System.out.println("Flamingo's Pizza");
            System.out.println("Inserte la opción que desee \n"
                    + "1.- Crear clientes\n" + "2.- Crear especialidad\n"
                    + "3.- Ordenamiento por ID\n" + "4.- Ordenamiento Burbuja\n"
                    + "5.- catalago de piizas\n" + "6.- Crear una orden de compra\n"
                    + "7.- Reportes de venta\n" + "8.- Salir");

            switch (opcion) {
                case 1:
                    System.out.println("¿Cliente?");
                    String nombre = consola.nextLine();
                    System.out.println("¿Domicilio?");
                    String direccion = consola.nextLine();
                    System.out.println("¿Celular");
                    String telefono = consola.nextLine();

                    listaCli.add(new Cliente(nombre, direccion, telefono));
                    break;

                case 2:
                    System.out.println("¿especialidad?");
                    String especialidad = consola.nextLine();
                    System.out.println("¿precio?");
                    float precio = Float.parseFloat(consola.nextLine());
                    catalogo.insertarPizza(new Pizza(especialidad, precio));
                    break;
                case 3:
                    Cliente.imprimirListadoClientes(listaCli);
                    break;
                case 4:
                    listaCli = Cliente.OrdenarAlfabeticamente(listaCli);
                    break;
                case 5:
                    catalogo.imprimirCatalogo();
                    break;
                case 6:
                    OrdenCompra orden = new OrdenCompra();
                    orden.insertarItemCompra(catalogo);
                    System.out.println("¿Nombre del cliente?");
                    orden.setCliente(Cliente.seleccionarCliente(listaCli));
                    ordenes.add(orden);
                    break;
                case 7:
                    String Venta = "";

                    //de lado izquierdo es el elemento en singular y el lado derecho el listado de donde voy recorrer mis elementos
                    for (OrdenCompra ord : ordenes) {
                        Venta += "Compra #" + ord.getIdOrdenCompra() + " Items:\n";
                        float totalCompra = 0;
                        for(ItemCompra ordItem: ord.getProductosItem()){
                            Venta += "Especialidad: " +ordItem.getProducto().getEspecialidad() + "\t" + "Precio unitario: " + ordItem.getProducto().getPrecio() + "\t" + "Cantidad: " + ordItem.getCantidad() + "\t" + "Subtotal: " + ordItem.getSubtotal() + "\n";
                            totalCompra += ordItem.getSubtotal();
                        }
                        Venta += "Total de compra #" + ord.getIdOrdenCompra() + ": " + totalCompra + "\n";
                        Venta += "Cliente: " + ord.getCliente().getNombre() + "\n";
                    }

                    System.out.println(Venta);
                    break;
                case 8:
                    System.out.println("hasta luego");
                break;
                default:
                    System.out.println("La opcion elegida no esta dentro del rango, por favor intentelo de nuevo");
            }
        }
    }

}
