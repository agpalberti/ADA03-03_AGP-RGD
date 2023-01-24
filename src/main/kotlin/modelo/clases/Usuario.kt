package modelo.clases

import Direccion

abstract class Usuario(
    val id: String, open val nombre: String,
    open val password: String, direccion: Direccion? = null
) {


}