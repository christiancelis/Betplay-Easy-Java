package com.soccer;

import java.util.Scanner;

import com.soccer.view.viewPlayer;
import com.soccer.view.viewTeam;

public class Main {

  
    public static void main(String[] args) {

        Controller ctrlTeams = new Controller();
        Scanner sc = new Scanner(System.in);

        viewTeam.controlador = ctrlTeams;
        viewPlayer.controlador = ctrlTeams;
        
        viewTeam vt = new viewTeam();
        viewPlayer vtp = new viewPlayer();



       

        while (true) {
            System.out.println("........................................................................................");
        System.out.println("\r\n" + //
                        "██╗░░░░░██╗░██████╗░░█████╗░  ██████╗░███████╗████████╗██████╗░██╗░░░░░░█████╗░██╗░░░██╗\r\n" + //
                        "██║░░░░░██║██╔════╝░██╔══██╗  ██╔══██╗██╔════╝╚══██╔══╝██╔══██╗██║░░░░░██╔══██╗╚██╗░██╔╝\r\n" + //
                        "██║░░░░░██║██║░░██╗░███████║  ██████╦╝█████╗░░░░░██║░░░██████╔╝██║░░░░░███████║░╚████╔╝░\r\n" + //
                        "██║░░░░░██║██║░░╚██╗██╔══██║  ██╔══██╗██╔══╝░░░░░██║░░░██╔═══╝░██║░░░░░██╔══██║░░╚██╔╝░░\r\n" + //
                        "███████╗██║╚██████╔╝██║░░██║  ██████╦╝███████╗░░░██║░░░██║░░░░░███████╗██║░░██║░░░██║░░░\r\n" + //
                        "╚══════╝╚═╝░╚═════╝░╚═╝░░╚═╝  ╚═════╝░╚══════╝░░░╚═╝░░░╚═╝░░░░░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░");
        System.out.println("........................................................................................");



       

          
        while(true){
            System.out.println("Menu de Gestion de Equipos");
            System.out.println("1 Equipos");
            System.out.println("2.Jugadores");
            System.out.println("3.Doctores");
            System.out.println("4.Entrenadores");
            System.out.println("5.Salir");
    
            String opcion = "";
            try {
                System.out.print("Digite una opcion para acceder a la informacion de cada item: ");
                opcion = sc.nextLine();     
            } catch (Exception e) {
                System.out.println("Error: "+ e);
            }
            
            switch (opcion) {
                case "1":
                    //menu equipo.
                    vt.start(sc);
                    break;
                case "2":
                    vtp.startp(sc);
                    break;
                case "3":
                    break;
                case "4":
                    return;
                case "5":
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }

    }
}