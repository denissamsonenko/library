(function() {
    const tableBody = document.querySelector('.table__content');
    const paginationItem = document.querySelector('.pagination__items');
    const tableSortItem = document.querySelector('.surname');
    const notesOnPage = 2;
    let sortValue = 'desc';

    document.addEventListener('DOMContentLoaded', init);

    tableSortItem.addEventListener('click', function () {

        if (sortValue === 'desc') {
            sortValue = 'asc'
        } else {
            sortValue = 'desc';
        }
        listReader(sortValue, notesOnPage).then(value => {
            contentRender(value);
        });
    })

    function printPage(data) {
        let countOfItems = Math.ceil(data / notesOnPage);
        for (let i = 1; i <= countOfItems; i++) {
            let li = document.createElement('li');
            li.className = 'pagination__item';
            li.innerHTML = `${i}`;

            li.addEventListener('click', function () {
                //TODO: should add className for emphasizing page
                listReader(sortValue, notesOnPage, this.innerHTML).then(value => {
                    contentRender(value);
                });
            })
            paginationItem.appendChild(li);
        }
    }

    function contentRender(data) {
        tableBody.innerHTML = '';
        for (let note of data) {
            let tr = document.createElement('tr');
            tableBody.appendChild(tr);
            fillCell(note.surname, tr);
            fillCell(note.name, tr);
            fillCell(note.birthDate, tr);
            fillCell(note.address, tr);
            fillCell(note.email, tr);
        }
    }

    function fillCell(text, tr) {
        this.innerHTML = '';
        let td = document.createElement('td');
        if (!text) {
            td.innerHTML = 'undefined';
        } else {
            td.innerHTML = text;
        }
        tr.appendChild(td);
    }

    function init() {
        Promise.all([getPagePagination(), listReader()]).then(values => {
            printPage(values[0]);
            contentRender(values[1]);
        });
    }

    async function listReader(sort = sortValue, limit = notesOnPage, offset = 1) {
        try {
            const response = await fetch(`http://localhost:8081/lib/controller?command=send_list_reader&offset=${offset}&limit=${limit}&sort=${sort}`);
            if (response.ok) {
                return await response.json();
            }
        } catch (error) {
            console.error(error.message)
        }
    }

    async function getPagePagination() {
        try {
            const response = await fetch(`http://localhost:8081/lib/controller?command=send_count_reader`);
            if (response.ok) {
                return await response.text();
            }
        } catch (error) {
            console.error(error.message)
        }
    }
})()