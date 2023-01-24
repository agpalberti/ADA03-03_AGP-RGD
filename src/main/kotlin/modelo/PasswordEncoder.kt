package modelo

class PasswordEncoder() {

    companion object {

        /** Encodes a password with a simple encoding
         * @param password The password that you want to encode
         * @return The password encoded
         * */
        fun encodePassword(password: String): String {
            var encodedPassword = ""

            password.forEach { encodedPassword += (it.code + 3).toChar() }

            return encodedPassword
        }

        /** Decodes a password encoded with this class
         * @param password The password that you want to decode
         * @return The password decoded
         * */
        fun decodePassword(password: String): String {
            var decodedPassword = ""

            password.forEach { decodedPassword += (it.code + -3).toChar() }

            return decodedPassword
        }
    }

}