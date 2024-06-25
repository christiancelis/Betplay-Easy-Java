package com.soccer.view;

import java.util.Enumeration;
import java.util.Scanner;

import com.soccer.Controller;
import com.soccer.model.entity.Doctor;
import com.soccer.model.entity.Team;

public class viewDoctor {
    public static Controller controlador;

    public void startD(Scanner scanner) {
        Validador val = new Validador();
        String codigoE = null;
        String codigoD = null;
        int choice;

        System.out.println("\r\n" + //
                        "██████╗░░█████╗░░█████╗░████████╗░█████╗░██████╗░\r\n" + //
                        "██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝██╔══██╗██╔══██╗\r\n" + //
                        "██║░░██║██║░░██║██║░░╚═╝░░░██║░░░██║░░██║██████╔╝\r\n" + //
                        "██║░░██║██║░░██║██║░░██╗░░░██║░░░██║░░██║██╔══██╗\r\n" + //
                        "██████╔╝╚█████╔╝╚█████╔╝░░░██║░░░╚█████╔╝██║░░██║\r\n" + //
                        "╚═════╝░░╚════╝░░╚════╝░░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝");

        while (true) {
            System.out.println("\n1. Crear doctor");
            System.out.println("2. Actualizar doctor");
            System.out.println("3. Buscar doctor");
            System.out.println("4. Eliminar doctor ");
            System.out.println("5. Listar todos doctores");
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
                        crearDoctor(val, codigoE, scanner);
                    } catch (Exception e) {
                        System.out.println(e);
                        continue;
                    }
                    break;

                case 2:
                codigoD = val.leerdato("Ingrese el codigo del jugador a actualizar: ", scanner);
                if (!controlador.doctores.containsKey(codigoD)) {
                    System.out.println("El Codigo de Jugador no existe");
                    continue;
                }
                actualizarDoctor(val,codigoD,scanner);
                break;
                case 3:
                    codigoD = val.leerdato("Ingrese el codigo del doctor a buscar: ", scanner);
                    if (!controlador.doctores.containsKey(codigoD)) {
                        System.out.println("El Codigo de doctor no existe");
                        continue;
                    }
                    buscarDoctor(codigoD);
                    break;
                case 4:
                    codigoE = val.leerdato("Ingrese el codigo del equipo: ", scanner);
                    if (!controlador.equipos.containsKey(codigoE)) {
                        System.out.println("El Codigo de Equipo no existe");
                        continue;
                    }
                    codigoD = val.leerdato("Ingrese el codigo del doctor a eliminar: ", scanner);
                    if (!controlador.doctores.containsKey(codigoD)) {
                        System.out.println("El Codigo de doctor no existe");
                        continue;
                    }
                    eliminarDoctor(codigoE, codigoD);
                    break;
                case 5:
                    ListarDoctores();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Opcion invalida, intentelo de nuevo.");
            }
        }
    }

    public static void crearDoctor(Validador val, String codigoE, Scanner sc) {
        while (true) {
            Team eq = controlador.equipos.get(codigoE);
            String id = val.leerdato("Ingresa el id del doctor: ", sc);
            if (controlador.doctores.containsKey(id)) {
                System.out.println("Doctor ya existe");
                continue;
            }
            Doctor doctor = new Doctor();

            doctor.setId(id);
            doctor.setNombre(val.leerdato("Digita el nombre del doctor: ", sc));
            doctor.setApellido(val.leerdato("Digite Apellido del doctor: ", sc));
            doctor.setEdad(val.leerNumero("Digite edad del doctor: ", sc));
            doctor.setTitulo(val.leerdato("Digite el titulo del doctor", sc));
            doctor.setExpYear(val.leerNumero("Digite los Años de experiencia", sc));

            eq.getLstMasajistas().add(doctor);
            controlador.doctores.put(id, doctor);
            return;
        }
    }

    public static void buscarDoctor(String codeD) {
        Doctor doctor = controlador.doctores.get(codeD);
        System.out.println("Doctor: " + codeD);
        System.out.println("Nombre: " + doctor.getNombre() + ", Apellido: " + doctor.getApellido() + ", Edad: "
                + doctor.getEdad() + ", Titulo: " + doctor.getTitulo() + ", Años de Experiencia: "
                + doctor.getExpYear());
    }

    public static void eliminarDoctor(String codeE, String codeD) {
        controlador.doctores.remove(codeD);

        Team equipo = controlador.equipos.get(codeE);
        for (Doctor item : equipo.getLstMasajistas()) {
            if(item.getId().equals(codeD)){
                equipo.getLstMasajistas().remove(item);
                break;
            }
        }
        System.out.println("Doctor eliminado con exito.");
    }

    public static void ListarDoctores(){
        Enumeration<String> llaves = controlador.doctores.keys();
        String llave = "";
        if (!llaves.hasMoreElements()) {
            System.out.println(">> No hay doctores para mostrar, Inserte Doctores");
            return;
        }
        System.out.println("Doctores");
        while (llaves.hasMoreElements()) {
            llave = String.valueOf(llaves.nextElement());
            Doctor doctor = controlador.doctores.get(llave);
            System.out.println("Id: " + doctor.getId()+ ", Nombre: " + doctor.getNombre() + ", Apellido: " + doctor.getApellido() + ", Edad: "
                + doctor.getEdad() + ", Titulo: " + doctor.getTitulo() + ", Años de Experiencia: "
                + doctor.getExpYear());
        }       
    }

    
    public static void actualizarDoctor(Validador val, String codigoD, Scanner sc){
        Doctor doctor = controlador.doctores.get(codigoD);
        
        System.out.println("Doctor: " + codigoD);
        System.out.println("Campos a actualizar");
        System.out.println("1. Nombre");
        System.out.println("2. Apellido");
        System.out.println("3. Edad");
        System.out.println("4. Titulo");
        System.out.println("5. Años de Experiencia");

        Integer choice = Integer.parseInt(val.leerdato("Elija una opcion: ", sc));
        System.out.println("\n");

        switch (choice) {
            case 1:
                System.out.println("Campo actual: " + doctor.getNombre());
                doctor.setNombre(val.leerdato("Digite el nuevo nombre: ",sc));
                System.out.println("Nombre actualizado correctamente");
                buscarDoctor(codigoD);
                break;
            case 2:
                System.out.println("Campo actual: " + doctor.getApellido());
                doctor.setApellido(val.leerdato("Digite el nuevo apellido: ",sc));
                System.out.println("Apellido actualizado correctamente");
                buscarDoctor(codigoD);                
                break;
            case 3:
                System.out.println("Campo actual: " + doctor.getEdad());
                doctor.setEdad(val.leerNumero("Digite la nueva edad: ",sc));
                System.out.println("Edad actualizada correctamente");
                buscarDoctor(codigoD);
                break;
            case 4:
                System.out.println("Campo actual: " + doctor.getTitulo());
                doctor.setTitulo(val.leerdato("Digite el nuevo Titulo: ",sc));
                System.out.println("Titulo actualizdo correctamente");
                buscarDoctor(codigoD);
                break;
            case 5:
                System.out.println("Campo actual: " + doctor.getExpYear());
                doctor.setExpYear(val.leerNumero("Digite nuevo campo de años de experiencia: ",sc));
                System.out.println("Posicion actualizada correctamente");
                buscarDoctor(codigoD);
                break;    
            default:
                System.out.println("Opcion no disponible");
                break;
        }
    }
}
