document.addEventListener('DOMContentLoaded', function () {
    async function listBook() {
        try {
            const response = await fetch('http://localhost:8081/lib/controller?command=list_book');
            return await response.json();
        } catch (error) {
            console.log(error)
        }
    }
})