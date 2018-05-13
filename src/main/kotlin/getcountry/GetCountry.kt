package getcountry


import com.google.common.base.MoreObjects
import com.google.common.io.Files
import com.google.common.io.Resources
import com.google.gson.GsonBuilder


import java.io.File
import java.io.InputStreamReader
import java.net.URI
import java.util.regex.Pattern
import java.util.ArrayList
import java.io.IOException
import java.net.URL


import java.nio.file.Paths
import java.util.stream.Collectors



/**
 * List of all countries with regions and cities
 */
val countries : List<Country> = getOriginalCountries()

/**
 * This method return the list of all countries
 * Countries stored in resources/countries
 * Every country presented in form of class Country
 *
 * @return List<Country> the list of all countries
 * @see Country
 */
fun getOriginalCountries() : List<Country> {


   // val listOfFiles = File(URI("src/main/resources/countries").path).listFiles()
    val countries : MutableList<Country> = ArrayList()
//    for (file in listOfFiles) {
//        countries.add(GsonBuilder().create().fromJson(file.readText(), Country::class.java))
//    }

  //  val url = Resources.getResource("countries/Russia.json")

//    val loader = MoreObjects.firstNonNull(Thread.currentThread().contextClassLoader, Resources::class.java.classLoader)
//    val urls = loader.getResources("countries/Russia.json").toList()
//

    //val text = Resources.toString(url, Charsets.UTF_8)

//    urls.forEach {
//        val text = Resources.toString(it, Charsets.UTF_8)
//        countries.add(GsonBuilder().create().fromJson(text, Country::class.java))
//    }

    val files = getResourceFolderFiles("countries")
    files.forEach { countries.add(GsonBuilder().create().fromJson(it.readText(), Country::class.java)) }




    // countries.add(GsonBuilder().create().fromJson(text, Country::class.java))

    //Files.fileTraverser().breadthFirst("").forEach()


    return countries
}

private fun getResourceFolderFiles(folder: String): Array<File> {
    val loader = Thread.currentThread().contextClassLoader
    val url = loader.getResource(folder)
    val path = url!!.path
    return File(path).listFiles()
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

    return results
}

