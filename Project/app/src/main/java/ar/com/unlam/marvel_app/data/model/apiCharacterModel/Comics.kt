import java.io.Serializable


data class Comics2 (

	val available : Int,
	val collectionURI : String,
	val items : List<Items>,
	val returned : Int
):Serializable