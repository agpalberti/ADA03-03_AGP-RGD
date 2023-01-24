package vista

import vista.strings.Strings

class LoginGUI {

    fun identifyType():Int?{
        println(Strings.identifyType)

        val input: String = readln()

        try {
            return input.toInt()
        }catch (_: Exception){
            return null
        }
    }

    fun clientLogin():Pair<String,String>{

        println(Strings.clientInsertDNI)
        val dni: String = readln()

        println(Strings.insertPassword)
        val password: String = readln()

        return Pair(dni, password)
    }


    fun tallerLogin():Pair<String,String>{

        println(Strings.tallerInsertCIF)
        val cif: String = readln()

        println(Strings.insertPassword)
        val password: String = readln()

        return Pair(cif,password)
    }


}