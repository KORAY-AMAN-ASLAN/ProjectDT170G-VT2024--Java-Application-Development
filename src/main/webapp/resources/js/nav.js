document.addEventListener('DOMContentLoaded', function() {
    const navToggle = document.querySelector('.nav-toggle');
    const menu = document.querySelector('#menu');
    const mainNav = document.querySelector('.main-nav');

    // Toggle menu visibility on navToggle click
    navToggle.addEventListener('click', function(e) {
        e.stopPropagation(); // Prevent click from propagating to window
        menu.classList.toggle('is-active'); // Use class to toggle visibility
    });

    // Stop propagation to prevent clicks within the main-nav from closing the menu
    menu.addEventListener('click', function(e) {
        e.stopPropagation();
    });

    // Close the menu when clicking anywhere outside
    window.addEventListener('click', function() {
        if (menu.classList.contains('is-active')) {
            menu.classList.remove('is-active');
        }
    });
});
