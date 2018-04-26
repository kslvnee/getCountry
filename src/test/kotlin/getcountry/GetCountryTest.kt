package getcountry

import org.junit.Assert.assertEquals
import org.junit.Test


internal class GetCountryTest {

    @Test
    fun getTest() {
        assertEquals("Hello A",get("A"))
    }
}