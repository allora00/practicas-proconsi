package ejercicio5

fun main(){
    val stdIn = "Bread$$##12.5$$##10";
    val valores = ItemSeparator(stdIn)

    println("--- CADENA ORIGINAL ---\n$stdIn\n")
    println("-Nombre: " + valores.name)
    println("-Precio: " + valores.price)
    println("-Cantidad: " + valores.quantity)
}

class ItemSeparator(val valores: String){
    private val aux: List<String> = valores.split("$$##")
    var name: String = aux[0]
    var price: Double = aux[1].toDouble()
    var quantity: Int = aux[2].toInt()
}