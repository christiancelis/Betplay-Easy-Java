package com.soccer.view;


import java.util.Enumeration;
import java.util.Scanner;
import com.soccer.Controller;
import com.soccer.model.entity.Player;
import com.soccer.model.entity.Team;

public class viewPlayer {
        public static Controller controlador;
       
       public void startp(Scanner scanner){
       Validador val = new Validador();
        String codigoE = null;
        String codigoJ =null;
        int choice;
        

       System.out.println("\r\n" + //
                            "░░░░░██╗██╗░░░██╗░██████╗░░█████╗░██████╗░░█████╗░██████╗░\r\n" + //
                            "░░░░░██║██║░░░██║██╔════╝░██╔══██╗██╔══██╗██╔══██╗██╔══██╗\r\n" + //
                            "░░░░░██║██║░░░██║██║░░██╗░███████║██║░░██║██║░░██║██████╔╝\r\n" + //
                            "██╗░░██║██║░░░██║██║░░╚██╗██╔══██║██║░░██║██║░░██║██╔══██╗\r\n" + //
                            "╚█████╔╝╚██████╔╝╚██████╔╝██║░░██║██████╔╝╚█████╔╝██║░░██║\r\n" + //
                            "░╚════╝░░╚═════╝░░╚═════╝░╚═╝░░╚═╝╚═════╝░░╚════╝░╚═╝░░╚═╝");

        while (true) {
            System.out.println("\n1. Crear Jugador");
            System.out.println("2. Actualizar Jugador");
            System.out.println("3. Buscar Jugador");
            System.out.println("4. Eliminar jugador");
            System.out.println("5. Listar todos jugadores");
            System.out.println("6. Volver");

            choice = Integer.parseInt(val.leerdato("Elija una opcion: ", scanner));
            System.out.println("\n");

            switch (choice) {
                case 1:
                    
                     try {
                            codigoE = val.leerdato("Ingrese el codigo del equipo: ", scanner);
                            if(!controlador.equipos.containsKey(codigoE)){
                                System.out.println("El Codigo de Equipo no existe");
                                continue;
                            }
                            crearJugador(val, codigoE, scanner);
                     } catch (Exception e) {
                            System.out.println(e);
                            continue;
                     }
                     break;

                case 2:
                    
                    break;

                case 3:
                    codigoJ = val.leerdato("Ingrese el codigo del jugador a buscar: ", scanner);
                    if(!controlador.jugadores.containsKey(codigoJ)){
                        System.out.println("El Codigo de Jugador no existe");
                        continue;
                    }
                    buscarJugador(codigoJ);
                    break;
                case 4:
                    codigoJ = val.leerdato("Ingrese el codigo del jugador a eliminar: ", scanner);
                    if(!controlador.jugadores.containsKey(codigoJ)){
                        System.out.println("El Codigo de Jugador no existe");
                        continue;
                    }
                    eliminarJugador(codigoJ);
                    break;
                case 5:
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Opcion invalida, intentelo de nuevo.");
            }
        }
    }


    public static void crearJugador(Validador val, String codigoE, Scanner sc){
        while (true) {
            Team eq = controlador.equipos.get(codigoE);
            String id = val.leerdato("Ingresa el id del usuario: ",sc);
            if(controlador.jugadores.containsKey(id)){
                System.out.println("Jugador ya existe");
                continue;
            }
            Player jugador = new Player();

            jugador.setId(id);
            jugador.setNombre(val.leerdato("Digita el nombre del jugador: ",sc));
            jugador.setApellido(val.leerdato("Digite Apellido del jugador: ", sc));
            jugador.setEdad(val.leerNumero("Digite edad del jugador: ", sc));
            jugador.setDorsal(val.leerNumero("Digite dorsal del jugador: ", sc));
            jugador.setPosicion(val.leerdato("Digite posicion del jugador: ", sc));

            eq.getLstJugadores().add(jugador);
            controlador.jugadores.put(id, jugador);

            return ;
        }
    }

    public static void buscarJugador(String codeJ) {
        Player jugador = controlador.jugadores.get(codeJ);
        System.out.println("Jugador: " + codeJ);
        System.out.println("Nombre: " + jugador.getNombre() +", Apellido: " +  jugador.getApellido() + ", Edad: " + jugador.getEdad() + ", Numero Camisa: " + jugador.getDorsal() + ", Posicion: " + jugador.getPosicion() );
    }

    public static void eliminarJugador(String codeJ){
        controlador.jugadores.remove(codeJ);
        System.out.println("Jugador eliminado con exito.");
    }

}