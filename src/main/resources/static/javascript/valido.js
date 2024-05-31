const vnombre = document.getElementById('nombre');
const apellido = document.getElementById('apellidos');
const email = document.getElementById('email');
const telefono = document.getElementById("telefono");
const comensales = document.getElementById('comensalreserva')
const vfecha = document.getElementById('fecha')
const vhora = document.getElementById('horaReserva')
const politica = document.getElementById('politica')
const mapa =  document.querySelector("#plano_mesas > article.container")

const formulario = document.getElementById('formReserva')
const errorTel = document.getElementById('errorTel')
var mesaSeleccionada = document.getElementById('mesaSeleccionada')



//Validaciones 
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
var mesaCorrecta = false

//Añado un escucha para cada input

vnombre.addEventListener('focusout', () => {
    var nom = vnombre.value.trim();
    
    if (nom=== "") {
        console.log('estoy en nombre vacio')
               
        vnombre.style.color = '#E13600'
        vnombre.value = "Introduzca el nombre"
        
        vnombre.addEventListener('focusin',() => {
           
            if(nom === ""){
            vnombre.value = ''
            vnombre.style.color = '#616D69'
            vnombre.placeholder = ''
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
        var unaAlerta = 0
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if(em!= ""){
            if (!emailRegex.test(em)) {
                console.log('no pasa')
                if(unaAlerta == 0){
                    alert('Formato incorrecto')
                    errorE= true
                } 
            }
            else{
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
                    errorTel.style.fontSize = '20px'
                    errorTel.style.color ='#E13600'
                    errorTel.textContent='Formato teléfono incorrecto'
                    telCorrecto = false
            }
            else{
                errorTel.textContent = ''
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
    var dia = fechaMin.getDate() +1;
    var _mes = fechaMin.getMonth(); //viene con valores de 0 al 11
    _mes = _mes + 1; //ahora lo tienes de 1 al 12

    if(_mes === 9 || _mes === 4 || _mes ===6 || _mes ===10 || _mes ===10){
        if(dia===31){
            dia=1
            _mes+=1
        }
    }
    if(_mes === 1 || _mes === 3 || _mes ===5 || _mes ===10 || _mes ===7 || _mes ===8 || _mes ===9 || _mes ===12){
            if(dia===32){
                dia=1
                _mes+=1
            }
    if(_mes === 2){
        if(dia===29){
        dia=1
        _mes+=1
                }
            }
    
    }
    if (_mes < 10) //ahora le agregas un 0 para el formato date en los meses menor que 10
    {
        var mes = "0" + _mes;
    } else {
        var mes = _mes.toString;
    }

    if (dia < 10) //ahora le agregas un 0 para el formato date en los meses menor que 10
    {
        var dia = "0" + dia;
    }
    

    var fecha_minimo = anio + '-' + mes + '-' + dia;

vfecha.setAttribute('min',fecha_minimo)
vfecha.setAttribute('value',fecha_minimo)
//Evitamos que el usuario no haya dejado los comensales sin elegir 
// y validamos que haya seleccionado una fecha
vfecha.addEventListener('focusin', ()=> {
    if(elegirComensal == 0){
        alert('Por favor, dígamos cuantos comensales son.')
        elegirComensal = 1
        comensales.focus()
    }

    if(vfecha.value != null){
        console.log('fecha correcto')
        fechaCorrecto = true
    }
    else
    {
    console.log('Fecha sin seleccionar')
    fechaCorrecto = false
    }
})


//Validación de elegir hora

vhora.addEventListener('focusin',()=>{
    if(vhora.value!=''){
        console.log('hora elegida')
        horaCorrecto = true
    }
    else{
        horaCorrecto = false
    }})
    


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




//MESAS


mesaSeleccionada.value = 0
   
plano = document.getElementById('plano_mesas')


var mesa1 = document.getElementById('mesa1')

mesa1.addEventListener('click',() =>{
        parseInt(mesaSeleccionada.value = 1)   
        mesaCorrecta = true
        console.log('mesa 1 elegida')
        mesa1.style.backgroundImage = "url('../assets/img/2_cuadrada_naranja.png')"    
})

var mesa2 = document.getElementById('mesa2')

mesa2.addEventListener('click',() =>{
        
        console.log('mesa 2 elegida')
        mesaSeleccionada.value = 2  
        mesaCorrecta = true
        mesa2.style.backgroundImage = "url('../assets/img/4_cuadrada_naranja.png')"
   
})

var mesa3 = document.getElementById('mesa3')

mesa3.addEventListener('click',() =>{
        
        console.log('mesa 3 elegida')
        mesaSeleccionada.value = 3  
        mesaCorrecta = true
        mesa3.style.backgroundImage = "url('../assets/img/6_naranja.png')"
})

var mesa4 = document.getElementById('mesa4')

mesa4.addEventListener('click',() =>{
        
        console.log('mesa 4 elegida')
        mesaSeleccionada.value = 4 
        mesaCorrecta = true
        mesa4.style.backgroundImage = "url('../assets/img/4_redonda_naranja.png')"
   
})

var mesa5 = document.getElementById('mesa5')

mesa5.addEventListener('click',() =>{
        
        console.log('mesa 5 elegida')
        mesaSeleccionada.value = 5   
        mesaCorrecta = true
        mesa5.style.backgroundImage = "url('../assets/img/2_redonda_naranja.png')"
   
})

var mesa6 = document.getElementById('mesa6')

mesa6.addEventListener('click',() =>{
        
        console.log('mesa 6 elegida')
        mesaSeleccionada.value = 6  
        mesaCorrecta = true
        mesa6.style.backgroundImage = "url('../assets/img/2_redonda_naranja.png')"
   
})

var mesa7 = document.getElementById('mesa7')

mesa7.addEventListener('click',() =>{
        
        console.log('mesa 7 elegida')
        mesaSeleccionada.value = 7 
        mesaCorrecta = true
        mesa7.style.backgroundImage = "url('../assets/img/8_naranja.png')"
})

var mesa8 = document.getElementById('mesa8')

mesa8.addEventListener('click',() =>{
        
        console.log('mesa 8 elegida')
        mesaSeleccionada.value = 8   
        mesaCorrecta = true
        mesa8.style.backgroundImage = "url('../assets/img/2_redonda_naranja.png')"
   
})

var mesa9 = document.getElementById('mesa9')

mesa9.addEventListener('click',() =>{
        
        console.log('mesa 9 elegida')
        mesaSeleccionada.value = 9
        mesaCorrecta = true
        mesa9.style.backgroundImage = "url('../assets/img/2_redonda_naranja.png')"
   
})

var mesa10 = document.getElementById('mesa10')

mesa10.addEventListener('click',() =>{
        
        console.log('mesa 10 elegida')
        mesaSeleccionada.value = 10
        mesaCorrecta = true
        mesa10.style.backgroundImage = "url('../assets/img/2_cuadrada_naranja.png')"
})

var mesa11 = document.getElementById('mesa11')

mesa11.addEventListener('click',() =>{
        
        console.log('mesa 11 elegida')
        mesaSeleccionada.value = 11
        mesaCorrecta = true
        mesa11.style.backgroundImage = "url('../assets/img/4_cuadrada_naranja.png')"
})

var mesa12 = document.getElementById('mesa12')

mesa12.addEventListener('click',() =>{
        
        console.log('mesa 12 elegida')
        mesaSeleccionada.value = 12
        mesaCorrecta = true
        mesa12.style.backgroundImage = "url('../assets/img/6_naranja.png')"
   
})

var mesa13 = document.getElementById('mesa13')

mesa13.addEventListener('click',() =>{
        
        console.log('mesa 13 elegada')
        mesaSeleccionada.value = 13
        mesaCorrecta = true
        mesa13.style.backgroundImage = "url('../assets/img/4_redonda_naranja.png')"
   
})


plano.addEventListener('click',()=>{
    
    if(parseInt(mesaSeleccionada.value) != parseInt(mesa1.value) & mesa1.disabled === false){
        mesa1.style.backgroundImage = "url('../assets/img/2_cuadrada_verde.png')" 
    }
    if(parseInt(mesaSeleccionada.value) != parseInt(mesa2.value) & mesa2.disabled === false){
        mesa2.style.backgroundImage = "url('../assets/img/4_cuadrada_verde.png')" 
    } 
    if(parseInt(mesaSeleccionada.value) != parseInt(mesa3.value) & mesa3.disabled === false){
        mesa3.style.backgroundImage = "url('../assets/img/6_verde.png')" 
    }
    if(parseInt(mesaSeleccionada.value) != parseInt(mesa4.value)  & mesa4.disabled === false){
        mesa4.style.backgroundImage = "url('../assets/img/4_redonda_verde.png')" 
    }
    if(parseInt(mesaSeleccionada.value) != parseInt(mesa5.value)  & mesa5.disabled === false){
        mesa5.style.backgroundImage = "url('../assets/img/2_redonda_verde.png')" 
    }
    if(parseInt(mesaSeleccionada.value) != parseInt(mesa6.value)  & mesa6.disabled === false){
        mesa6.style.backgroundImage = "url('../assets/img/2_redonda_verde.png')" 
    }
    if(parseInt(mesaSeleccionada.value) != parseInt(mesa7.value)  & mesa7.disabled === false){
        mesa7.style.backgroundImage = "url('../assets/img/8_verde.png')" 
    }
    if(parseInt(mesaSeleccionada.value) != parseInt(mesa8.value)  & mesa8.disabled === false){
        mesa8.style.backgroundImage = "url('../assets/img/2_redonda_verde.png')" 
    }
    
    if(parseInt(mesaSeleccionada.value) != parseInt(mesa9.value)  & mesa9.disabled === false){
        mesa9.style.backgroundImage = "url('../assets/img/2_redonda_verde.png')" 
    }
    if(parseInt(mesaSeleccionada.value) != parseInt(mesa10.value)  & mesa10.disabled === false){
        mesa10.style.backgroundImage = "url('../assets/img/2_cuadrada_verde.png')" 
    }
    if(parseInt(mesaSeleccionada.value) != parseInt(mesa11.value)  & mesa11.disabled === false){
        mesa11.style.backgroundImage = "url('../assets/img/4_cuadrada_verde.png')" 
    }
    if(parseInt(mesaSeleccionada.value) != parseInt(mesa12.value)  & mesa12.disabled === false){
        mesa12.style.backgroundImage = "url('../assets/img/6_verde.png')" 
    }
    if(parseInt(mesaSeleccionada.value) != parseInt(mesa13.value)  & mesa13.disabled === false){
        mesa13.style.backgroundImage = "url('../assets/img/4_redonda_verde.png')" 
    }
    

})




function validateReservaForm() {
    //Creamos un valor que hasta que no sea validado sea false
    var validado = false
    
    if (vnombre.value === "" || apellido.value=="" || email.value=="" || telefono.value=="" ) {
        vnombre.focus()
        alert('Faltan campos de rellenar')
        validado = false
    }else{
    if(nomCorrecto === true && 
    apeCorrecto === true &&
    emCorrecto === true &&
    telCorrecto === true &&
    comenCorrecto === true &&
    fechaCorrecto === true &&
    horaCorrecto === true &&
    polCorrecto === true &&
    mesaCorrecta===true ){
        validado = true
        console.log('todo correcto')
        alert('Reserva realizada!')
    }}

      
    return validado;
    }
    
