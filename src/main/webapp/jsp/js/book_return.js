async function searchOrder(email) {
    try {
        const response = await fetch(`http://localhost:8081/lib/controller?command=send_order&email=${email}`);
        if (response.ok) {
            return await response.json();
        }
    } catch (error) {
        console.error(error.message)
    }
}