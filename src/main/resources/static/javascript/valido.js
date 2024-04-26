const nombre = document.getElementById("nombrereserva");
const apellido = document.getElementById('apellidoreserva');
const email = document.getElementById("emailreserva");
const telefono = document.getElementById("telefonoreserva");
const comensales = document.getElementById('comensalreserva')
const fecha = document.getElementById('fecha')
const hora = document.getElementById('horaReserva')
const politica = document.getElementById('politica')

const formulario = document.getElementById('formReserva')
const warningIcon = document.getElementById("warning-icon");
const errorEmail = document.getElementById('errorEmail')

var nomCorrecto = false
var apeCorrecto = false
var emCorrecto = false
var telCorrecto = false
var comenCorrecto = false
var fechaCorrecto = false
var horaCorrecto = false
var polCorrecto = false
var errorE = false
var errorT = false








//Añado un escucha para cada input

nombre.addEventListener('focusout', () => {
    var nom = nombre.value.trim();
    
    if (nom=== "") {
        console.log('estoy en nombre vacio')
               
        nombre.style.color = '#E13600'
        nombre.value = "Introduzca el nombre"
        
        nombre.addEventListener('focusin',() => {
           
            if(nom === ""){
            nombre.value = ''
            nombre.style.color = '#616D69'
            nombre.placeholder = ''
            } 
        })
        nomCorrecto = false

    }
    else{
        console.log('Nombre completado')
        nomCorrecto = true
    }
   
})

//Aviso de apellido vacío
apellido.addEventListener('focusout', () => {
    var ape = apellido.value.trim();
    if (ape=== "") {
        console.log('estoy en apellido vacio') 
        apellido.style.color = '#E13600'
        apellido.value = "Introduzca el apellido"
    }
    else{
        console.log('apellido completado')
        apeCorrecto = true
    }
    apellido.addEventListener('focusin',() => {
        apellido.value = ''
        apellido.style.color = '#616D69'
        apellido.placeholder = ''
    })   
})


//Aviso de email vacío

email.addEventListener('focusout', () => {
    var em = email.value.trim();
    if (em== "") {
        console.log('estoy en email vacio')
        email.style.color = '#E13600'
        email.value = "Introduzca el email"
        email.addEventListener('focusin',() => {
            email.value = ''
            email.style.color = '#616D69'
            email.placeholder = ''
        })      
    }
    else{
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if(em!= ""){
            if (!emailRegex.test(em)) {
                console.log('no pasa')
                errorEmail.style.color ='#E13600'
                errorEmail.textContent='Formato email incorrecto'
                errorEmail.style.fontSize = '20px'
                errorE = true
                
            }
            else{
                
                errorEmail.textContent=''
                errorE = false
             } 
        }
        }
        if(errorE == false){
            console.log('email compleado')
            emCorrecto = true
        }   

        
})


//aviso de telefono incorrecto
telefono.addEventListener('focusout', () => {
    const tel = telefono.value.trim();
    
    if (tel== "") {
        console.log('estoy en telefono vacio')
             
        telefono.style.color = '#E13600'
        telefono.value = "Introduzca el teléfono"


        telefono.addEventListener('focusin',() => {
            
            telefono.value = ''
            telefono.style.color = '#616D69'
            telefono.placeholder = ''
        })      
    }
    else{
        var telefonoRegex = /(\+34|0034|34)?[-]*(6|7)[ -]*([0-9][ -]*){8}/;
            if (!telefonoRegex.test(tel)) { //Si no pasa la prueba de telefono
                console.log('no pasa telefono')
                if(errorE == true){
                errorEmail.style.fontSize = '20px'
                errorEmail.style.color ='#E13600'
                errorEmail.textContent='Formato email y teléfono incorrectos'
                }
                else{
                    errorEmail.style.fontSize = '20px'
                    errorEmail.style.color ='#E13600'
                    errorEmail.textContent='Formato teléfono incorrecto'
                }
     
            }
            else{
                console.log('telefono completado')
                telCorrecto = true
                
        } 
        
    }
})


//Aviso de elegir los comensales

var elegirComensal = 0
comensales.addEventListener('focusin', ()=>{
    
    elegirComensal = 1
    comenCorrecto = true
    console.log('comensal elegido')

})


//No se puede elegir una fecha anterior a la de hoy
var fechaMin = new Date();
    var anio = fechaMin.getFullYear();
    var dia = fechaMin.getDate() + 1;
    var _mes = fechaMin.getMonth(); //viene con valores de 0 al 11
    _mes = _mes + 1; //ahora lo tienes de 1 al 12
    if (_mes < 10) //ahora le agregas un 0 para el formato date
    {
        var mes = "0" + _mes;
    } else {
        var mes = _mes.toString;
    }

    var fecha_minimo = anio + '-' + mes + '-' + dia;

fecha.setAttribute('min',fecha_minimo)
//Evitamos que el usuario no haya dejado los comensales sin elegir 
// y validamos que haya seleccionado una fecha
fecha.addEventListener('focusin', ()=> {
    if(elegirComensal == 0){
        alert('Por favor, dígamos cuantos comensales son.')
        elegirComensal = 1
        comensales.focus()
    }
    if(fecha.value != null){
        fechaCorrecto = true
    }
    else
    fechaCorrecto = false

})


//Validación de elegir hora

hora.addEventListener('click', () => {
    if(hora.value!=null){
        horaCorrecto = true
    }
    else{
        horaCorrecto = false
    }
})

//Validar el checkeo de las politicas de privacidad
politica.addEventListener('click',()=> {
    
    if(politica.checked == true){
        console.log('Ha aceptado')
        polCorrecto = true
    }
    else
    {
        console.log('No ha aceptado')
        polCorrecto = false
    }
})

formulario.addEventListener('submit',(e) => {
e.preventDefault();

})




function validateReservaForm() {
    //Creamos un valor que hasta que no sea validado sea false
    var validado = false

    if (nombre.value === "") {
        console.log('estoy en nombre vacio')
        
        nombre.placeholder = ("Introduzca el nombre")
 
    }
      return validado;

    }
   
   

