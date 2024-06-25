package com.soccer.view;

import java.util.Enumeration;
import java.util.Scanner;
import com.soccer.Controller;
import com.soccer.model.entity.Coach;
import com.soccer.model.entity.Doctor;
import com.soccer.model.entity.Player;
import com.soccer.model.entity.Team;


public class viewTeam {

    public static Controller controlador;

    public void start(Scanner scanner) {

        Validador val = new Validador();
        String codigoE = null;
        int choice = 0;

        while (true) {
            
            System.out.println("\n1. Crear Equipo");
            System.out.println("2. Actualizar Equipo");
            System.out.println("3. Buscar Equipo");
            System.out.println("4. Eliminar Equipo");
            System.out.println("5. Listar todos Equipos");
            System.out.println("6. Volver");

            choice = Integer.parseInt(val.leerdato("Elija una opcion: ", scanner));
            System.out.println("\n");

            Team equipo = null;
                            if (equipo == null) {
                                equipo = new Team();
                            }  

            switch (choice) {
                case 1:
                
                    
                        try {
                             codigoE = val.leerdato("Ingrese el codigo del equipo: ", scanner);
                        
                            // Check if team exists
                            if (controlador.equipos.containsKey(codigoE)) {
                                System.out.println("Error: Codigo ya Existe");
                                continue;
                            }
                                         
                            equipo.setNombre(val.leerdato("Ingrese Nombre del equipo: ", scanner));
                            equipo.setCiudad(val.leerdato("Ingrese la ciudad: ", scanner));
                        
                            controlador.equipos.put(codigoE, equipo);
                            System.out.println("Equipo creado con exito");
                            break;
                        } catch (Exception e) {
                            System.out.println("Error al crear equipo: " + e.getMessage());
                        }
                    
                    break;
                case 2:
                    try {
                        String campoActualizado = "";

                        codigoE = val.leerdato("Ingrese Codigo del Equipo: ", scanner);
                        equipo = controlador.equipos.get(codigoE);

                        System.out.println("\nCampos del equipo " + equipo.getNombre());
                        System.out.println("1.nombre");
                        System.out.println("2.ciudad");

                        int op = Integer.parseInt(val.leerdato("Elige la opcion del campo a actualizar:", scanner));

                        switch (op) {
                            case 1:
                                try {
                                    System.out.println("Campo Actual: " + equipo.getNombre());
                                    campoActualizado = val.leerdato("Digita el nombre: ", scanner);
                                    equipo.setNombre(campoActualizado);
                                } catch (Exception e) {
                                    System.out.println("Error al actualizar el campo: " + e);
                                }
                                break;

                            case 2:
                                try {
                                    System.out.println("Campo Actual: " + equipo.getNombre());
                                    campoActualizado = val.leerdato("Digita la ciudad: ", scanner);
                                    equipo.setCiudad(campoActualizado);

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
                        equipo = controlador.equipos.get(codigoE);
                        System.out.println("\nEquipo");
                        System.out.println("Codigo Equipo: " + codigoE);
                        System.out.println("Nombre: " + equipo.getNombre());
                        System.out.println("Ciudad: " + equipo.getCiudad() + "\n");
                        
                    } catch (Exception e) {
                        System.out.println("Equipo no encontrado -> Mensaje de Error: " + e);
                    }
                    System.out.println("Jugadores: ");
                    for (Player item : equipo.getLstJugadores()) {

                        System.out.println("\nId: " + item.getId() + ", Nombre: "+ item.getNombre() + ", Apellido: " + item.getApellido() + ", Edad: " + item.getEdad() + ", Numero camisa: " + item.getDorsal() + ", Posicion: " + item.getPosicion());
                    }
                    System.out.println("Doctores: ");
                    for (Doctor item : equipo.getLstMasajistas()) {
                        System.out.println("Id: " + item.getId() + ",Nombre: " + item.getNombre() + ", Apellido: " + item.getApellido() + ", Edad: "
                        + item.getEdad() + ", Titulo: " + item.getTitulo() + ", Años de Experiencia: "
                        + item.getExpYear());
                        
                    }
                    System.out.println("Entrenadores: ");
                    for (Coach item : equipo.getLstEntrenadores()) {
                        System.out.println("Id: " + item.getId()+ "Nombre: " + item.getNombre() + ", Apellido: " + item.getApellido() + ", Edad: "
                        + item.getEdad() + ", id Federacion: " + item.getIdFederacion());
                        
                    }

                    break;
                case 4:
                    try {
                        codigoE = val.leerdato("Ingrese Codigo del Equipo: ", scanner);
                        equipo = controlador.equipos.get(codigoE);
                        Enumeration<String> llaves = controlador.equipos.keys();
                        String llave = "";
                        while (llaves.hasMoreElements()) {
                            llave = String.valueOf(llaves.nextElement());
                            if (codigoE.equals(llave)) {
                                controlador.equipos.remove(codigoE);
                                System.out.println("Equipo " + equipo.getNombre() + " eliminado");
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Equipo no encontrado -> Mensaje de Error: " + e);
                    }
                    break;
                case 5:
                    MostrarEquipos();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opcion invalida, intentelo de nuevo.");
            }
        }
    }




    public static void MostrarEquipos(){
        try {
            Enumeration<String> llaves = controlador.equipos.keys();
            String llave = "";
            if (!llaves.hasMoreElements()) {
                System.out.println(">> No hay equipos para mostrar, Inserte Equipos");
                return;
            }

            while (llaves.hasMoreElements()) {
                llave = String.valueOf(llaves.nextElement());
                Team e = controlador.equipos.get(llave);
                System.out.println("\n Equipo con codigo: " + llave);
                System.out.println("Nombre Equipo: " + e.getNombre() + ", Ciudad: " + e.getCiudad() + "\n");
                System.out.println("Jugadores: ");
                for (Player item : e.getLstJugadores()) {

                    System.out.println("\nId: " + item.getId() + ", Nombre: "+ item.getNombre() + ", Apellido: " + item.getApellido() + ", Edad: " + item.getEdad() + ", Numero camisa: " + item.getDorsal() + ", Posicion: " + item.getPosicion());
                }
                System.out.println("Doctores: ");
                for (Doctor item : e.getLstMasajistas()) {
                    System.out.println("Id: " + item.getId() + ",Nombre: " + item.getNombre() + ", Apellido: " + item.getApellido() + ", Edad: "
                    + item.getEdad() + ", Titulo: " + item.getTitulo() + ", Años de Experiencia: "
                    + item.getExpYear());
                    
                }
                System.out.println("Entrenadores: ");
                for (Coach item : e.getLstEntrenadores()) {
                    System.out.println("Id: " + item.getId()+ "Nombre: " + item.getNombre() + ", Apellido: " + item.getApellido() + ", Edad: "
                    + item.getEdad() + ", id Federacion: " + item.getIdFederacion());
                    
                }
            }
        } catch (Exception e) {
            System.out.println("Equipo no encontrado -> Mensaje de Error: " + e);
        }
        return;
    }
}


