package ar.com.unlam.marvel_app.data.model.apiModel

import Series
import Stories
import Thumbnail
import Urls

data class ResultsEvent (

    val id : Int,
    var title : String,
    val description : String,
    val resourceURI : String,
    val urls : List<Urls>,
    val modified : String,
    val start : String,
    val end : String,
    val thumbnail : Thumbnail,
    val creators : Creators,
    val characters : Characters,
    val stories : Stories,
    val comics : Comics,
    val series : Series,
    val next : Next,
    val previous : Previous
)