package com.soccer.view;

import java.util.Enumeration;
import java.util.Scanner;

import com.soccer.Controller;
import com.soccer.model.entity.Coach;
import com.soccer.model.entity.Team;

public class viewCoach {
    public static Controller controlador;

    public void startC(Scanner scanner) {
        Validador val = new Validador();
        String codigoE = null;
        String codigoC = null;
        int choice;

        System.out.println("\r\n" + //
                        "███████╗███╗░░██╗████████╗██████╗░███████╗███╗░░██╗░█████╗░██████╗░░█████╗░██████╗░\r\n" + //
                        "██╔════╝████╗░██║╚══██╔══╝██╔══██╗██╔════╝████╗░██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗\r\n" + //
                        "█████╗░░██╔██╗██║░░░██║░░░██████╔╝█████╗░░██╔██╗██║███████║██║░░██║██║░░██║██████╔╝\r\n" + //
                        "██╔══╝░░██║╚████║░░░██║░░░██╔══██╗██╔══╝░░██║╚████║██╔══██║██║░░██║██║░░██║██╔══██╗\r\n" + //
                        "███████╗██║░╚███║░░░██║░░░██║░░██║███████╗██║░╚███║██║░░██║██████╔╝╚█████╔╝██║░░██║\r\n" + //
                        "╚══════╝╚═╝░░╚══╝░░░╚═╝░░░╚═╝░░╚═╝╚══════╝╚═╝░░╚══╝╚═╝░░╚═╝╚═════╝░░╚════╝░╚═╝░░╚═╝");

        while (true) {
            System.out.println("\n1. Crear entrenador");
            System.out.println("2. Actualizar entrenador");
            System.out.println("3. Buscar entrenador");
            System.out.println("4. Eliminar entrenador");
            System.out.println("5. Listar todos entrenadores");
            System.out.println("6. Volver");

            choice = Integer.parseInt(val.leerdato("Elija una opcion: ", scanner));
            System.out.println("\n");

            switch (choice) {
                case 1:

                    try {
                        codigoE = val.leerdato("Ingrese el codigo del equipo: ", scanner);
                        if (!controlador.equipos.containsKey(codigoE)) {
                            System.out.println("El Codigo de Equipo no existe");
                            continue;
                        }
                        crearEntrenador(val, codigoE, scanner);
                    } catch (Exception e) {
                        System.out.println(e);
                        continue;
                    }
                    break;

                case 2:
                    codigoC = val.leerdato("Ingrese el codigo del entrenador a actualizar: ", scanner);
                    if (!controlador.entrenadores.containsKey(codigoC)) {
                        System.out.println("El Codigo de entrenador no existe");
                        continue;
                    }
                    actualizarEntrenador(val,codigoC,scanner);

                    break;

                case 3:
                    codigoC = val.leerdato("Ingrese el codigo del entrenador a buscar: ", scanner);
                    if (!controlador.entrenadores.containsKey(codigoC)) {
                        System.out.println("El Codigo de entrenador no existe");
                        continue;
                    }
                    buscarEntrenador(codigoC);
                    break;
                case 4:
                    codigoE = val.leerdato("Ingrese el codigo del equipo: ", scanner);
                    if (!controlador.equipos.containsKey(codigoE)) {
                        System.out.println("El Codigo de Equipo no existe");
                        continue;
                    }
                    codigoC = val.leerdato("Ingrese el codigo del entrenador a eliminar: ", scanner);
                    if (!controlador.entrenadores.containsKey(codigoC)) {
                        System.out.println("El Codigo de entrenador no existe");
                        continue;
                    }
                    eliminarEntrenador(codigoE, codigoC);
                    break;
                case 5:
                    ListarEntrenadores();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opcion invalida, intentelo de nuevo.");
            }
        }
    }

