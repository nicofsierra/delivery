<%@include file="../../includes/head.jsp"%>


<body>
	<div class="container">

		<%@include file="../../includes/header.jsp"%>
		<form:form modelAttribute="usuario" method="POST"
			action="validar-usuario">
			<div class="form-group">
				<label for="usuario">Usuario</label>
				<form:input type="text" class="form-control" path="nombre"
					placeholder="Usuario" />
			</div>
			<div class="form-group">
				<label for="password">Password</label>
				<form:input type="password" class="form-control"
					placeholder="Password" path="pass" />
			</div>
			<div class="error">
				<h6>${error }</h6>
			</div> 
			<button type="submit" class="btn btn-primary">Ingresar</button>

		</form:form>


	</div>
</body>
</html>
