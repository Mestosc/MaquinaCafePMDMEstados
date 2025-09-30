//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val cafe = Cafe(1.23,5, TiposCafe.CHOCOLATE)
    Datos.monedas = 2.23
    repeat(11) {
        Datos.monedas = 2.13
        MaquinaCafe.hacerCafe(cafe)
    }
}