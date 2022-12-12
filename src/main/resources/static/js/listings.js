if (window.location.pathname == "/listings/add") {
    let titleElement = document.getElementById("title");
    titleElement.focus();

    fetchCategories().then((categories) => {
        let dataList = document.getElementById("categories");

        categories.forEach((category) => {
            let optionElement = document.createElement("option");
            optionElement.innerText = category.category;

            dataList.appendChild(optionElement);
        });
    });
}

if (window.location.pathname.startsWith("/listings/")) {
    let latInput = document.getElementById("latitude");
    let longInput = document.getElementById("longitude");

    showMap(latInput.value, longInput.value);
}
