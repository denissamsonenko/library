const inputs = document.querySelectorAll('input');
const form = document.querySelector('form');

form.addEventListener('submit', handleSubmit);

function handleSubmit(event) {
    event.preventDefault();
    if (validate()) {
        form.reset();
        createReader({
            name: form.name.value,
            surname: form.surname.value,
            middleName: form.middleName.value,
            passport: form.passport.value,
            birthDate: form.birthDate.value,
            email: form.email.value,
            address: form.address.value,
        });
    } else {
        console.log("validate fail")
    }

}
// validate
function validate() {
    let isValid = true;
    inputs.forEach(input => {
        if (!input.value.trim()) {
            input.style.borderColor = 'red'
            isValid = false;
        } else {
            input.style.borderColor = 'black'
        }
    });
    return isValid;
}

async function createReader(reader) {
    try {
        const response = await fetch('http://localhost:8081/lib/controller?command=create_reader', {
            method: 'POST',
            body: JSON.stringify(reader),
            headers: {
                'Content-Type': 'application/json',
            },
        });
        if (!response.ok) {
            throw new Error(response.statusText)
        }
    } catch (error) {
        //function
    }
}



