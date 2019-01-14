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
					<c:forEach items="${detalles}" var="pedidoProducto">
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
					<td class="total">Vuelto </td>
					<td class="total">${vuelto}</td>	
					</tr>
			</table>
			<div class="row">
			<div class="col-md-6 text-center">
				<a href="volver-cliente"><button
						class="btn btn-primary">Volver</button></a>
			</div>
		</div>
		
		
		</section>
		<div class="error">
			<h6>${error }</h6>
		</div>
	</div>
</body>
</html>