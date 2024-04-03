// Function to open the popup
function openPopup() {
    document.getElementById('popupEvent').style.display = 'flex';
}

// Function to close the popup
function closePopup() {
    document.getElementById('popupEvent').style.display = 'none';
}

document.addEventListener('click', function(event) {
    var popup = document.getElementById('popupEvent');
    if (event.target !== popup && !popup.contains(event.target)) {
        popup.style.display = 'none';
    }
});


// Delay the popup to open 2 seconds after the DOM is loaded
document.addEventListener('DOMContentLoaded', function() {
    setTimeout(openPopup, 2000);
});

// Close the popup when clicking outside of it
window.addEventListener('click', function(event) {
    var popup = document.getElementById('popupEvent');
    if (event.target === popup) {
        closePopup();
    }
});

// Prevent closing when clicking inside the popup
document.querySelector('.popup-content').addEventListener('click', function(event) {
    event.stopPropagation();
});
