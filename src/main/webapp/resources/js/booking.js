

document.addEventListener("DOMContentLoaded", function() {
    // Get all elements with the class "test"
    const buttons = document.querySelectorAll('.guestAmountBtn');


    // Loop through each button
    buttons.forEach(function(button) {
        // Add event listener for click event
        button.addEventListener('click', function() {
            openModal(2);
            closeModal(1);
        });
    });
});

// Function to open a modal
function openModal(modalId) {
    const modal = document.getElementById("modalStep" + modalId);
    modal.style.display = 'block';
    modal.setAttribute('aria-hidden', 'false');
    document.body.classList.add('modal-open'); // Add class to body
    document.documentElement.style.overflow = 'hidden'; // Prevent scrolling on html element
}

// Function to close a modal
function closeModal(modalId) {
    const modal = document.getElementById("modalStep" + modalId);
    modal.style.display = 'none';
    modal.setAttribute('aria-hidden', 'true');

    // Check if any modals are still open
    const modals = document.querySelectorAll('.modal')
    let isOpen = false;

    modals.forEach(modal => {
        if (modal.style.display === 'block') {
            isOpen = true;
        }
    });

    if (!isOpen) {
        document.body.classList.remove('modal-open'); // Remove class from body if no modals are open
        document.documentElement.style.overflow = 'hidden scroll'; // Restore scrolling on html element
    }
}




// Event listener for booking button
document.getElementById('bookingButton1').addEventListener('click', function() {
    openModal('1');
});

document.getElementById('bookingButton2').addEventListener('click', function() {
    openModal('1');
});
//bookingButton3
document.getElementById('bookingButton3').addEventListener('click', function() {
    openModal('1');
});
function validateDateTimeInputs() {
    // Get references to the input elements
    var dateInput = document.getElementById("j_idt17:datePicker_input");
    var timeInput = document.getElementById("j_idt17:timePicker_input");

    // Check if the inputs are empty
    var dateIsEmpty = dateInput.value.trim() === '';
    var timeIsEmpty = timeInput.value.trim() === '';

    // If both date and time are required fields
    if (dateIsEmpty || timeIsEmpty) {
        return false; // Return false if either date or time is empty
    } else {
        return true; // Return true if both date and time are provided
    }
}
function validateNamePhone() {
    const nameInput = document.getElementById('j_idt17:userName');
    const phoneInput = document.getElementById('j_idt17:userPhone');

    if (nameInput.value.trim() === '' || phoneInput.value.trim() === '') {
        // If either field is empty, disable the submit button and prevent form submission
        return false;
    } else {
        // Validate phone number for valid characters (digits, spaces, dashes, parentheses)
        const phoneNumber = phoneInput.value.trim();
        const phoneNumberRegex = /^[\d\s\-()]+$/; // Allow digits, spaces, dashes, and parentheses

        if (!phoneNumberRegex.test(phoneNumber)) {
            return false; // Prevent form submission if phone number contains invalid characters
        }

        // If both fields are filled and the phone number contains only valid characters, allow form submission
        return true;
    }
}

// Event listener for choosing number of guests

document.getElementById('j_idt17:saveBooking').addEventListener('click', function(event) {
    event.preventDefault(); // Prevent the default form submission behavior

    if (validateNamePhone()) {
        // Populate and show the confirmation
        let userName = document.getElementById('j_idt17:userName').value;
        let userPhone = document.getElementById('j_idt17:userPhone').value;
        document.getElementById('bookingDetails').innerText = "Bokningen sparades på namnet: " + userName + "\nTelefonnummer: " + userPhone;

        openModal('4'); // Show the modal with booking details

        // Delayed actions after showing the modal
        setTimeout(() => {
            closeModal('4'); // Hide the confirmation modal
            closeModal('3'); // You might want to check if you need to close another modal here

            // Refresh the page or perform other cleanup actions here
            // If you're handling form submission asynchronously,
            // you might want to include it here or adjust according to your app's flow
            window.location.reload(); // This refreshes the page
        }, 10000); // Adjust timing as needed

    } else {
        document.getElementById('invalidNamePhoneInput').style.display = "block";
    }
});

function resetForm() {
    document.getElementById('userName').value = '';
    document.getElementById('userPhone').value = '';
}

// Function to navigate back to the previous modal
function goBackModal(currentModalId, previousModalId) {
    closeModal(currentModalId);
    openModal(previousModalId);
}

function showMoreThanSixInfo() {
    const moreThanSixInfo = document.getElementById("moreThanSixInfo");
    moreThanSixInfo.style.display = "block";
}

function showInvalidDateInput() {
    const moreThanSixInfo = document.getElementById("invalidDateTimeInput");
    moreThanSixInfo.style.display = "block";
}

document.getElementById('chooseDateTime').addEventListener('click', async function() {
    if (validateDateTimeInputs()) {
        // Perform the availability check asynchronously
        try {
            const isAvailable = await checkAvailableBookings();
            if (isAvailable) {
                openModal('3');
                closeModal('2');
            } else {
                // Handle the case when bookings are full (isAvailable is false)
                // You might want to inform the user that no bookings are available
                // For example:
                showFullBooked();
            }
        } catch (error) {
            console.error('Error checking booking availability:', error);
            // Optionally handle errors, e.g., show an error message to the user
        }
    } else {
        showInvalidDateInput();
    }
});
function showFullBooked() {
    const moreThanSixInfo = document.getElementById("fullBooked");
    moreThanSixInfo.style.display = "block";
}

async function checkAvailableBookings() {

    const datePickerValue = document.getElementById("j_idt17:datePicker_input").value;
    const parts = datePickerValue.split('/'); // For "MM/DD/YYYY", split by "/"

    // Reorder the parts to "YYYY-MM-DD"
    // Adjust the indices [2], [1], [0] based on your input format
    const formattedDate = parts[2] + "-" + parts[1] + "-" + parts[0];

    const url = "http://localhost:8080/projektDT170G-1.0-SNAPSHOT/api/bookings?date=" + formattedDate;

    const response = await fetch(url);
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    const data = await response.json();
    return (data.length < 4);
}