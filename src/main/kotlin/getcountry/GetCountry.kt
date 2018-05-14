package getcountry

import com.google.common.io.Resources
import com.google.gson.GsonBuilder
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import java.util.regex.Pattern
import java.util.ArrayList

/**
 * List of all countries with regions and cities
 */
val countries : List<Country> = getOriginalCountries()

/**
 * This method return the list of all countries
 * Countries stored in resources/countries
 * Every country presented in form of class Country
 *
 * @return A list of {@link Country <tt>Country</tt>} objects
 * @see Country
 */
fun getOriginalCountries() : List<Country> {
    val countries : MutableList<Country> = ArrayList()
    PathMatchingResourcePatternResolver()
            .getResources("countries/*.json")
            .toList()
            .forEach {
                countries.add(
                        GsonBuilder().create().fromJson(Resources.toString(it.url, Charsets.UTF_8),
                        Country::class.java)
                ) }
    return countries
}

/**
 * This method tries to determine the Country/Region/City names
 * based on string location.
 *
 * @return List<IResultCountry> the list of all found country/region/city names
 * @see IResultCountry
 */
fun get(location:String):List<IResultCountry> {
    if (location.isEmpty()) {
        return ArrayList()
    }

    val midResults = ArrayList<ResultCountry>()

    for (pCountry in countries) {

        for (countrySynonym in pCountry.synonims) {
            val pattern = Pattern.compile("(\\W|^)" + countrySynonym.toLowerCase() + "(\\W|$)")
            val m = pattern.matcher(location.toLowerCase())
            if (m.find()) {
                midResults.add(ResultCountry(pCountry.name))
                break
            }

        }

        for (pRegion in pCountry.regions) {

            for (regionSynonym in pRegion.synonims) {
                val pattern = Pattern.compile("(\\W|^)" + regionSynonym.toLowerCase() + "(\\W|$)")
                val m = pattern.matcher(location.toLowerCase())
                if (m.find()) {
                    midResults.add(ResultCountry(pCountry.name, pRegion.name))
                    break
                }
            }

            for (pCity in pRegion.cities) {
                for (citySynonym in pCity.synonims) {
                    val pattern = Pattern.compile("(\\W|^)" + citySynonym.toLowerCase() + "(\\W|$)")
                    val m = pattern.matcher(location.toLowerCase())
                    if (m.find()) {
                        midResults.add(ResultCountry(pCountry.name, pRegion.name, pCity.name))
                        break
                    }

                }
            }
        }

    }

    val results = ArrayList<IResultCountry>()
    for (midResult in midResults) {
        var found = false
        for (result in results) {
            if (result.country.equals(midResult.country)) {
                if (midResult.region.isNotEmpty()) {
                    result.region = midResult.region
                }
                if (midResult.city.isNotEmpty()) {
                    result.city = midResult.city
                }
                result.foundNubmer += 1
                found = true
            }
        }
        if (!found) {
            results.add(midResult)
        }
    }
    results.sortByDescending { it.foundNubmer }
    val maxElement = results.maxBy { it.foundNubmer }

    return results.filter { s -> s.foundNubmer == maxElement!!.foundNubmer }
}
