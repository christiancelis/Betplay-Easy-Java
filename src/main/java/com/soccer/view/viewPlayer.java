package com.soccer.view;


import java.util.Scanner;
import com.soccer.Controller;
import com.soccer.model.entity.Player;
import com.soccer.model.entity.Team;

public class viewPlayer {
       
       public void startp(Scanner scanner,Controller controlador){
       Validador val = new Validador();
       Player pl = new Player();
        String codigoE = null;
        Team eq = new Team();
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
            System.out.println("6. Salir");

            choice = Integer.parseInt(val.leerdato("Elija una opcion: ", scanner));
            System.out.println("\n");

            switch (choice) {
                case 1:
                     try {
                            codigoE = val.leerdato("Ingrese el codigo del equipo: ", scanner);
                            eq = controlador.equipos.get(codigoE);

                            if (eq == null) {
                                   System.out.println("Error, Codigo No Existe");
                                   continue;
                            }
                            
                     } catch (Exception e) {
                            System.out.println("El Codigo de Equipo no existe");
                            continue;
                     }
                     
                     crearJugador(val, controlador, eq,pl, codigoE, scanner);
                     break;

                case 2:
                    
                    break;

                case 3:
                    break;
                case 4:
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


    public static void crearJugador(Validador val, Controller controlador, Team eq,Player jugador, String codigoE, Scanner sc){
        while (true) {
            eq = controlador.equipos.get(codigoE);
            String id = val.leerdato("Ingresa el id del usuario: ",sc);
            Player pl = controlador.jugadores.get(id);
            if(pl!=null){
                System.out.println("Jugador ya existe");
                continue;
            }
            jugador.setId(id);
            jugador.setNombre(val.leerdato("Digita el nombre del jugador: ",sc));
            jugador.setApellido(val.leerdato("Digite Apellido del jugador: ", sc));
            jugador.setEdad(val.leerNumero("Digite edad del jugador: ", sc));
            jugador.setDorsal(val.leerNumero("Digite dorsal del jugador: ", sc));
            jugador.setPosicion(val.leerdato("Digite posicion del jugador: ", sc));

            eq.setLstJugadores(jugador);
            controlador.jugadores.put(id, jugador);

            return ;
        }
       
    };

}