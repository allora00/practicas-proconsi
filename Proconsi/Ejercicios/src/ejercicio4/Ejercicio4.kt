package ejercicio4

fun main(){
    println("Introduzca una opción:")
    println("1- Añadir cliente")
    println("2- Consultar cliente")
    println("3- Borrar cliente")
    println("4- Editar cliente")
    println("5- Listar clientes")
    var opcion: Int = readln().toInt()

    when(opcion){
        1 -> anadirCliente()
        2 -> consultarCliente()
        3 -> borrarCliente()
        4 -> editarCliente()
        5 -> listarCliente()
        else -> throw IllegalArgumentException("Introduzca un opcion disponible")
    }
}

fun anadirCliente(){
    println("cliente añadido")
}

fun consultarCliente(){
    println("cliente consultado")
}

fun borrarCliente(){
    println("cliente borrado")
}

fun editarCliente(){
    println("cliente editado")
}

fun listarCliente(){
    println("cliente listado")
}
