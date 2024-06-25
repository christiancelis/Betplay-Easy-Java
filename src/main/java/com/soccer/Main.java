package com.soccer;

import java.util.Scanner;

import com.soccer.view.viewCoach;
import com.soccer.view.viewDoctor;
import com.soccer.view.viewPlayer;
import com.soccer.view.viewTeam;

public class Main {

  
    public static void main(String[] args) {

        Controller ctrlTeams = new Controller();
        Scanner sc = new Scanner(System.in);

        viewTeam.controlador = ctrlTeams;
        viewPlayer.controlador = ctrlTeams;
        viewDoctor.controlador = ctrlTeams;
        viewCoach.controlador = ctrlTeams;
        
        viewTeam vt = new viewTeam();
        viewPlayer vtp = new viewPlayer();
        viewDoctor vtd = new viewDoctor();
        viewCoach vtc = new viewCoach();



       

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
                    if(ctrlTeams.equipos.isEmpty()){
                        System.out.println("\nInserte Equipos para acceder a este menu\n");
                        continue;
                    }
                    vtp.startp(sc);
                    break;
                case "3":
                if(ctrlTeams.equipos.isEmpty()){
                    System.out.println("\nInserte Equipos para acceder a este menu\n");
                    continue;
                }
                    vtd.startD(sc);
                    break;
                case "4":
                if(ctrlTeams.equipos.isEmpty()){
                    System.out.println("\nInserte Equipos para acceder a este menu\n");
                    continue;
                }
                    vtc.startC(sc);
                    break;
                case "5":
                System.out.println("\nSaliendo.................................\n");
                System.out.println("\nMade by Christian Celis - DarkHouse - All Right Reserved - Liga BetPlay \n");
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