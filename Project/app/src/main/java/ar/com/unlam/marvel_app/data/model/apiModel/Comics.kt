package ar.com.unlam.marvel_app.data.model.apiModel

import Items
import java.io.Serializable



data class Comics (

    val available : Int,
    val collectionURI : String,
    var items : List<Items>,
    val returned : Int
):Serializable