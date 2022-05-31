
function checkPwdIdentity(){
    let password1 = document.getElementById("password1");
    let password2 = document.getElementById("password2");

    checkIdentity(password1, password2);
}

function checkMailIdentity(){
    let mail1 = document.getElementById("email1");
    let mail2 = document.getElementById("email2");

    checkIdentity(mail1, mail2);
}

function toggle_pwd(element, icon){
    let pwd = document.getElementById(element);
    let value = pwd.getAttribute('type') === 'password' ? 'text' : 'password';
    pwd.setAttribute('type',value);
    icon.classList.toggle('fa-eye-slash');
}

function checkIdentity(value1, value2){

    if(value1.value == value2.value && value1.value.length > 0){
        value1.style.border="solid green";
        value2.style.border="solid green";
    }else{
        value1.style.border="solid red";
        value2.style.border="solid red";
    }
}