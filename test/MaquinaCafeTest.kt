import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class MaquinaCafeTest {
    private val outContent = ByteArrayOutputStream()
    private val originalOut = System.out

    @BeforeEach
    fun setUpStreams() {
        System.setOut(PrintStream(outContent))
        MaquinaCafe.clean()
    }

    @AfterEach
    fun restoreStreams() {
        System.setOut(originalOut)
    }

    @Test
    fun hacerCafeConDineroExactoSirveCafeYNoDaVuelta() {
        val cafe = Cafe(1.5, 2, TiposCafe.CAPPUCCINO)
        MaquinaCafe.hacerCafe(cafe, 1.5)
        val output = outContent.toString()
        assertTrue(output.contains("Empezando a preparar cafe"))
        assertTrue(output.contains("Preparando cafe"))
        assertTrue(output.contains("Sirviendo CAPPUCCINO"))
        assertTrue(output.contains("Cafe servido"))
        assertFalse(output.contains("de vuelta"))
    }

    @Test
    fun hacerCafeConDineroSuficienteDaVuelta() {
        val cafe = Cafe(1.0, 1, TiposCafe.DESCAFEINADO)
        MaquinaCafe.hacerCafe(cafe, 2.0)
        val output = outContent.toString()
        assertTrue(output.contains("Le corresponden 1,00€ de vuelta") || output.contains("Le corresponden 1.00€ de vuelta"))
    }

    @Test
    fun hacerCafeConDineroInsuficienteNoPrepara() {
        val cafe = Cafe(2.0, 1, TiposCafe.CHOCOLATE)
        MaquinaCafe.hacerCafe(cafe, 1.0)
        val output = outContent.toString()
        assertTrue(output.contains("No tienes suficiente dinero"))
        assertFalse(output.contains("Preparando cafe"))
    }

    @Test
    fun hacerCafeConFiltroSucioFalla() {
        val cafe = Cafe(1.0, 1, TiposCafe.CAPPUCCINO)
        repeat(10) { MaquinaCafe.hacerCafe(cafe, 1.0) }
        outContent.reset()
        MaquinaCafe.hacerCafe(cafe, 1.0)
        val output = outContent.toString()
        assertTrue(output.contains("Fallo: Filtro sucio"))
    }

    @Test
    fun limpiarFiltroPermiteHacerCafeDeNuevo() {
        val cafe = Cafe(1.0, 1, TiposCafe.CAPPUCCINO)
        repeat(11) { MaquinaCafe.hacerCafe(cafe, 1.0) }
        MaquinaCafe.clean()
        outContent.reset()
        MaquinaCafe.hacerCafe(cafe, 1.0)
        val output = outContent.toString()
        assertTrue(output.contains("Empezando a preparar cafe"))
        assertTrue(output.contains("Cafe servido"))
    }
}
