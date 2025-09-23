/**
 * DataClass que representa los cafes
 * @property precio el precio de los cafes
 * @property cantidadAzucar la cantidad de azucar del cafe
 * @property tipo el tipo de Cafe
 */
data class Cafe(var precio: Double, var cantidadAzucar: Int, var tipo: TiposCafe, var canitdadIngredientes: Int)
