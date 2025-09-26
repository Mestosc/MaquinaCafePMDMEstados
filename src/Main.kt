//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val cafe = Cafe(1.23,5, TiposCafe.CHOCOLATE)
    repeat(10) { MaquinaCafe.hacerCafe(cafe, 2.0) }
}