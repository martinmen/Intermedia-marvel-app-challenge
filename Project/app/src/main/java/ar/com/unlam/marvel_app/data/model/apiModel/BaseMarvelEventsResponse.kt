package ar.com.unlam.marvel_app.data.model.apiModel

data class BaseMarvelEventsResponse (

	val code : Int,
	val status : String,
	val copyright : String,
	val attributionText : String,
	val attributionHTML : String,
	val etag : String,
	val data : DataEvents
)