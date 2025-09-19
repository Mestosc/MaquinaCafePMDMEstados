sealed class EstadosMaquinas {
    object Idle : EstadosMaquinas()
    object preparandoCafe : EstadosMaquinas()
    data class sirviendoCafe(val marca: String) : EstadosMaquinas()
    data class fallo(val error: String) : EstadosMaquinas()
}