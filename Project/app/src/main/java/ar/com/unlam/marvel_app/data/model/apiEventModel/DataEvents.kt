
data class DataEvents (

	val offset : Int,
	val limit : Int,
	val total : Int,
	val count : Int,
	val results : List<ResultsEvent>
)