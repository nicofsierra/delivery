<%@include file="../../includes/head.jsp"%>


<body>
	<div class="container">

		<%@include file="../../includes/header.jsp"%>

		<section>
			<c:set var="usuario" value="${usuario}" scope="session" />
			<c:if test="${not empty usuario.nombre }">
				<form:form modelAttribute="producto" method="POST"
					action="alta-producto">
					<div class="row">
						<div class="col-md-4">
							<label for="id">ID PRODUCTO</label>
							<form:input type="text" path="id" class="form-control" />
						</div>
						<div class="col-md-4">
							<label for="nombre">DESCRIPCION</label>
							<form:input type="text" path="nombre" class="form-control" />
						</div>
						<div class="col-md-4">
							<label for="precio">PRECIO UNITARIO</label>
							<form:input type="text" path="precioUnitario"
								class="form-control" />
						</div>
					</div>
					<div class="alineado-centro">
						<button type="submit" class="btn btn-primary">Cargar</button>
					</div>

				</form:form>
				<div class="alineado-centro">
					<a href="ver-todos"><button class="btn btn-primary">Ver
							Todos</button></a>
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