<%@page import="ecom.service.BookPOJO"%>
<%@page import="java.util.List"%>
<%@taglib uri="/WEB-INF/jstl/c.tld" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>eCom une nouvelle façon de lire</title>
<link rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/panier.js"></script>
</head>
<body>
<header>
<img class="logoGauche" alt="" src="images/eComOrange.png"/>
<img class="logoDroit" alt="" src="images/eComOrange.png"/><p>eCom la CENTRALE de livres informatique</p>
</header>
<nav><ul>
<li><a href="">ACCUEIL</a></li>
<li><a href="">SYSTEME</a></li>
<li><a href="">LANGAGES</a></li>
<li><a href="">BASE DE DONNEES</a></li>
<li><a href="">ADMINISTRATION</a></li>
</ul></nav>
	<div class="caddie">
	<section>
		<article>
			<form name="fAddBook" action="addToCart" method="post">
			<table>
				<tr>
					<th class="id">ID</th>
					<th>Image</th>
					<th>Nom</th>
					<th class="description">Description</th>
					<th class="prix">Prix</th>
					<th>Quantit&eacute;</th>
					<th>Ajouter au panier</th>
				</tr>
				<c:forEach var="book" items="${modele}" varStatus="pStatus">
					<tr>
						<td class="id">${book.getKey()}</td>
						<td><img alt="couverture ${book.getName()}" src="images/${book.getImage()}"></td>
						<td>${book.getName()}</td>
						<td class="description">${book.getDescription()}</td>
						<td class="prix">${book.getPrice()}&euro;</td>
						<td><input id="${book.getKey()}" type="number" name="nombre" value="1"></td>
						<td><a onClick="addToCart(${book.getKey()})"><img src="images/cart.png"></a></td>
					</tr>
				</c:forEach>
			</table>
			</form>
		</article>
		<aside>
			<table id="panier" style="visibility:block;">
				<tr>
					<th colspan="4">Votre panier</th>
				</tr>
				<tr>
					<th>Nom</th>
					<th>Qt&eacute;</th>
					<th>Prix unitaire</th>
					<th>Sous total</th>
				</tr>
				<c:forEach var="cartBooks" items="${cartBooks}">
					<tr>
						<td>${cartBooks.getName()}</td>
						<td>${cartBooksNumber}[cartBooks.index]</td>
						<td>${cartBooks.getPriceByQty()}</td>
						<td>${cartBooks.getPriceByQty(${cartBooksNumber}[cartBooks.index])}&euro;</td>
					</tr>
				</c:forEach>
				<tr>
					<th colspan="2">Sous total</th>
					<td colspan="2">${totalPrice}&euro;</td>
				</tr>
				<tr>				
					<th colspan="2">Livraison</th>
					<td colspan="2">${delivery}&euro;</td>
				</tr>
				<tr>
					<th colspan="2">Total</th>
					<td colspan="2">${totalPriceDelivery}&euro;</td>
				</tr>				
			</table>
			<p class="bnt">
			<form name="fOrderCart" action="orderCart" method="post">
				<input type="button" onclick="fOrderCart.submit();" value="Payer">
			</form>
			</p>
		</aside>
	</section>
		</div>
<p id="footer">Copyright 2016 - La boutique du programmeur</p>
</body>
</html>