<%@include file="../../includes/head.jsp"%>


<body>
	<div class="container">

		<%@include file="../../includes/header.jsp"%>

		<section>
			<c:set var="usuario" value="${usuario}" scope="session" />
			<c:if test="${not empty usuario.nombre }">

				<div class="alineado-centro">
					<a href="index"><button class="btn btn-primary">Volver</button></a>
					<a href="prepara-alta"><button class="btn btn-primary">Cargar</button></a>
					<a href="ver-todos"><button class="btn btn-primary">Ver
							Todos</button></a>
				</div>
			</c:if>
			<c:if test="${empty usuario.nombre }">
				<p>prohibido</p>
			</c:if>
			<div class="error">
					<h6>${error }</h6>
			</div>
		</section>
	</div>


</body>
</html>