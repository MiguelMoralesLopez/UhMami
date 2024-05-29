function descargaPdf(idReserva) {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function (){
		if(this.readyState == 4){
			if(this.status == 200){
				if (this.responseText != null) {
		          const link = document.createElement('a');
		          link.href = 'data:application/pdf;base64,' + this.responseText;
		          link.download = 'ReservasUhmami ' + idReserva + '.pdf';
		          document.body.appendChild(link);
		          link.click();
		          document.body.removeChild(link);
		        }
			} else {
				alert ("Hubo un problema al realizar el pdf, por favor intentelo de nuevo");
			}
		}
	};
	
	xmlHttp.open('POST', 'http://localhost:8087/generarPdfCliente?id=' + idReserva, true);
	xmlHttp.setRequestHeader('Content-Type', 'application/json');
	
	
	const datos = {
		fecha: fecha,
	};
	xmlHttp.send(JSON.stringify(datos));
	
 };
 


 function enviarFormReserva(){
	
	
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function (){
		if(this.readyState == 4){
			if(this.status == 200){
				alert("Se ha creado la reserva con id: " + this.responseText);
				descargaPdf(this.responseText);
			} else {
				alert ("Hubo un problema al realizar la petición, por favor intentelo de nuevo");
			}
		}
	};
	xmlHttp.open('POST', "http://localhost:8087/reservar", true);
	xmlHttp.setRequestHeader('Content-Type', 'application/json');
	
	let nombre_reserva = document.getElementById("nombre").value;
	let apellidos_reserva = document.getElementById("apellidos").value;
	let email_reserva = document.getElementById("email").value;
	let telefono_reserva = document.getElementById("telefono").value;
	let comensales_reserva = document.getElementById("comensalreserva").value;
	let fecha_reserva = document.getElementById("fecha").value;
	let hora_reserva = document.getElementById("horaReserva").value;
	let mesa_reserva = document.getElementById("mesaSeleccionada").value;
	let observaciones_reserva = document.getElementById("observaciones").value;
	let datos = {
		nombre: nombre_reserva,
		apellidos: apellidos_reserva,
		email: email_reserva,
		telefono: telefono_reserva,
		comensales: comensales_reserva,
		fecha: fecha_reserva,
		hora: hora_reserva,
		mesa: mesa_reserva,
		observaciones: observaciones_reserva
	};
	xmlHttp.send(JSON.stringify(datos));
}