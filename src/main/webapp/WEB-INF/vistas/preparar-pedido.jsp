
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
			<form action="procesar-pedido" method="GET" id="formulario">
				<div class="form-group">
					<div class="col-md-6">
						<label for="producto">Producto</label> <input type="text"
							class="form-control" placeholder="Producto" name="producto" />
					</div>
					<div class="col-md-6">
						<label for="cantidad">Cantidad</label> <input type="text"
							class="form-control" placeholder="Cantidad" name="cantidad" />
					</div>
				</div>
				<input type="hidden" name="clienteId" value="${cliente.telefono}">

				<div class="alineado-centro">
					<button class="btn btn-primary" type="submit">Agregar</button>

				</div>
			</form>
			<table class="table table-dark">
				<thead>
					<tr>
						<th scope="col">ID Producto</th>
						<th scope="col">Descripcion</th>
						<th scope="col">Cantidad</th>
						<th scope="col">Precio</th>
						<th scope="col">Borrar</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${productoCantidad}" var="pedidoProducto">
						<tr>
							<td>${pedidoProducto.productoId}</td>
							<td>${pedidoProducto.nombre}</td>
							<td>${pedidoProducto.cantidad}</td>
							<td>${pedidoProducto.precio}</td>
							<td><a href="borrar-producto-pedido?pCantId=${pedidoProducto.id}"><button
										class="glyphicon glyphicon-remove" style="color:red">
									</button></a>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="alineado-centro">
				<a href="finalizar-pedido?clienteId=${cliente.telefono}"><button
						class="btn btn-primary">Finalizar</button></a>
			</div>
		</section>
		<div class="error">
			<h6>${error }</h6>
		</div>
	</div>
</body>
</html>