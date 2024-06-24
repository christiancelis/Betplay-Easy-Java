package com.soccer.view;

import java.util.Enumeration;
import java.util.Scanner;
import com.soccer.Controller;
import com.soccer.model.entity.Team;


public class viewTeam {
    public static Controller controlador;

    public void start(Scanner scanner) {
        Validador val = new Validador();
        String codigoE = null;
        Team eq = new Team();
        int choice;

        while (true) {
            System.out.println("\n1. Crear Equipo");
            System.out.println("2. Actualizar Equipo");
            System.out.println("3. Buscar Equipo");
            System.out.println("4. Eliminar Equipo");
            System.out.println("5. Listar todos Equipos");
            System.out.println("6. Gestionar Plantilla");
            System.out.println("7. Salir");

            choice = Integer.parseInt(val.leerdato("Elija una opcion: ", scanner));
            System.out.println("\n");

            switch (choice) {
                case 1:
                    codigoE = val.leerdato("Ingrese el codigo del equipo: ", scanner);
                    Team a = controlador.equipos.get(codigoE);

                    if (a != null) {
                        System.out.println("Error, Codigo ya Existe");
                        continue;
                    }
                    eq.setNombre(val.leerdato("Ingrese Nombre del equipo: ", scanner));
                    eq.setCiudad((val.leerdato("Ingrese la ciudad: ", scanner)));
                    controlador.equipos.put(codigoE, eq);
                    break;
                case 2:
                    try {
                        String campoActualizado = "";

                        codigoE = val.leerdato("Ingrese Codigo del Equipo: ", scanner);
                        eq = controlador.equipos.get(codigoE);

                        System.out.println("\nCampos del equipo " + eq.getNombre());
                        System.out.println("1.nombre");
                        System.out.println("2.ciudad");

                        int op = Integer.parseInt(val.leerdato("Elige la opcion del campo a actualizar:", scanner));

                        switch (op) {
                            case 1:
                                try {
                                    System.out.println("Campo Actual: " + eq.getNombre());
                                    campoActualizado = val.leerdato("Digita el nombre: ", scanner);
                                    eq.setNombre(campoActualizado);
                                } catch (Exception e) {
                                    System.out.println("Error al actualizar el campo: " + e);
                                }
                                break;

                            case 2:
                                try {
                                    System.out.println("Campo Actual: " + eq.getNombre());
                                    campoActualizado = val.leerdato("Digita la ciudad: ", scanner);
                                    eq.setCiudad(campoActualizado);

                                } catch (Exception e) {
                                    System.out.println("Error al actualizar el campo: " + e);
                                }
                                break;

                            default:
                                System.out.println("Opcion no Valida");
                                break;
                        }

                    } catch (Exception e) {
                        System.out.println("Equipo no encontrado -> Mensaje de Error: " + e);
                    }
                    break;

                case 3:
                    try {
                        codigoE = val.leerdato("Ingrese Codigo del Equipo: ", scanner);
                        eq = controlador.equipos.get(codigoE);
                        System.out.println("\nEquipo");
                        System.out.println("Codigo Equipo: " + codigoE);
                        System.out.println("Nombre: " + eq.getNombre());
                        System.out.println("Ciudad: " + eq.getCiudad() + "\n");
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Equipo no encontrado -> Mensaje de Error: " + e);
                    }
                    break;
                case 4:
                    try {
                        codigoE = val.leerdato("Ingrese Codigo del Equipo: ", scanner);
                        eq = controlador.equipos.get(codigoE);
                        Enumeration<String> llaves = controlador.equipos.keys();
                        String llave = "";
                        while (llaves.hasMoreElements()) {
                            llave = String.valueOf(llaves.nextElement());
                            if (codigoE.equals(llave)) {
                                controlador.equipos.remove(codigoE);
                                System.out.println("Equipo " + eq.getNombre() + " eliminado");
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Equipo no encontrado -> Mensaje de Error: " + e);
                    }
                    break;
                case 5:
                    MostrarEquipos(controlador,eq);
                    break;
                case 6:

                try {
                    Enumeration<String> llaves = controlador.equipos.keys();
                    if (!llaves.hasMoreElements()) {
                        System.out.println(">> Para ingresar a este menu por favor inserte Equipos");
                        continue;
                    }
                    MenuGestionEquipos(scanner);  
                } catch (Exception e) {
                        System.out.println("-> Mensaje de Error: " + e);
                }
                    
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Opcion invalida, intentelo de nuevo.");
            }
        }
    }




    public static void MostrarEquipos(Controller controlador, Team eq){
        try {
            Enumeration<String> llaves = controlador.equipos.keys();
            String llave = "";
            if (!llaves.hasMoreElements()) {
                System.out.println(">> No hay equipos para mostrar, Inserte Equipos");
                return;
            }
            while (llaves.hasMoreElements()) {
                eq = null;
                llave = String.valueOf(llaves.nextElement());
                eq = controlador.equipos.get(llave);
                System.out.println("Equipo Numero: " + llave);
                System.out.println(eq.toString() + "\n"); 
            }
        } catch (Exception e) {
            System.out.println("Equipo no encontrado -> Mensaje de Error: " + e);
        }
        return;
    }

    public static void MenuGestionEquipos(Scanner sc){
        viewPlayer vtp = new viewPlayer();
        System.out.println("Menu de Gestion de Equipos");
        System.out.println("1.Jugadores");
        System.out.println("2.Doctores");
        System.out.println("3.Entrenadores");
        System.out.println("4.Salir");

        String opcion = "";
        try {
            System.out.print("Digite una opcion para acceder a la informacion de cada item: ");
            opcion = sc.nextLine();     
        } catch (Exception e) {
            System.out.println("Error: "+ e);
        }
        
        switch (opcion) {
            case "1":
            vtp.startp(sc,controlador);
                break;
            case "2":
                break;
            case "5":
                System.out.println("Saliendo................");
                return  ;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }



}
