package vista

import Direccion
import Taller
import modelo.PasswordEncoder
import vista.strings.Strings

class TallerGUI:GeneralGUI {

    override fun startMenu(): Int? {
        println(Strings.mainMenuTaller)

        val input: String = readln()

        try {
            if (input.toInt() in 1..5){
                return input.toInt()
            } else return null
        }catch (_: Exception){
            return null
        }
    }

    override fun darseAlta(): Pair<Taller, Direccion>? {

        println(Strings.tallerInsertCIF)
        val cif = readln()
        println(Strings.insertNombre)
        val nombre = readln()
        println(Strings.insertPassword)
        val password = readln()

        val direccion = getDireccion()

        if (cif.isNotEmpty() && nombre.isNotEmpty() && password.isNotEmpty() && direccion != null){
            return Pair(Taller(cif,nombre,PasswordEncoder.encodePassword(password)),direccion)
        } else return null

    }

    fun obtenerCIF():String{
        println(Strings.tallerInsertCIF)
        val cif = readln()
        return cif
    }

    fun asignarPedido():Long{
        println(Strings.insertIDPedido)
        val idPedidoString = readln()
        var idPedido: Long = 0
        if (idPedidoString.toLongOrNull()!= null) {idPedido = idPedidoString.toLong()}
        return idPedido
    }


}