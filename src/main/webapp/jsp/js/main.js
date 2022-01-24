(function () {
    const tableBody = document.querySelector('.table__content');
    const ul = document.querySelector('.pagination__items');
    const nameRusColumn = document.querySelector('.name_rus');
    const availableBookColumn = document.querySelector('.rest_book');
    const arrowFromNameRus= document.querySelector('.arrow__nameRus');
    const arrowFromAvailable= document.querySelector('.arrow__restBook');

    const notesOnPage = 20;
    let sortColumn = 'all';
    let sortValue = 'desc';

    document.addEventListener('DOMContentLoaded', init);

    nameRusColumn.addEventListener('click', function () {
        sortColumn = 'name_ru';
        arrowFromNameRus.classList.toggle('reverse');
        sortValue = (sortValue === 'desc')? 'asc': 'desc';
        listBook(sortColumn, sortValue, notesOnPage, 1).then(value => {
            contentRender(value);
        });
    })

    availableBookColumn.addEventListener('click', function () {
        sortColumn = 'rest_book';
        arrowFromAvailable.classList.toggle('reverse');
        sortValue = (sortValue === 'desc')? 'asc': 'desc';
        listBook(sortColumn, sortValue, notesOnPage, 1).then(value => {
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

            li.addEventListener("click", function () {
                ul.innerHTML = '';
                const currentPage = +this.innerHTML;
                renderPage(totalPage, currentPage);
                listBook(sortColumn, sortValue, notesOnPage, currentPage).then(values => contentRender(values));
            });
        }
    }

    function contentRender(data) {
        tableBody.innerHTML = '';
        for (let note of data) {
            const genreName = note.genres.reduce(function (prev, cur) {
                return [...prev, cur.genreName]
            }, []);
            let year;
            if (note.publishDate) {
                year = new Date(note.publishDate).getFullYear();
            }

            let tr = document.createElement('tr');
            tableBody.appendChild(tr);
            fillCell(note.nameRus, tr);
            fillCell(genreName.join(', '), tr);
            fillCell(year, tr);
            fillCell(note.quantity, tr);
            fillCell(note.availableBook, tr);
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
        Promise.all([listBook(), getSizeBook()]).then(value => {
            contentRender(value[0]);
            pagination(value[1]);
        });
        listBook().then(values => contentRender(values));
    }

    async function listBook(sortColumn = 'all', sort = 'desc', limit = 20, offset = 1) {
        try {
            const response = await fetch(`http://localhost:8081/lib/controller?command=list_book&offset=${offset}&limit=${limit}&sort=${sort}&sort_value=${sortColumn}`);
            if (response.ok) {
                return await response.json();
            }
        } catch (error) {
            console.log(error.message)
        }
    }

    async function getSizeBook() {
        try {
            const response = await fetch(`http://localhost:8081/lib/controller?command=send_book_count`);
            if (response.ok) {
                return await response.text();
            }
        } catch (error) {
            console.error(error.message)
        }
    }
})()
