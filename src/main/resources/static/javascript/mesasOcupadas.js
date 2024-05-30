const URL_DESTINO = "http://localhost:8087/"
const RECURSO = "reservas/mesasBloqueadas"
const fecha = document.getElementById('fecha')
const hora = document.getElementById('horaReserva')



    hora.addEventListener('change', function getMesasOcupadas(){
        console.log('entro de getMesas')
            if(fecha.value!=''){
                console.log('haciendo llamada')
                    let xmlHttp = new XMLHttpRequest()
                    xmlHttp.onreadystatechange = function (){
                        if(this.readyState == 4){
                            if(this.status == 200){
                                procesarRespuesta(this.responseText)
                            } else {
                                alert("Ha habido un problema al recuperar las mesas para esta fecha y hora, por favor vuelve a intentarlo en unos segundos.")
                            }
                        }
                    }
                    xmlHttp.open("POST", URL_DESTINO + RECURSO, true)
                    xmlHttp.setRequestHeader('Content-Type', 'application/json')
                    
                    const mesaDto = {
                        fecha: fecha.value,
                        hora: hora.value
                    } 
                    xmlHttp.send(JSON.stringify(mesaDto));
                    const ids = ['mesa1', 'mesa2', 'mesa3', 'mesa4', 'mesa5', 'mesa6', 'mesa7', 'mesa8', 'mesa9', 'mesa10', 'mesa11', 'mesa12', 'mesa13']
                    const mesas = ids.map(id => document.getElementById(id))
                    function procesarRespuesta(responseText) {
                    
                
                        console.log('entro en procesar respuesta')
                
                        const response = JSON.parse(responseText)
                        let idMesas = response.idMesas
                        mesas.forEach(
                            mesa=> {
                                if(idMesas.includes(parseInt(mesa.value))){
                                    mesa.disabled = true
                                }
                                else{
                                    mesa.disabled = false
                                }
                                
                            }
                        )               
                        
                        }
                    }
                    
    
    }) 
    
    fecha.addEventListener('change', function getMesasOcupadas(){
        console.log('entro de getMesas')
            if(hora.value!=''){
                console.log('haciendo llamada')
                    let xmlHttp = new XMLHttpRequest()
                    xmlHttp.onreadystatechange = function (){
                        if(this.readyState == 4){
                            if(this.status == 200){
                                procesarRespuesta(this.responseText)
                            } else {
                                alert("Ha habido un problema al recuperar las mesas para esta fecha y hora, por favor vuelve a intentarlo en unos segundos.")
                            }
                        }
                    }
                    xmlHttp.open("POST", URL_DESTINO + RECURSO, true)
                    xmlHttp.setRequestHeader('Content-Type', 'application/json')
                    
                    const mesaDto = {
                        fecha: fecha.value,
                        hora: hora.value
                    } 
                    xmlHttp.send(JSON.stringify(mesaDto));
                    const ids = ['mesa1', 'mesa2', 'mesa3', 'mesa4', 'mesa5', 'mesa6', 'mesa7', 'mesa8', 'mesa9', 'mesa10', 'mesa11', 'mesa12', 'mesa13']
                    const mesas = ids.map(id => document.getElementById(id))
                    function procesarRespuesta(responseText) {
                    
                
                        console.log('entro en procesar respuesta')
                
                        const response = JSON.parse(responseText)
                        let idMesas = response.idMesas
                        mesas.forEach(
                            mesa=> {
                                if(idMesas.includes(parseInt(mesa.value))){
                                    mesa.disabled = true
                                }
                                else{
                                    mesa.disabled = false
                                }
                                
                            }
                        )
                        }
                    }
                    
    
    }) 





 
 