package ejercicio7

fun main(){
    var numero: Int?
    println("Introduzca un numero")
    do{
        val entrada = readlnOrNull()
        numero = entrada?.toIntOrNull()
        if (numero == null || numero < 0) {
            println("Debes introducir un numero positivo")
        }
    } while (numero == null || numero < 0)

    if(kaprekar(numero)){
        println("Es un numero valido kaprekar")
    }else{
        println("No es un numero valido kaprekar")
    }

}

fun kaprekar(numero: Int): Boolean{
    if (numero == 0 || numero == 1) {
        return true
    }
    val cuadrado = (numero.toLong() * numero.toLong()).toString()
    val longitudCuadrado = cuadrado.length

    for (i in 1 until longitudCuadrado) {
        val izquierdaStr = cuadrado.substring(0, i)
        val derechaStr = cuadrado.substring(i)

        val izquierda = izquierdaStr.toLong()
        val derecha = derechaStr.toLong()

        if (derecha == 0L) {
            continue
        }

        if (izquierda + derecha == numero.toLong()) {
            return true
        }
    }

    return false
}