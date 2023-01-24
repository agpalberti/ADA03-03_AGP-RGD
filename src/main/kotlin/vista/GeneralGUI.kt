package vista

import Direccion
import modelo.clases.Usuario
import vista.strings.Strings

interface GeneralGUI {

    fun startMenu(): Int?

    fun darseAlta(): Pair<Usuario,Direccion>?

    fun getDireccion():Direccion?{
        println(Strings.insertDireccionCalle)
        val calle: String = readln()
        println(Strings.insertDireccionNumero)
        val numeroString: String = readln()
        var numero: Int = 0
        if (numeroString.toIntOrNull() != null) {numero = numeroString.toInt()}
        println(Strings.insertDireccionCP)
        val cpString: String = readln()
        var cp : Int = 0
        if (cpString.toIntOrNull() != null) {cp = cpString.toInt()}

        if (calle.isNotEmpty()){
            return Direccion(calle = calle,numero = numero, codigopostal = cp)
        } else return null

    }

    fun printIncorrectInput(){
        println(Strings.incorrectInput)
    }

    fun dataNotFound(){
        println(Strings.dataNotFound)
    }

    fun printString(data: Usuario){
        println(data)
    }

    fun exit(){
        println(Strings.exit)
    }

    fun printAltaExito(){
        println(Strings.altaExito)
    }

    fun printAltaError(){
        println(Strings.altaError)
    }

    fun <T> printSet(set: Set<T>){
        set.forEach { println(it) }
    }



}