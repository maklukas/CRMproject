$('.message a').click(function(){
	  $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
	  });


var password = document.getElementById("regPassword")
  , confirm_password = document.getElementById("confirmPassword");

function validatePassword(){
  if(password.value != confirm_password.value) {
    confirm_password.setCustomValidity("Passwords Don't Match");
  } else {
    confirm_password.setCustomValidity('');
  }
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;