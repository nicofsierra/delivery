<%@include file="../../includes/head.jsp"%>


<body>
	<div class="container">

		<%@include file="../../includes/header.jsp"%>

		<section>
			<c:set var="usuario" value="${usuario}" scope="session" />
			<c:if test="${not empty usuario.nombre }">
					<table class="table table-striped table-dark">
						<thead>
							<tr>
								<th scope="col">Id Producto</th>
								<th scope="col">Descripcion</th>
								<th scope="col">Precio Unitario</th>
								<th scope="col">Borrar</th>
								<th scope="col">Editar</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${productos}" var="producto">
								<tr>
									<td><input type="checkbox" name="id"
										value="${producto.id}"></td>
									<td scope="row">${producto.id}</td>
									<td>${producto.nombre}</td>
									<td>${producto.precioUnitario}</td>
									<td><a href="borrar-producto?id=${producto.id}"><button
										class="glyphicon glyphicon-remove" style="color:red">
									</button></a>
									<td><a href="editar?id=${producto.id}"><button
										class="glyphicon glyphicon-edit">
									</button></a>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				<div class="alineado-centro">
					<a href="abm-productos"><button class="btn btn-primary">Volver</button></a>
				</div>
				<div class="error">
					<h6>${error }</h6>
				</div>
			</c:if>
			<c:if test="${empty usuario.nombre }">
				<p>prohibido</p>
			</c:if>
		</section>

	</div>
</body>
</html>