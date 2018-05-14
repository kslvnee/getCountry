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
        val result = get("Russia, Tatarstan, Kazan, Usa")
        assertEquals("Russia",result[0].country)
        assertEquals("Tatarstan",result[0].region)
        assertEquals("Kazan",result[0].city)
        assertEquals(1, result.size)
    }

    @Test
    fun getTestWithKazan() {
        val result = get("Russia")
        assertEquals("Russia",result[0].country)
    }


    @Test
    fun getTestWithUsaRussia() {
        val result = get("USA, Russia")
        assertEquals("Russia",result[0].country)
        assertEquals(2, result.size)
    }

    @Test
    fun getTestWithTatarstanCalifornia() {
        val result = get("Tatarstan, California")
        assertEquals("Russia",result[0].country)
    }

    @Test
    fun getTestWithTatarstanUSA() {
        val result = get("Tatarstan, USA")
        assertEquals("Russia",result[0].country)
    }

    @Test
    fun getTestWithNumbers() {
        val result = get("030, 123")
        assertEquals(ArrayList<IResultCountry>(),result)
    }

    @Test
    fun getTestWithUsa() {
        val result = get("USA")
        assertEquals("USA",result[0].country)
    }
}