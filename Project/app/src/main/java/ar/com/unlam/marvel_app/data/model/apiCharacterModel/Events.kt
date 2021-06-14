import java.io.Serializable

data class Events (
	val available : Int,
	val collectionURI : String,
	val items : List<Items>,
	val returned : Int
):Serializable