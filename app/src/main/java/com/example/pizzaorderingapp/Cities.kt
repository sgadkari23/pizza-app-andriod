package com.example.pizzaorderingapp
/*
* Application Name: Pizza App
* Name : Supriya Gadkari & Raj Shahu
* Group No: 3
* Description: Cities class
* */
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

class Cities {
    val cities = hashMapOf<String, City>()
    constructor()
    {
        val austinBounds = LatLngBounds(
            LatLng((30.2452), -97.7854),  // SW bounds
            LatLng((30.3207), -97.6758) // NE bounds
        )
        val austin = City("Austin", austinBounds, mutableListOf("ChIJO2sMhHi1RIYRFgYG7hgl-6Y", "ChIJMX5-2-m0RIYRcp3AoYaNE9w", "ChIJVaujmae1RIYRpGyZcs9VBao", "ChIJu96UVvy0RIYRCPxWa4TSr6o", "ChIJG0-Fm3-1RIYRQgWzWeY1ljQ"))
        this.cities.put("Austin", austin)

        val dallas = City("Dallas", austinBounds, mutableListOf("ChIJg9LVxoWZToYROYcMz4NT_Cw", "ChIJsWWnNbGeToYRWWt5hAcv6qw", "ChIJEWJ0wZWYToYRS-2LzR8pnPo", "ChIJaUqgjNSeToYRqpjno_vCILg", "ChIJI7ICkqCeToYROukGO2SczzI"))
        this.cities.put("Dallas", dallas)

        val sanAntonio = City("San Antonio", austinBounds, mutableListOf("ChIJ51lHiXZZXIYRHrpTMRBFhiY", "ChIJvdDWrqxYXIYRrvbNKLrCjzA", "ChIJq6oGNN71XIYRcKOIOnDFxQ8", "ChIJQ5ukQLlYXIYRJiHwC1Shxs8", "ChIJaVRYcANZXIYR6BC3KzPrJZo"))
        this.cities.put("San Antonio", sanAntonio)

        val boston = City("Boston", austinBounds, mutableListOf("ChIJWSwS9nZ644kR3jrjaLDWIAY", "ChIJ5Sk27_V544kRqLuOmhg2w_g", "ChIJ5ZsSxj1644kRWieCXebjoZ4", "ChIJlQwTsw9644kRVRk86P0nRBg", "ChIJGwXlxvV544kR6qP6Pr2EENU"))
        this.cities.put("Boston", boston)

        val houston = City("Houston", austinBounds, mutableListOf("ChIJ6UvzkMO_QIYRUFtNOhTLO-w", "ChIJvc6mQYzAQIYRRXAx2rEukig", "ChIJ70-JyG6_QIYR7dQxVzxJKEQ", "ChIJp5v6VCW_QIYRsBulbXJDew8", "ChIJNSAhnWq_QIYRXJ32NTjrcwU"))
        this.cities.put("Houston", houston)
    }

    fun getCity(name:String): City?
    {
        return this.cities.get(name)
    }
}

class City {
    var latLngBounds:LatLngBounds? = null
    var name:String? = null
    var pizzaStoreIds = mutableListOf<String>()

    constructor(name:String, latLngBounds:LatLngBounds, pizzaStoreIds:MutableList<String>)
    {
        this.name = name
        this.latLngBounds = latLngBounds
        this.pizzaStoreIds = pizzaStoreIds
    }
}