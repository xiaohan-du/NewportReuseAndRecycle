async function fetchCategories() {
    const response = await fetch("http://localhost:8080/api/listings/categories");
    if (!response.ok) {
        const message = `An error has occurred: ${response.status}`;
        throw new Error(message);
    } else {
        const categories = await response.json();
        return categories;
    }
};

fetchCategories().then((categories) => {
    let categoriesDropdown = document.getElementById("categories");
    categories.map(c => {
        let dropdownItem = document.createElement("option");
        dropdownItem.innerHTML = c.category;
        categoriesDropdown.appendChild(dropdownItem);
    })
});

async function postEditRequest(id) {
    const location = window.location.hostname;
    const settings = {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        }
    };
    try {
        const fetchResponse = await fetch(`http://${location}:8080/api/listings/edit/${id}`, settings);
        return fetchResponse;
    } catch (e) {
        return e;
    }
};

const getUrlParts = (url) => {
    var a = document.createElement('a');
    a.href = url;

    return {
        pathname: a.pathname
    };
};

let submitBtn = document.getElementById("confirmBtn");
if (window.location.href.indexOf("/listings/edit") > -1) {
    const listUrl = getUrlParts(window.location.href).pathname;
    const listId = listUrl.replace('/listings/edit/','');
    let response;
    submitBtn.addEventListener("click", () => {
        postEditRequest(listId).then((response) => {
            if (response.status = 200) {
                window.location.replace('/listings');
            } else {
                alert("Edit listing error!")
            };
        });
    });
};
