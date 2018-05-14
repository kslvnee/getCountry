# getCountry 
[![Build Status](https://travis-ci.org/getcoderio/getCountry.svg?branch=master)](https://travis-ci.org/getcoderio/getCountry)
[![codecov](https://codecov.io/gh/getcoderio/getCountry/branch/master/graph/badge.svg)](https://codecov.io/gh/getcoderio/getCountry)
[![License](https://img.shields.io/github/license/getcoderio/getCountry.svg)](https://github.com/getcoderio/getCountry/blob/master/LICENSE)

The simple library that transforms String location into Country/Region/City


```kotlin
import getcountry.GetCountry.get

fun main(args: Array<out String>) {
    print(get("from Boston"))
    // Output:
    // [ResultCountry(country=USA, region=Massachusetts, city=Boston)]
    
    print(get("St. Petersburg"))
    // Output:
    // [ResultCountry(country=Russia, region=St. Petersburg, city=St. Petersburg), ResultCountry(country=USA, region=Florida, city=St. Petersburg)]
    
    print(get("St. Petersburg Fl"))
    // Output:
    // [ResultCountry(country=USA, region=Florida, city=St. Petersburg)]
}
```

## Dependency

Stable releases are hosted on [JCenter](https://bintray.com/bintray/jcenter).

```xml
<repository>
  <id>jcenter</id>
  <url>http://jcenter.bintray.com/</url>
</repository>
<!-- ... -->
<dependency>
  <groupId>com.github.getcoderio</groupId>
  <artifactId>GetCountry</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```