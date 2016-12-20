function addToCart(key){
	var numberOfBooks = document.getElementById(key).value;
	document.location.href = "addToCart?number="+numberOfBooks+"&bookId="+key;
}