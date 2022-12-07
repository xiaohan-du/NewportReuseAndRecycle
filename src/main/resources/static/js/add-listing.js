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
})