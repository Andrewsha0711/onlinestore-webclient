let form = document.querySelector('.js-form'),
    formInputs = document.querySelectorAll('.js-input'),
    inputEmail = document.querySelector('.js-input-email');
    inputPassword = document.querySelector('.js-input-password');
    inputPasswordAgain = document.querySelector('.js-input-password-again');

    function validateEmail(email) {
        let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(email).toLowerCase());
    }

    function validatePassword(password, passwordSecond) {
      if (password.length <= 6) {
        return "invalid_password:password_short"
      }
      else if (password.includes(' ')) {
        return "invalid_password:illegal_character"
      }
      else{
        if(password == passwordSecond){
          return "valid";
        }
        else {
          return "invalid_password:passwords_dont_match"
        }
      }
    }

    function validateCountry(country) {
        let re = new RegExp('.co$');
        return re.test(String(country).toLowerCase());
    }

    function validatePhone(phone) {
        let re = /^[0-9\s]*$/;
        return re.test(String(phone));
    }

    form.onsubmit = function () {
        let emailVal = inputEmail.value,
            passwordValue = inputPassword.value;
            passwordValueSecond = inputPasswordAgain.value;
            emptyInputs = Array.from(formInputs).filter(input => input.value === '');

        formInputs.forEach(function (input) {
            if (input.value === '') {
                input.classList.add('error');

            } else {
                input.classList.remove('error');
            }
        });
        if (inputEmail.value === '') {
            inputEmail.classList.add('error');

        } else {
            inputEmail.classList.remove('error');
        }
        if (emptyInputs.length !== 0) {
            console.log('inputs not filled');
            return false;
        }

        if(!validateEmail(emailVal)) {
            console.log('email not valid');
            inputEmail.classList.add('error');
            return false;
        } else {
            inputEmail.classList.remove('error');
        }

        if (validateCountry(emailVal)) {
            console.log('email from Columbia');
            inputEmail.classList.add('error');
            return false;
        } else {
            inputEmail.classList.remove('error');
        }
        let str = validatePassword(passwordValue, passwordValueSecond);
        if (str == 'valid') {
            inputPassword.classList.remove('error');
        } else {
            console.log('%s',str);
            inputPassword.classList.add('error');
            return false;
        }

    }

