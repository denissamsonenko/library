(function () {
    const tableBody = document.querySelector('.table__content');
    const ul = document.querySelector('.pagination__items');
    const tableSortItem = document.querySelector('.surname');
    const tableArrow = document.querySelector('.table__arrow');

    const notesOnPage = 20;
    let sortValue = 'asc';

    document.addEventListener('DOMContentLoaded', init);
    tableSortItem.addEventListener('click', function () {
        tableArrow.classList.toggle('reverse');
        sortValue = (sortValue === 'desc') ? 'asc' : 'desc';
        listReader(sortValue, notesOnPage).then(value => {
            contentRender(value);
        });
    })

    function pagination(sizeData) {
        let countPage = Math.ceil(Number.parseInt(sizeData) / notesOnPage);
        renderPage(countPage, 1)
    }

    function renderPage(totalPage, currentPage = 1) {
        let page = currentPage;
        const delta = 2;
        let range = [];
        let rangeWithDots = [];
        let l;
        range.push(1);

        if (totalPage <= 1) {
            return range;
        }

        for (let i = page - delta; i <= page + delta; i++) {
            if (i < totalPage && i > 1) {
                range.push(i);
            }
        }
        range.push(totalPage);

        for (let i of range) {
            if (l) {
                if (i - l === 2) {
                    rangeWithDots.push(l + 1);
                } else if (i - l !== 1) {
                    rangeWithDots.push('...');
                }
            }
            rangeWithDots.push(i);
            l = i;
        }

        for (let rangeWithDot of rangeWithDots) {
            const li = document.createElement('li');
            li.innerHTML = rangeWithDot;
            li.className = 'pagination__item';
            ul.appendChild(li);
            if (li.innerHTML !== '...') {
                li.addEventListener("click", function () {
                    ul.innerHTML = '';
                    const currentPage = +this.innerHTML;
                    renderPage(totalPage, currentPage);
                    listReader(sortValue, notesOnPage, currentPage).then(value => contentRender(value));
                });
            }
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
            td.innerHTML = 'none';
        } else {
            td.innerHTML = text;
        }
        tr.appendChild(td);
    }

    function init() {
        Promise.all([listReader(), getSizeData()]).then(value => {
            contentRender(value[0]);
            pagination(value[1]);
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

    async function getSizeData() {
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