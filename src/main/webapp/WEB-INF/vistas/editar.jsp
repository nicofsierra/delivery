<%@include file="../../includes/head.jsp"%>


<body>
	<div class="container">

		<%@include file="../../includes/header.jsp"%>

		<section>
			<c:set var="usuario" value="${usuario}" scope="session" />
			<c:if test="${not empty usuario.nombre }">
				<form  method="GET"
					action="graba-edicion">
					<div class="row">
						<div class="col-md-4">
							<label for="id">ID PRODUCTO</label>
							<input type="text" name="id" class="form-control" value="${producto.id}" readonly="readonly"/>
						</div>
						<div class="col-md-4">
							<label for="nombre">DESCRIPCION</label>
							<input type="text" name="nombre" class="form-control" value="${producto.nombre}" required="REQUIRED" />
						</div>
						<div class="col-md-4">
							<label for="precio">PRECIO UNITARIO</label>
							<input type="text" name="precioUnitario"
								class="form-control" value="${producto.precioUnitario}" required="REQUIRED"/>
						</div>
					</div>
					<div class="alineado-centro">
						<button type="submit" class="btn btn-primary">Aceptar</button>
					</div>

				</form>
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