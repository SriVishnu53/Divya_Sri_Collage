(function() {
    // Initialize EmailJS with your user ID
    emailjs.init("user_your_emailjs_user_id");  // Replace with your EmailJS user ID

    // Handle form submission
    document.getElementById('contact-form').addEventListener('submit', function(event) {
        event.preventDefault();  // Prevent the form from submitting the traditional way

        // Collect form data
        const formData = {
            from_name: document.getElementById('name').value,
            from_email: document.getElementById('email').value,
            message: document.getElementById('message').value,
        };

        // Send email using EmailJS
        emailjs.send("service_your_service_id", "template_your_template_id", formData)
            .then(function(response) {
                // Show success message
                alert("Message sent successfully!");
                document.getElementById('contact-form').reset();  // Reset form after successful submission
            }, function(error) {
                // Show error message
                alert("Failed to send message. Please try again.");
                console.error("EmailJS Error: ", error);
            });
    });
})();
