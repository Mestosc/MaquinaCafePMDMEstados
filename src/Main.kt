//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
//    repeat(12) {
//        Datos.monedas = 12.34
//        println("Haciendo cafe numero $it")
//        MaquinaCafe.hacerCafe(Cafe(1.0, 1, TiposCafe.CAPPUCCINO))
 //   } // Ahora si funciona si hago el cafe 12 veces me dice dos veces la parte del fallo
    menu()
}
fun menu() {
    while (true) {
        println("---- Bienvenido a la maquina de cafe ----")
        println("Digame el tipo de cafe que desea: ")
        val cafes = mapOf(
            "Chocolate" to Cafe(1.23, 5, TiposCafe.CHOCOLATE),
            "Cappuccino" to Cafe(1.50, 2, TiposCafe.CAPPUCCINO),
            "Descafeinado" to Cafe(1.00, 1, TiposCafe.DESCAFEINADO)
        )
        cafes.forEach { (nombre, cafe) -> println("$nombre: ${cafe.precio}€") }
        print("Dime tu eleccion de cafe (escribe salir para detener programa): ")
        var tipoCafe = readlnOrNull()?.trim() ?: ""
        if (tipoCafe.equals("salir", ignoreCase = true)) {
            println("Saliendo del programa...")
            return
        }
        tipoCafe = (tipoCafe.take(1).uppercase() + tipoCafe.substring(1).lowercase())
        val cafe = cafes[tipoCafe] ?: Cafe(0.0, 0, TiposCafe.DESCAFEINADO)
        if (cafe.precio == 0.0) {
            println("Tipo de cafe no valido, se detendra el programa")
            return
        }
        println("Ha seleccionado: $tipoCafe")
        println("Su precio es: ${cafe.precio}€")
        print("Introduzca las monedas: ")
        val monedasInput = readlnOrNull()?.trim() ?: "0"
        val monedas = monedasInput.toDoubleOrNull() ?: 0.0
        Datos.monedas = monedas
        MaquinaCafe.hacerCafe(cafe)
    }
}