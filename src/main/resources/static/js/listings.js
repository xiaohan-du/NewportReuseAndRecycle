async function fetchListing() {
    const response = await fetch("http://localhost:8080/api/listings/all");
    if (!response.ok) {
        const message = `An error has occurred: ${response.status}`;
        throw new Error(message);
    } else {
        const listings = await response.json();
        return listings;
    }
};

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
        const data = await fetchResponse.json();
        console.log("in")
        return data;
    } catch (e) {
        return e;
    }
};

if (window.location.pathname == "/listings/add") {
    fetchCategories().then((categories) => {
        let dataList = document.getElementById("categories");
        categories.forEach((category) => {
            let optionElement = document.createElement("option");
            optionElement.innerText = category.category;
            dataList.appendChild(optionElement);
        });
        console.log(dataList)
    });
    const categoryDropdown = document.getElementById("categories");
    const selectElement = (id, valueToSelect) => {
        let categoryDropdown = document.getElementById("categories");
        categoryDropdown.value = valueToSelect;
    }
};

if (window.location.pathname == "/listings") {
    fetchListing().then((listings) => {
        const NUM_OF_COLS = 3;
        let numOfRows = listings.length / NUM_OF_COLS + 1;
        let initialResults = document.getElementById("initial-listings-grid");
        let sidebarResults = document.getElementById("sidebar-listings-grid");
        sidebarResults.innerHTML = '';
        for (let i = 0; i < numOfRows; i++) {
            let row = document.createElement("div");
            row.className = "row";
            for (let j = 0; j < NUM_OF_COLS; j++) {
                let curIndex = NUM_OF_COLS * i + j;
                if (curIndex == listings.length) {
                    initialResults.appendChild(row);
                    return;
                }
                let col = document.createElement("div");
                col.className = "col";
                img = document.createElement("img");
                img.src = listings[curIndex].imageUrl;
                img.height = "128";
                let pTitle = document.createElement("p");
                pTitle.innerText = `Title: ${listings[curIndex].title}`;
                let pDescription = document.createElement("p");
                pDescription.innerText = `Description: ${listings[curIndex].description}`;
                let pUserID = document.createElement("p");
                pUserID.innerText = `User ID: ${listings[curIndex].userId}`;
                let pPrice = document.createElement("p");
                pPrice.innerText = `Price: £${listings[curIndex].price.toFixed(2)}`;
                let pCategory = document.createElement("p");
                pCategory.innerText = `Category: ${listings[curIndex].category}`;
                let editBtn = document.createElement("button");
                editBtn.innerText = "Edit";

                editBtn.addEventListener("click", () => {
                    window.location.replace(`/listings/edit/${listings[curIndex].id}`);
                });

                col.classList.add('text-start');
                col.appendChild(img);
                col.appendChild(pTitle);
                col.appendChild(pDescription);
                col.appendChild(pUserID);
                col.appendChild(pPrice);
                col.appendChild(pCategory);
                col.appendChild(editBtn);
                row.append(col);
            }
            initialResults.append(row);
        }
    });
}