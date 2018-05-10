package getcountry

import org.junit.Assert.assertEquals
import org.junit.Test


internal class GetCountryTest {

    @Test
    fun getTestWithWrongLocation() {
        assertEquals(ArrayList<IResultCountry>(),get("location"))
    }

    @Test
    fun getTestWithEmptyLocation() {
        assertEquals(ArrayList<IResultCountry>(),get(""))
    }

    @Test
    fun getTestWithRussiaTatarstanKazanLocation() {
        val result = get("Russia, Tatarstan, Kazan")
        assertEquals("Russia",result[0].country)
        assertEquals("Tatarstan",result[0].region)
        assertEquals("Kazan",result[0].city)
    }
}