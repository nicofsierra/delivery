<%@include file="../../includes/head.jsp"%>


<body>
	<div class="container">

		<%@include file="../../includes/header.jsp"%>
		
		<section>
			
			<table class="table table-dark">
				<thead>
					<tr>
						<th scope="col">Telefono</th>
						<th scope="col">Domicilio</th>
						<th scope="col">Localidad</th>
						<th scope="col">Importe</th>
						<th scope="col">Reimprimir Comanda</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pedidos}" var="pedido">
						<tr>
							<td>${pedido.telefono}</td>
							<td>${pedido.domicilio}</td>
							<td>${pedido.localidad}</td>
							<td>${pedido.importe}</td>
							<td><a href="reimprimir-comanda?idPedido=${pedido.idPedido}"><button
										class="btn btn-primary">Reimprimir
									</button></a>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="alineado-centro">
				<a href="index"><button class="btn btn-primary">Volver</button></a>
			</div>
		</section>
		<div class="error">
			<h6>${error }</h6>
		</div>
	</div>
</body>
</html>