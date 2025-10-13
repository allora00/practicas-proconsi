package ejercicio3

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun main(){

    println("Introduce una fecha (yyyy/MM/dd)")
    val fecha1 = readln()
    val fecha2 = readln()
    val formato = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    lateinit var fechaFormat1: LocalDate
    lateinit var fechaFormat2: LocalDate
    try{
        fechaFormat1 = LocalDate.parse(fecha1, formato)
        fechaFormat2 = LocalDate.parse(fecha2, formato)
    }catch(e:Exception){
        println(e.message)
    }

    diferencia(fechaFormat1, fechaFormat2)
    inicioFin(fechaFormat1, fechaFormat2)
    dias(fechaFormat1, fechaFormat2)
    semanas(fechaFormat1, fechaFormat2)
}

fun diferencia(fechaFormat1: LocalDate, fechaFormat2: LocalDate) {
    val diferenca = ChronoUnit.DAYS.between(fechaFormat1, fechaFormat2)
    println("Hay una diferencia de $diferenca dias")
}

fun inicioFin(fechaFormat1: LocalDate, fechaFormat2: LocalDate) {
    val inicio1 = LocalDate.of(fechaFormat1.year, 1, 1)
    val fin1 = LocalDate.of(fechaFormat1.year, 12, 31)
    val inicio2 = LocalDate.of(fechaFormat2.year, 1, 1)
    val fin2 = LocalDate.of(fechaFormat2.year, 12, 31)
    print("Inicio 1ºfecha: $inicio1     ")
    println("Fin 1ºfecha: $fin1")
    print("Inicio 2ºfecha: $inicio2     ")
    println("Fin 2ºfecha: $fin2")
}

fun dias(fechaFormat1: LocalDate, fechaFormat2: LocalDate) {
    val inicio1 = LocalDate.of(fechaFormat1.year, 1, 1)
    val dias1 = ChronoUnit.DAYS.between(inicio1, fechaFormat1)
    val inicio2 = LocalDate.of(fechaFormat2.year, 1, 1)
    val dias2 = ChronoUnit.DAYS.between(inicio2, fechaFormat2)
    println("Dias 1º fecha: $dias1  Dias 2º fecha: $dias2")
}

fun semanas(fechaFormat1: LocalDate, fechaFormat2: LocalDate) {
    val inicio1 = LocalDate.of(fechaFormat1.year, 1, 1)
    val dias1 = ChronoUnit.WEEKS.between(inicio1, fechaFormat1)
    val inicio2 = LocalDate.of(fechaFormat2.year, 1, 1)
    val dias2 = ChronoUnit.WEEKS.between(inicio2, fechaFormat2)
    println("Semanas 1º fecha: $dias1  Semanas 2º fecha: $dias2")
}