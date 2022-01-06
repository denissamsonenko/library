document.addEventListener('DOMContentLoaded', function () {
    async function listReader() {
        try {
            const response = await fetch('http://localhost:8081/lib/controller?command=send_list_reader');
            return await response.json();
        } catch (error) {
            console.log(error)
        }
    }
})