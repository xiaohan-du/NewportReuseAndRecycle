fetchCategories().then((categories) => {
    let categoriesDropdown = document.getElementById("categories");
    categories.map((c) => {
        let dropdownItem = document.createElement("option");
        dropdownItem.innerHTML = c.category;
        categoriesDropdown.appendChild(dropdownItem);
    });
});

const getUrlParts = (url) => {
    var a = document.createElement("a");
    a.href = url;

    return {
        pathname: a.pathname,
    };
};

let submitBtn = document.getElementById("confirmBtn");

if (window.location.href.indexOf("/listings/edit") > -1) {
    const listUrl = getUrlParts(window.location.href).pathname;
    const listId = listUrl.replace("/listings/edit/", "");
    submitBtn.addEventListener("click", () => {
        postEditRequest(listId).then((response) => {
            if ((response.status = 200)) {
                window.location.replace("/listings");
            } else {
                alert("Edit listing error!");
            }
        });
    });
};

let imageField = document.getElementById("image");
imageField.value = "";
