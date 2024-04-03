
    window.addEventListener('scroll', function() {
    var scrollPosition = window.scrollY;
    var topLinks = document.querySelectorAll('.top-link');

    // Clear any existing timeouts to prevent multiple triggers
    topLinks.forEach(function(link) {
    clearTimeout(link.dataset.timeoutId);
});

    topLinks.forEach(function(link) {
    if (scrollPosition > window.innerHeight * 0.25) {
    // Set a timeout to add the 'show' class after 1 second (1000 milliseconds)
    var timeoutId = setTimeout(function() {
    link.classList.add('show');
}, 1000); // 1000 milliseconds = 1 second

    // Store timeoutId in the link element for potential clearTimeout
    link.dataset.timeoutId = timeoutId;
} else {
    link.classList.remove('show');
}
});
});


