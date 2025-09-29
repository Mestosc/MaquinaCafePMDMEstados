interface EntrarCafe {
    /**
     * Funcion de entrada en estado para una maquina de estado, pasandole la informacion que necesita cada estado como el [cafe] y las [monedas] que se le han introducido
     */
    fun onEnter(cafe: Cafe, monedas: Double)
}