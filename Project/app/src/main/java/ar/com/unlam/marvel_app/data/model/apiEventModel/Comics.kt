import java.io.Serializable



data class Comics (

	val available : Int,
	val collectionURI : String,
	val items : List<Items>,
	val returned : Int
):Serializable