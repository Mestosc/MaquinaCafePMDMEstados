/**
 * Los estados de la maquina de cafe
 * @property Idle Estado de espera, la maquina esta esperando
 * @property preparandoCafe Estado de preparacion el cafe esta preparandose
 * @property sirviendoCafe Estado de que el cafe esta siendo servido
 * @property fallo Ha habido alguna clase de fallo
 */
sealed class EstadosMaquinas {
    object Idle : EstadosMaquinas()
    object preparandoCafe : EstadosMaquinas()
    data class sirviendoCafe(val marca: String) : EstadosMaquinas()
    data class fallo(val error: String) : EstadosMaquinas()
}