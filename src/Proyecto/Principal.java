package Proyecto;

import java.util.Scanner;

public class Principal {
    static Scanner entrada = new Scanner(System.in);
    final static int num = 4; // Numero de aeropuertos
    static Aeropuerto aeropuertos[] = new Aeropuerto[num];
    public static void main(String[] args){
        //Insertar datos de los aeropuertos
        insertarDatosAeropuerto(aeropuertos);
        menu();
    }
    public static void insertarDatosAeropuerto(Aeropuerto aero[]){
        aero[0] = new AeropuertoPublico(80000000, "Jorge Chavez", "Lima", "Perú");
        aero[0].insertarCompañia(new Compañia("AeroPerú"));
        aero[0].insertarCompañia(new Compañia("LATAM"));
        aero[0].getCompañia("AeroPerú").insertarVuelo(new Vuelo("IB20", "Lima", "México", 150.90, 150));
        aero[0].getCompañia("AeroPerú").insertarVuelo(new Vuelo("IB21", "Lima", "Buenos Aires", 180.99, 120));
        aero[0].getCompañia("LATAM").insertarVuelo(new Vuelo("FC12", "Lima", "Londres", 500.90, 180));
        aero[0].getCompañia("AeroPerú").getVuelo("IB20").insertarPasajero(new Pasajero("Alejandro", "20BGHP", "Peruana"));
        aero[0].getCompañia("AeroPerú").getVuelo("IB20").insertarPasajero(new Pasajero("Maria", "PJKL20", "Mexicana"));
        aero[0].getCompañia("LATAM").getVuelo("FC12").insertarPasajero(new Pasajero("Raul", "JH21KL", "Peruana"));

        aero[1] = new AeropuertoPublico(20000000, "Aeropuerto Bogota", "Bogota", "Colombia");
        aero[1].insertarCompañia(new Compañia("AirAmerica"));
        aero[1].insertarCompañia(new Compañia("VuelaBogota"));
        aero[1].getCompañia("AirAmerica").insertarVuelo(new Vuelo("AE030", "Bogota", "Lima", 150.90, 150));
        aero[1].getCompañia("AirAmerica").insertarVuelo(new Vuelo("AE031", "Bogota", "Lima", 180.99, 120));
        aero[1].getCompañia("VuelaBogota").insertarVuelo(new Vuelo("TC12", "Bogota", "Londres", 500.90, 180));
        aero[1].getCompañia("AirAmerica").getVuelo("AE030").insertarPasajero(new Pasajero("Zamir", "20BGHL", "Colombiano"));
        aero[1].getCompañia("AirAmerica").getVuelo("AE030").insertarPasajero(new Pasajero("Sebas", "PJKL21", "Mexicana"));
        aero[1].getCompañia("VuelaBogota").getVuelo("TC12").insertarPasajero(new Pasajero("Raul", "JH21KP", "Colombiano"));

        aero[2] = new AeropuertoPrivado("Aeropuerto Bogota", "Bogota", "Colombia");
        aero[2].insertarCompañia(new Compañia("AirColombia"));
        String empresas[] = {"Cobresol", "Anguila3"};
        ((AeropuertoPrivado)aero[2]).insertarEmpresas(empresas);
        aero[2].getCompañia("AirColombia").insertarVuelo(new Vuelo("AE025", "Bogota", "Lima", 150.90, 150));
        aero[2].getCompañia("AirColombia").getVuelo("AE025").insertarPasajero(new Pasajero("Alejandro", "20BGHL", "Colombiano"));

        aero[3] = new AeropuertoPublico(40000000, "Aeropuerto México", "México", "México");
        aero[3].insertarCompañia(new Compañia("AeroMexico"));
        aero[3].getCompañia("AeroMexico").insertarVuelo(new Vuelo("IB2040", "México", "Lima", 150.90, 150));
        aero[3].getCompañia("AeroMexico").insertarVuelo(new Vuelo("AIB2042", "México", "Lima", 180.99, 120));
        aero[3].getCompañia("AeroMexico").getVuelo("IB2040").insertarPasajero(new Pasajero("Zamir", "20BGHL", "Colombiano"));
    }
    public static void menu(){
        String nombreAeropuerto, nombreCompañia, origen, destino;
        int opcion;
        Aeropuerto aeropuerto;
        Compañia compañia;
        do{
            System.out.println("\t.:MENU:.");
            System.out.println("1. Ver aeropuertos gestionados(Públicos o privados");
            System.out.println("2. Ver empresas(Privado) o subvencion(Público");
            System.out.println("3. La lista de compañias de un aeropuerto");
            System.out.println("4. Lista de vuelos por compañia");
            System.out.println("5. Listar posibles vuelos de origen a destino");
            System.out.println("6. Salir");
            System.out.println("Opcion: ");
            opcion = entrada.nextInt();
            
            switch(opcion){
                case 1: 
                    System.out.println("");
                    mostrarDatosAeropuertos(aeropuertos);// Ver aeropuertos gestionados(Públicos o Privados)
                    break;
                case 2: 
                    System.out.println("");
                    mostrarPatrocinio(aeropuertos); // Ver empresas (Privado) o subvencion(Público)
                    break;
                case 3: // Listas compañias de un aeropuerto
                    entrada.nextLine();
                    System.out.print("Digite el nombre del aeropuerto: ");
                    nombreAeropuerto = entrada.nextLine();
                    aeropuerto = buscarAeropuerto(nombreAeropuerto, aeropuertos);
                    if(aeropuerto==null){
                        System.out.println("El aeropuerto no existe");
                    }
                    else{
                        mostrarCompañias(aeropuerto);
                    }
                    break;
                case 4: // Lista de vuelos por compañia
                    entrada.nextLine();
                    System.out.println("\nDigite el nombre del aeropuerto: ");
                    nombreAeropuerto = entrada.nextLine();
                    aeropuerto = buscarAeropuerto(nombreAeropuerto, aeropuertos);
                    if(aeropuerto==null){
                        System.out.println("El aeropuerto no exite");
                    }
                    else{
                        System.out.println("Digite el nombre de la compañia: ");
                        nombreCompañia = entrada.nextLine();
                        compañia = aeropuerto.getCompañia(nombreCompañia);
                        if(compañia==null){
                            System.out.println("La compañia no existe");
                        }
                        else{
                            mostrarVuelos(compañia);
                        }
                    }
                    break;
                case 5: // Listar posibles vuelos de Origen a Destino
                    entrada.nextLine();
                    System.out.println("\nDigite la ciudad de oirgen: ");
                    origen = entrada.nextLine();
                    System.out.println("\nDigite la ciudad de destino: ");
                    destino = entrada.nextLine();
                    mostrarVueloOrigenDestino(origen, destino, aeropuertos);
                    break;
                case 6: break;
                default: System.out.println("Error, se equivoco de opción");
            }
            System.out.println("");
        }while(opcion!=6);
    }
    public static void mostrarDatosAeropuertos(Aeropuerto aeropuertos[]){
        for(int i=0;i<aeropuertos.length;i++){
            if(aeropuertos[i] instanceof AeropuertoPrivado){
                System.out.println("Aeropuerto Privado");
                System.out.println("Nombre: "+aeropuertos[i].getNombre());
                System.out.println("Ciudad: "+aeropuertos[i].getCiudad());
                System.out.println("Pais: "+aeropuertos[i].getPais());
            }
            else{
                System.out.println("Aeropuerto Publico");
                System.out.println("Nombre: "+aeropuertos[i].getNombre());
                System.out.println("Ciudad: "+aeropuertos[i].getCiudad());
                System.out.println("Pais: "+aeropuertos[i].getPais());
            }
            System.out.println("");
        }
    }
    public static void mostrarPatrocinio(Aeropuerto aeropuertos[]){
        String empresas[];
        for(int i=0;i<aeropuertos.length;i++){
            if(aeropuertos[i] instanceof AeropuertoPrivado){ // Si el aeropuerto es privado
                empresas = ((AeropuertoPrivado)aeropuertos[i]).getListaEmpresas();
                System.out.println("Aeropuerto privado: "+aeropuertos[i].getNombre());
                System.out.println("Empresas: ");
                for(int j=0;j<empresas.length;j++){
                    System.out.println(empresas[j]);
                }
            }
            else{
                System.out.println("Aeropuerto publico: "+aeropuertos[i].getNombre());
                System.out.println("Subvencion: "+((AeropuertoPublico)aeropuertos[i]).getSubvencion());
            }
            System.out.println("");
        }
    }
    public static Aeropuerto buscarAeropuerto(String nombre, Aeropuerto aeropuertos[]){
        boolean encontrado = false;
        int i = 0;
        Aeropuerto aero = null;
        while((!encontrado)&&(i<aeropuertos.length)){
            if(nombre.equals(aeropuertos[i].getNombre())){
                encontrado = true;
                aero = aeropuertos[i];
            }
            i++;
        }
        return aero;
    }
    public static void mostrarCompañias(Aeropuerto aeropuerto){
        System.out.println("\nLas compañias de aeropuerto: "+aeropuerto.getNombre());
        for(int i=0;i<aeropuerto.getNumCompañia();i++){
            System.out.println(aeropuerto.getCompañia(i).getNombre());
        }
    }
    public static void mostrarVuelos(Compañia compañia){
        Vuelo vuelo;
        System.out.println("\nLos vuelos de la compañia: "+compañia.getNombre());
        for(int i=0;i<compañia.getNumVuelo();i++){
            vuelo = compañia.getVuelo(i);
            System.out.println("Identificador: "+vuelo.getIdentificador());
            System.out.println("Ciudad origen: "+vuelo.getCiudadOrigen());
            System.out.println("Ciudad destino: "+vuelo.getCiudadDestino());
            System.out.println("Precio: $"+vuelo.getPrecio());
            System.out.println("");
        }
    }
    public static Vuelo[] buscarVuelosOrigenDestino(String origen, String destino, Aeropuerto aeropuertos[]){
        Vuelo vuelo = null;
        int contador = 0;
        Vuelo listaVuelos[];

        for(int i=0;i<aeropuertos.length;i++){ //Recorremos los aeropuertos
            for(int j=0;j<aeropuertos[i].getNumCompañia();j++){ // Recorremos las compañias
                for(int k=0;k<aeropuertos[i].getCompañia(j).getNumVuelo();k++){ // Recorremos los vuelos
                    vuelo = aeropuertos[i].getCompañia(j).getVuelo(k);
                    if((origen.equals(vuelo.getCiudadOrigen()))&&(destino.equals(vuelo.getCiudadDestino()))){
                        contador++;
                    }
                }
            }
        }
        listaVuelos = new Vuelo[contador];
        int q = 0;
        for(int i=0;i<aeropuertos.length;i++){ //Recorremos los aeropuertos
            for(int j=0;j<aeropuertos[i].getNumCompañia();j++){ // Recorremos las compañias
                for(int k=0;k<aeropuertos[i].getCompañia(j).getNumVuelo();k++){ // Recorremos los vuelos
                    vuelo = aeropuertos[i].getCompañia(j).getVuelo(k);
                    if((origen.equals(vuelo.getCiudadOrigen()))&&(destino.equals(vuelo.getCiudadDestino()))){
                        listaVuelos[q] = vuelo;
                        q++;
                    }
                }
            }
        }
        return listaVuelos;
        
    }
    public static void mostrarVueloOrigenDestino(String origen, String destino, Aeropuerto aeropuertos[]){
        Vuelo vuelos[];
        vuelos = buscarVuelosOrigenDestino(origen, destino, aeropuertos);
        if(vuelos.length==0){
            System.out.println("No existen vuelos de esas ciudad oirgen a destino");
        }
        else{
            System.out.println("Vuelos encontrados: \n");
            for(int i=0;i<vuelos.length;i++){
                System.out.println("Identificador: "+vuelos[i].getIdentificador());
                System.out.println("Ciudad Origen: "+vuelos[i].getCiudadOrigen());
                System.out.println("Ciudad Destino: "+vuelos[i].getCiudadDestino());
                System.out.println("Precio: $"+vuelos[i].getPrecio());
                System.out.println("");  
            }
        }
    }
}
