import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val usuarios: List<PerfilUsuario>
    val nelson = PerfilUsuario(
        1,
        "Nelson",
        "Escalante",
        null,
        18,
        "esc22046@uvg.edu.gt",
        "Chico Superduper cool",
        "Activo",
        listOf(Hobby("Cantar", "Crear melodias con la voz.", null))
    )
    val juan = PerfilUsuario(
        2,
        "Juan Pablo",
        "Escalante",
        null,
        16,
        "esc4@gmail.com",
        "Mi hermano",
        "Inactivo",
        listOf(Hobby("Violinista", "Tocar el violin.", null))
    )

    usuarios = listOf(nelson, juan)

    menu(usuarios)

}

fun menu(usuarios: List<PerfilUsuario>) {
    var updatedUsuarios = usuarios

    println("============================================")
    println("1. Crear perfil.")
    println("2. Buscar usuario.")
    println("3. Eliminar perfil.")
    println("4. Agregar hobby.")
    println("5. Salir del programa.")
    println("Seleccione la opción que desea realizar.")

    when (readln().toInt()) {
        1 -> {
            updatedUsuarios = updatedUsuarios.plus(newUser())
            menu(updatedUsuarios)
        }
        2 -> {
            searchUser(updatedUsuarios)
            menu(updatedUsuarios)
        }
        3 -> {
            val deletedUsers = delUser(updatedUsuarios)
            if (deletedUsers != null) {
                updatedUsuarios = deletedUsers
            }
            menu(updatedUsuarios)
        }
        4 -> {
            addHobby(updatedUsuarios)
            menu(updatedUsuarios)
        }
        5 -> {
            exitProcess(0)
        }
        else -> {
            println("Por favor, ingrese un número válido.")
            menu(updatedUsuarios)
        }
    }
}

fun newUser(): PerfilUsuario {
    println("Ingrese el ID del usuario.")
    val id = readln()
    println("Ingrese el nombre del usuario.")
    val name = readln()
    println("Ingrese el apellido del usuario.")
    val lastName = readln()
    println("Ingrese el url de una foto del usuario. Puede dejar este espacio en blanco.")
    val url = readln()
    println("Ingrese la edad del usuario.")
    val age = readln()
    println("Ingrese el correo del usuario.")
    val address = readln()
    println("Ingrese una biografía para el usuario. Puede dejar este espacio en blanco.")
    val bio = readln()
    println("Indique si el usuario se encuentra 'Activo', 'Pendiendte' o 'Inactivo'.")
    val state = readln()

    return PerfilUsuario(id.toInt(), name, lastName, url, age.toInt(), address, bio, state, listOf())
}

fun searchUser(users: List<PerfilUsuario>) {
    println("Presione '1' si quiere usar el ID o '2' si quiere usar los nombres.")
    when (readln().toInt()) {

        1 -> {
            println("Ingrese el ID del usuario.")
            val id = readln().toInt()

            for (i in users) {
                if (i.id == id) {
                    println("Este ID es del usuario ${i.nombres}.")
                    printUser(i)
                    return
                }
            }
            println("El usuario no ha sido encontrado.")
            return
        }

        2 -> {
            println("Ingrese el nombre y/o el apellido del usuario, separados por un espacio.")
            val line = readln()
            val names = line.split(" ")

            for (i in users) {
                if ((i.nombres in names) || (i.apellidos in names)) {
                    println("Este ID es del usuario ${i.nombres}.")
                    printUser(i)
                    return
                }
            }
            println("El usuario no ha sido encontrado.")
            return
        }
        else -> {
            println("Ingrese una opción válida.")
        }
    }
}

fun delUser(usuarios: List<PerfilUsuario>): List<PerfilUsuario>? {
    println("Ingrese el ID del usuario.")
    val id = readln().toInt()

    for (i in usuarios) {
        if (i.id == id) {
            println("Este ID es del usuario ${i.nombres}.")
            println("Eliminando al usuario.")
            return usuarios.minus(i)
        }
    }
    println("El usuario no ha sido encontrado.")
    return null
}

fun addHobby(users: List<PerfilUsuario>) {
    println("Ingrese el ID del usuario al que quiere añadirle un hobby.")
    val id = readln().toInt()
    println("Ingrese el titulo del hobby.")
    val title = readln()
    println("Ingrese una descripcion del hobby.")
    val desc = readln()
    println("Ingrese el url de una foto del hobby. Puede dejar este espacio en blanco.")
    val url = readln()

    val user = searchById(users, id)

    user?.AgregarHobby(Hobby(title, desc, url))

}

fun searchById(users: List<PerfilUsuario>, id: Int): PerfilUsuario? {
    for (i in users) {
        if (i.id == id) {
            println("Este ID es del usuario ${i.nombres}.")
            println("Eliminando al usuario.")
            return i
        }
    }
    println("El usuario no ha sido encontrado.")
    return null
}

fun printUser(user: PerfilUsuario) {
    println("Su ID es ${user.id}")
    println("Su nombre es ${user.nombres}")
    println("Su apellido es ${user.apellidos}")
    if (user.urlPhoto != null) {
        println("El url para su foto es ${user.urlPhoto}")
    }
    println("Su edad es ${user.edad}")
    println("Su correo es ${user.correo}")
    if (user.biografia != null) {
        println("Su biografía es ${user.biografia}")
    }
}