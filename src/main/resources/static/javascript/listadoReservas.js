 function descargaPdf() {
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function (){
		if(this.readyState == 4){
			if(this.status == 200){
				if (this.responseText != null) {
		          const link = document.createElement('a');
		          link.href = 'data:application/pdf;base64,' + this.responseText;
		          link.download = 'ReservasUhmami ' + '.pdf';
		          document.body.appendChild(link);
		          link.click();
		          document.body.removeChild(link);
		        }
			} else {
				alert ("Hubo un problema al realizar la petición, por favor intentelo de nuevo");
			}
		}
	};
	
	const fecha = document.getElementById("fecha").value;
	xmlHttp.open('POST', 'http://localhost:8087/generarPdfAdmin?fecha=' + fecha, true);
	xmlHttp.setRequestHeader('Content-Type', 'application/json');
	xmlHttp.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem("token"));
	
	
	const datos = {
		fecha: fecha,
	};
	xmlHttp.send(JSON.stringify(datos));
	
 };

//Configuración para hacer llamada POST al endpoint que muestra el listado
//de reservas en base al día elegido. 
const URL_DESTINO_LISTA = "http://localhost:8087/";
const RECURSO_LISTA = "listaReservas";


function enviarPeticionLista(){
	
	 while (tabla.firstChild) {
        tabla.removeChild(tabla.firstChild);
    };
	let xmlHttp = new XMLHttpRequest();
	xmlHttp.onreadystatechange = function (){
		if(this.readyState == 4){
			if(this.status == 200){
				procesarRespuestaLista(this.responseText);
			} else {
				alert ("Hubo un problema al realizar la petición, por favor intentelo de nuevo");
			}
		}
	};
	xmlHttp.open('POST', URL_DESTINO_LISTA + RECURSO_LISTA, true);
	xmlHttp.setRequestHeader('Content-Type', 'application/json');
	//xmlHttp.setRequestHeader('Authorization', 'Bearer ' + sessionStorage.getItem("token"));
	
	const fecha = document.getElementById("fecha").value;
	const datos = {
		fecha: fecha,
	};
	console.log(fecha);
	xmlHttp.send(JSON.stringify(datos));
}

function procesarRespuestaLista(responseText){
	const response = JSON.parse(responseText);
	console.log(response);
	
	//Creo los títulos de la tabla
	let tr1 = document.createElement("tr");
	tr1.id = "titulos_tabla";
	let th1 = document.createElement("th");
	let h6_1 = document.createElement("h6");
	let contH6_1 = document.createTextNode("Hora");
	h6_1.appendChild(contH6_1);
	th1.appendChild(h6_1);
	tr1.appendChild(th1);
	
	let th2 = document.createElement("th");
	let h6_2 = document.createElement("h6");
	let contH6_2 = document.createTextNode("Nombre");
	h6_2.appendChild(contH6_2);
	th2.appendChild(h6_2);
	tr1.appendChild(th2);
	
	let th3 = document.createElement("th");
	let h6_3 = document.createElement("h6");
	let contH6_3 = document.createTextNode("Comensales");
	h6_3.appendChild(contH6_3);
	th3.appendChild(h6_3);
	tr1.appendChild(th3);
	
	let th4 = document.createElement("th");
	let h6_4 = document.createElement("h6");
	let contH6_4 = document.createTextNode("Mesa");
	h6_4.appendChild(contH6_4);
	th4.appendChild(h6_4);
	tr1.appendChild(th4);
	
	let th5 = document.createElement("th");
	let h6_5 = document.createElement("h6");
	let contH6_5 = document.createTextNode("Modificar");
	h6_5.appendChild(contH6_5);
	th5.appendChild(h6_5);
	tr1.appendChild(th5);
	//Añado la primera línea de la tabla a la misma
	tabla.appendChild(tr1)
	
	
	//Ahora leo el json que devuelve el servidor con el listado de reservas y las voy añadiendo una a una a la tabla
	for(let reserva of response){
		let tr2 = document.createElement("tr");
		tr2.id = "registros_tabla";
	
		let td1 = document.createElement("td");
		td1.className="td1";
		let td1_cont = document.createTextNode(reserva.hora.slice(0, 2) + ":" + reserva.hora.slice(2));
		td1.appendChild(td1_cont);
		tr2.appendChild(td1);
		
		let td2 = document.createElement("td");
		td2.className="td2";
		let td2_cont = document.createTextNode(reserva.cliente.nombre);
		td2.appendChild(td2_cont);
		tr2.appendChild(td2);
		
		let td3 = document.createElement("td");
		td3.className="td3";
		let td3_cont = document.createTextNode(reserva.comensales);
		td3.appendChild(td3_cont);
		tr2.appendChild(td3);
		
		let td4 = document.createElement("td");
		td4.className="td4";
		let td4_cont = document.createTextNode(reserva.mesa.id);
		td4.appendChild(td4_cont);
		tr2.appendChild(td4);
		
		let td5 = document.createElement("td");
		td5.className="td5";
		let td5_cont = document.createElement("button");
		td5_cont.className= "botones_modificar"
		td5.appendChild(td5_cont);
		tr2.appendChild(td5);
		tabla.appendChild(tr2);
	}
	
	
	
}

  