@file:JvmName("GetCountry2")

package getcountry

import com.google.gson.GsonBuilder
import java.io.File
import java.util.regex.Pattern
import javax.swing.plaf.synth.SynthLookAndFeel.getRegion
import java.util.ArrayList




val countries : List<Country> = getOriginalCountries()

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
                    val lowerCaseCity = citySynonym.toLowerCase()
                    val pattern = Pattern.compile("(\\W|^)$lowerCaseCity(\\W|$)")
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

    return results
}

fun getOriginalCountries() : List<Country> {
    val listOfFiles = File("src/main/resources/countries").listFiles()
    val countries : MutableList<Country> = ArrayList()
    for (file in listOfFiles) {
            countries.add(GsonBuilder().create().fromJson(file.readText(), Country::class.java))
    }
    //var d = countries.sortedBy { it.name }
    println(countries.size)
    return countries
}
