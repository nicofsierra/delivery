function confirmarCancelar(){
	var conf = confirm("Esta seguro de eliminar el PEDIDO?");
	if ( conf == true )
		return true;
	else
		return false;
}