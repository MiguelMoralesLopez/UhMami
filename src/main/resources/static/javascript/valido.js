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
var errorE = false
var errorT = false
// Función para mostrar u ocultar el ícono de advertencia según la condición
function mostrarAdvertencia(mostrar) {
    if (mostrar) {
      warningIcon.style.display = "inline-block";
    } else {
      warningIcon.style.display = "none";
    }
  }



//Añado un escucha para cada input

nombre.addEventListener('focusout', () => {
    var nom = nombre.value.trim();
    
    if (nom=== "") {
        console.log('estoy en nombre vacio')
        mostrarAdvertencia(true);        
        nombre.style.color = '#E13600'
        nombre.value = "Introduzca el nombre"
        
        nombre.addEventListener('focusin',() => {
            mostrarAdvertencia(false);
            if(nom === ""){
            nombre.value = ''
            nombre.style.color = '#616D69'
            nombre.placeholder = ''
            }
        })

    }
   
})

//Aviso de apellido vacío
apellido.addEventListener('focusout', () => {
    var ape = apellido.value.trim();
    console.log('Ha terminado de escribir');
    if (ape=== "") {
        console.log('estoy en apellido vacio')
        mostrarAdvertencia(true);        
        apellido.style.color = '#E13600'
        apellido.value = "Introduzca el apellido"
        
    }
apellido.addEventListener('focusin',() => {
    mostrarAdvertencia(false);
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
        mostrarAdvertencia(true);        
        email.style.color = '#E13600'
        email.value = "Introduzca el email"


        email.addEventListener('focusin',() => {
            mostrarAdvertencia(false);
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
                errorE = true
                
            }
            else{
                errorE = false
                errorEmail.textContent=''
             } 
        }
        }   

        
})


//aviso de telefono incorrecto
telefono.addEventListener('focusout', () => {
    const tel = telefono.value.trim();
    
    if (tel== "") {
        console.log('estoy en telefono vacio')
        mostrarAdvertencia(true);        
        telefono.style.color = '#E13600'
        telefono.value = "Introduzca el telefono"


        telefono.addEventListener('focusin',() => {
            mostrarAdvertencia(false);
            telefono.value = ''
            telefono.style.color = '#616D69'
            telefono.placeholder = ''
        })      
    }
    else{
        var telefonoRegex = /(\+34|0034|34)?[-]*(6|7)[ -]*([0-9][ -]*){8}/;
            if (!telefonoRegex.test(tel)) {
                console.log('no pasa')
                if(errorE == true){
                errorEmail.style.color ='#E13600'
                errorEmail.textContent='Formato email y telefono incorrectos'
                }
                else{
                    errorEmail.style.color ='#E13600'
                    errorEmail.textContent='Formato telefono incorrecto'
                }
     
            }
            else{
                errorEmail.textContent=''
                
        } 
        
    }
})


//Aviso de elegir los comensales

var elegirComensal = 0
comensales.addEventListener('focusin', ()=>{
    
    elegirComensal = 1
})


fecha.addEventListener('focusin', ()=> {
    if(elegirComensal == 0){
        alert('Por favor, dígamos cuantos comensales son.')
        comensales.focus()
        elegirComensal = 1
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
   
   

