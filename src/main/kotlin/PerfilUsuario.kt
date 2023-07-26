class PerfilUsuario(val id: Int,
                    val nombres: String,
                    val apellidos: String,
                    val urlPhoto: String?,
                    val edad: Int,
                    val correo: String,
                    val biografia: String?,
                    var estado: String,
                    var hobbies: List<Hobby>) {

    fun AgregarHobby(hobby: Hobby) {
        hobbies = hobbies.plus(hobby)
    }
}