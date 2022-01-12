(function () {
    const tableBody = document.querySelector('.table__content');
    const ul = document.querySelector('.pagination__items');
    const tableSortItem = document.querySelector('.surname');
    const tableArrow = document.querySelector('.table__arrow');

    const notesOnPage = 20;
    let sortValue = 'desc';

    document.addEventListener('DOMContentLoaded', init);
    tableSortItem.addEventListener('click', function () {
        tableArrow.classList.toggle('reverse');
        if (sortValue === 'desc') {
            sortValue = 'asc'
        } else {
            sortValue = 'desc';
        }
        listReader(sortValue, notesOnPage).then(value => {
            contentRender(value);
        });
    })

    function fillPages(rangeWithDots, totalPage) {
        for (const numPage of rangeWithDots) {
            const li = document.createElement('li');
            li.innerHTML = numPage;
            li.className = 'pagination__item';
            li.addEventListener('click', function () {
                pagination(totalPage, +this.innerHTML);
                listReader(sortValue, notesOnPage, this.innerHTML).then(value => {
                    contentRender(value);
                    this.classList.add('active');
                });
            });
            ul.appendChild(li);
        }
    }

    function pagination(data, page = 1) {
        //remove class active
        for (let child of ul.children) {
            child.classList.remove('active');
        }
        let totalPage = Math.ceil(data / notesOnPage);
        let currentPage = page;
        const delta = 2;
        let range = [];
            let rangeWithDots = [];
            let l;
        range.push(1);

        if (totalPage <= 1) {
            return range;
        }

        for (let i = currentPage - delta; i <= currentPage + delta; i++) {
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

        fillPages(rangeWithDots, totalPage);
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
            pagination(values[0], 1);
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