    public static void crearEntrenador(Validador val, String codigoE, Scanner sc) {
        while (true) {
            Team eq = controlador.equipos.get(codigoE);
            String id = val.leerdato("Ingresa el id del entrenador: ", sc);
            if (controlador.entrenadores.containsKey(id)) {
                System.out.println("Entrenador ya existe");
                continue;
            }
            Coach entrenador = new Coach();

            entrenador.setId(id);
            entrenador.setNombre(val.leerdato("Digita el nombre del entrenador: ", sc));
            entrenador.setApellido(val.leerdato("Digite Apellido del entrenador: ", sc));
            entrenador.setEdad(val.leerNumero("Digite edad del entrenador: ", sc));
            entrenador.setIdFederacion(val.leerNumero("Digite el id de la federacion del entrenador", sc));

            eq.getLstEntrenadores().add(entrenador);
            controlador.entrenadores.put(id, entrenador);
            return;
        }
    }

    public static void buscarEntrenador(String codeC) {
        Coach entrenador = controlador.entrenadores.get(codeC);
        System.out.println("Entrenador: " + codeC);
        System.out.println("Nombre: " + entrenador.getNombre() + ", Apellido: " + entrenador.getApellido() + ", Edad: "
                + entrenador.getEdad() + ", id Federacion: " + entrenador.getIdFederacion());
    }

    public static void eliminarEntrenador(String codeE, String codeC) {
        controlador.entrenadores.remove(codeC);

        Team equipo = controlador.equipos.get(codeE);
        for (Coach item : equipo.getLstEntrenadores()) {
            if(item.getId().equals(codeC)){
                equipo.getLstEntrenadores() .remove(item);
                break;
            }
        }
        System.out.println("Entrenador eliminado con exito.");
    }

    public static void ListarEntrenadores(){
        Enumeration<String> llaves = controlador.entrenadores.keys();
        String llave = "";
        if (!llaves.hasMoreElements()) {
            System.out.println(">> No hay  para mostrar, Inserte Equipos");
            return;
        }
        System.out.println("Entrenadores");
        while (llaves.hasMoreElements()) {
            llave = String.valueOf(llaves.nextElement());
            Coach entrenador = controlador.entrenadores.get(llave);
            System.out.println("Id: " + entrenador.getId() + ", Nombre: " + entrenador.getNombre() + ", Apellido: " + entrenador.getApellido() + ", Edad: "
            + entrenador.getEdad() + ", id Federacion: " + entrenador.getIdFederacion());
        }       
    }

    public static void actualizarEntrenador(Validador val, String codigoC, Scanner sc){
        Coach entrenador = controlador.entrenadores.get(codigoC);
        
        System.out.println("Entrenador: " + codigoC);
        System.out.println("Campos a actualizar");
        System.out.println("1. Nombre");
        System.out.println("2. Apellido");
        System.out.println("3. Edad");
        System.out.println("4. id Federacion");

        Integer choice = Integer.parseInt(val.leerdato("Elija una opcion: ", sc));
        System.out.println("\n");

        switch (choice) {
            case 1:
                System.out.println("Campo actual: " + entrenador.getNombre());
                entrenador.setNombre(val.leerdato("Digite el nuevo nombre: ",sc));
                System.out.println("Nombre actualizado correctamente");
                buscarEntrenador(codigoC);
                break;
            case 2:
                System.out.println("Campo actual: " + entrenador.getApellido());
                entrenador.setApellido(val.leerdato("Digite el nuevo apellido: ",sc));
                System.out.println("Apellido actualizado correctamente");
                buscarEntrenador(codigoC);                
                break;
            case 3:
                System.out.println("Campo actual: " + entrenador.getEdad());
                entrenador.setEdad(val.leerNumero("Digite la nueva edad: ",sc));
                System.out.println("Edad actualizada correctamente");
                buscarEntrenador(codigoC);
                break;
            case 4:
                System.out.println("Campo actual: " + entrenador.getIdFederacion());
                entrenador.setIdFederacion(val.leerNumero("Digite el nuevo id de federacion: ",sc));
                System.out.println("Titulo actualizado correctamente");
                buscarEntrenador(codigoC);
                break;
            default:
                System.out.println("Opcion no disponible");
                break;
        }
    }
  

}
