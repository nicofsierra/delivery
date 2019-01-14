<%@include file="../../includes/head.jsp"%>


<body>
	<div class="container">

		<%@include file="../../includes/header.jsp"%>

		<section>


			<div class="row">
				<div class="col-md-1">
					<label for="telefono">Telefono</label>
				</div>
				<div class="col-md-11">
					<input disabled type="text" class="form-control"
						placeholder="Ingrese el nro de Telefono" name="telefono"
						value="${telefono}" />
				</div>
				<c:set var="cliente" value="${cliente}" scope="session" />
				<c:if test="${empty cliente.telefono }">
					<div class="alineado-centro">
						<p>
							<button class="btn btn-primary" type="button"
								data-toggle="collapse" data-target="#collapseExample"
								aria-expanded="false" aria-controls="collapseExample">
								Cliente NO EXISTE - CREAR</button>
						</p>
					</div>
					<div class="collapse" id="collapseExample">
						<div class="card card-body">
							<form:form action="nuevo-cliente" method="POST"
								modelAttribute="clienteNuevo">
								<form:input type="hidden" value="${telefono}" path="telefono" />
								<div class="form-group">
									<label for="nombre">Nombre</label>
									<form:input type="text" class="form-control"
										placeholder="Nombre" path="nombre" required="REQUIRED" />
								</div>
								<div class="form-group">
									<label for="nombre">Domicilio</label>
									<form:input type="text" class="form-control"
										placeholder="Calle - Nro" path="calle" required="REQUIRED" />
								</div>
								<div class="form-group">
									<label for="nombre">Localidad</label>
									<form:input type="text" class="form-control"
										placeholder="Localidad" path="localidad" required="REQUIRED" />
								</div>
								<div class="alineado-centro">
									<button type="submit" class="btn btn-primary">Continuar</button>
								</div>
							</form:form>
						</div>
					</div>
				</c:if>
				<c:if test="${not empty cliente.telefono }">
					<form action="guardar-cambios-cliente" method="GET">
					<div class="cliente-cargado">
					<input type="hidden" value="${cliente.telefono }" name="telefono"/>
						<label for="nombre">Nombre:</label> <input type="text" class="form-control"
							value="${cliente.nombre }" name="nombre"/> <br> <label for="calle">Calle</label>
						<input type="text" value="${cliente.calle }" class="form-control" name="calle"/> <br> <label
							for="localidad">Localidad:</label> <input type="text"
							value="${cliente.localidad }" class="form-control" name=localidad />
						<div class="alineado-centro">
							<button type="submit" class="btn btn-primary">Guardar</button>
						</div>
					</div>
					</form>
					<div class="alineado-centro">
						<a href="preparar-pedido"><button class="btn btn-primary">Continuar</button></a>
						<a href="volver-cliente"><button class="btn btn-primary">Volver</button></a>
					</div>
				</c:if>
			</div>
			<div class="error">
				<h6>${error }</h6>
			</div>

		</section>
	</div>

</body>
</html>