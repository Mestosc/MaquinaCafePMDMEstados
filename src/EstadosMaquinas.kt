/**
 * Los estados de la maquina de cafe
 * @property Idle Estado de espera, la maquina esta esperando
 * @property PreparandoCafe Estado de preparacion el cafe esta preparandose
 * @property SirviendoCafe Estado de que el cafe esta siendo servido
 * @property Fallo Ha habido alguna clase de fallo
 */
sealed class EstadosMaquinas {
    object Idle : EstadosMaquinas()
    object PreparandoCafe : EstadosMaquinas()
    data class SirviendoCafe(val marca: String) : EstadosMaquinas()
    data class Fallo(val error: String) : EstadosMaquinas()
}