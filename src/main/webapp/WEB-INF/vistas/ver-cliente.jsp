<%@include file="../../includes/head.jsp"%>


<body>
	<div class="container">

		<%@include file="../../includes/header.jsp"%>

		<section>

			<form action="verificar-cliente" method="GET">
				<div class="row">
					<div class="col-md-1">
						<label for="telefono" style="font-size: 22px">Telefono</label>
					</div>
					<div class="col-md-11">
						<input type="text" class="form-control"
							placeholder="Ingrese el nro de Telefono" name="telefono" />
					</div>
				</div>
				<div class="alineado-centro">
					<button type="submit" class="btn btn-primary">Continuar</button>
				</div>
			</form>
			<div class="alineado-centro">
				<a href="index"><button class="btn btn-primary">Volver</button></a>
			</div>
			<div class="alineado-centro">
				<a href="ver-pedidos"><button class="btn btn-primary">Ver Pedidos</button></a>
			</div>
			<div class="error">
				<h6>${error }</h6>
			</div>
		</section>
	</div>

</body>
</html>