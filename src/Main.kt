//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val cafe = Cafe(1.23,5, TiposCafe.CHOCOLATE)
    println("--- Encendiendo maquina ---")
    MaquinaCafe.hacerCafe(cafe,1.23)
    println("--- Intentando hacer café de nuevo --")
    MaquinaCafe.hacerCafe(cafe,0.10)
    println("--- Limpiando ---")
    MaquinaCafe.clean()
    println("--- Preparando cafe otra vez ---")
    MaquinaCafe.hacerCafe(cafe,4.23)
}