package vista.strings

/**Lista de strings utilizados por nuestra interfaz gráfica*/
object Strings {


    //LOGIN
    val identifyType = "\nBienvenido, identificate:\n" +
            "1. Cliente\n" +
            "2. Taller\n" +
            "3. Registrarse como cliente\n" +
            "4. Registrarse como taller\n" +
            "5. Salir del programa\n"

    val clientInsertDNI = "\nIntroduzca su DNI"
    val tallerInsertCIF = "\nIntroduzca su CIF"

    val insertPassword = "\nIntroduzca su contraseña"

    //CLIENTE
    val mainMenuCliente = "\nBienvenido a la BBDD querido Cliente. Elija una opción:\n" +
            "1. Dar de alta un pedido\n" +
            "2. Consultar pedidos realizados\n" +
            "3. Consultar talleres relacionados\n"+
            "4. Volver atrás\n"

    val insertClientEdad = "Introduzca su edad: \n"
    val insertClientCiudad = "Introduzca su ciudad: \n"
    val insertPedidoDescripcion = "Introduzca la descripción del pedido: \n"



    //TALLER

    val mainMenuTaller = "\nBienvenido a la BBDD querido Taller. Elija una opción:\n" +
            "1. Consultar pedidos sin asignar\n" +
            "2. Consultar pedidos asignados\n" +
            "3. Consultar clientes relacionados\n" +
            "4. Asignarse un pedido\n" +
            "5. Volver atrás\n"

    val insertTallerCIF = "Introduzca su CIF: \n"

    val insertIDPedido = "Introduce el id del pedido: \n"


    //DIRECCION
    //calle numero codigo postal
    val insertDireccionCalle = "Introduzca su calle: \n"
    val insertDireccionNumero = "Introduzca su número: \n"
    val insertDireccionCP = "Introduzca su codigo postal: \n"


    //GENERICO
    val altaExito = "Dado de alta con éxito \n"
    val insertDireccion = "Introduzca su dirección: \n"
    val insertNombre = "Introduzca su nombre: \n"

    val incorrectInput = "\nLa entrada ha sido incorrecta"

    val dataNotFound = "\nNo se ha encontrado la entrada"

    val altaError = "Error en el alta \n"

    val exit = "\nAdiós."


}