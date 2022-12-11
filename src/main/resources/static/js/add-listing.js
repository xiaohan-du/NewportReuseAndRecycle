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

if (window.location.pathname.startsWith("/listings/edit")) {
    let latitude = document.getElementById("latitude");
    let longitude = document.getElementById("longitude");

    showMap(latitude.value, longitude.value);

    let addressField1 = document.getElementById("ship-address");
    let postalTown = document.getElementById("postal_town");
    let state = document.getElementById("state");
    let postcode = document.getElementById("postcode");
    let country = document.getElementById("country");

    let requiredText = document.getElementById("required-prompt");
    requiredText.style.display = "none";

    // These fields aren't required when editing, since
    // the long and lat coords are saved in the HTML separately
    // in hidden input dialogues
    addressField1.required = false;
    postalTown.required = false;
    state.required = false;
    postcode.required = false;
    country.required = false;

    let labels = document.getElementsByTagName("label");

    for (let label of labels) {
        let text = label.innerText;
        if (text.endsWith("*")) {
            label.innerText = text.replace("*", "");
        }
    }
}

let imageField = document.getElementById("image");
imageField.value = "";
