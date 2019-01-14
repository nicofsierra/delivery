<%@include file="../../includes/head.jsp"%>


<body>
	<div class="container">

		<%@include file="../../includes/header.jsp"%>
		<c:set var="cliente" value="${cliente}" scope="session" />
		<section>
			<div class="recuadro">
				<div class="col-md-6">
					<label> Telefono </label> <input class="form-control" type="text"
						disabled value="${cliente.telefono}" />
				</div>
				<div class="col-md-6">
					<label> Nombre </label> <input class="form-control" type="text"
						disabled value="${cliente.nombre}" />
				</div>
				<div class="col-md-6">
					<label> Direccion</label> <input class="form-control" type="text"
						disabled value="${cliente.calle}" />
				</div>
				<div class="col-md-6">
					<label> Localidad </label> <input class="form-control" type="text"
						disabled value="${cliente.localidad}" />
				</div>
			</div>
			<form action="imprimir-comanda" method="GET">
				<table class="table table-dark">
					<thead>
						<tr>
							<th scope="col">ID Producto</th>
							<th scope="col">Descripcion</th>
							<th scope="col">Cantidad</th>
							<th scope="col">Precio</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pedidosProductos}" var="pedidoProducto">
							<tr>
								<td>${pedidoProducto.productoId}</td>
								<td>${pedidoProducto.nombre}</td>
								<td>${pedidoProducto.cantidad}</td>
								<td>${pedidoProducto.precio}</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td class="total">TOTAL:</td>
							<td class="total">${pedido.precio }</td>
							<td class="total">PAGA CON:</td>
							<td class="total"><input type="text" name="vuelto"
								class="form-control" /></td>
						</tr>
				</table>

				<input type="hidden" value="${cliente.telefono }" name="clienteId" />
				<input type="hidden" value="${pedido.id}" name="pedidoId" />
				<div class="row">
					<div class="col-md-12">
						<label>Observaciones: </label> <input type="text" name="obs"
							class="form-control" />
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-4 text-center">
						<button class="btn btn-primary" type="submit">Imprimir
							Comanda</button>
					</div>
					<div class="col-md-4 text-center">
						<a
							href="volver-preparar-pedido?clienteId=${cliente.telefono}&pedidoId=${pedido.id}"><button
								class="btn btn-primary">Volver</button></a>
					</div>
				</div>
			</form>


		</section>
		<div class="error">
			<h6>${error }</h6>
		</div>
	</div>
</body>
</html>