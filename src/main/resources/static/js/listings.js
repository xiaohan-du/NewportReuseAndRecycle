async function fetchListing() {
    const response = await fetch("http://localhost:8080/api/listings/all");
    if (!response.ok) {
        const message = `An error has occurred: ${response.status}`;
        throw new Error(message);
    } else {
        const listings = await response.json();
        return listings;
    }
}

async function fetchCategories() {
    const response = await fetch("http://localhost:8080/api/listings/categories");
    if (!response.ok) {
        const message = `An error has occurred: ${response.status}`;
        throw new Error(message);
    } else {
        const categories = await response.json();
        return categories;
    }
}

if (window.location.pathname == "/listings/add") {
    fetchCategories().then((categories) => {
        let dataList = document.getElementById("categories");

        categories.forEach((category) => {
            let optionElement = document.createElement("option");
            optionElement.innerText = category.category;

            dataList.appendChild(optionElement);
        })
    })
}

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

                let form = document.createElement("form");
                let returnUrl = window.location.protocol + "//" + window.location.host + `/listings/addReport`;
                form.action = returnUrl;
                form.method = "Post";
                let hiddenInputListingId = document.createElement("input");
                hiddenInputListingId.name = `listingid`;
                hiddenInputListingId.setAttribute(`value`, `${listings[curIndex].id}`);
                hiddenInputListingId.hidden = true;

                let hiddenInputUserId = document.createElement("input");
                hiddenInputUserId.name = `userid`;
                hiddenInputUserId.setAttribute(`value`, `${listings[curIndex].userId}`);
                hiddenInputUserId.hidden = true;

                let reason = document.createElement("input");
                reason.name = "reason";
                reason.setAttribute(`placeholder`, "Reason For Report");
                reason.setAttribute(`type`, "text");

                let reportButton = document.createElement("input");
                reportButton.value = "Report";
                reportButton.type = "submit";
                reason.required = true;



                col.classList.add('text-start');
            col.appendChild(img);
            col.appendChild(pTitle);
            col.appendChild(pDescription);
            col.appendChild(pUserID);
            col.appendChild(pPrice);
            col.appendChild(pCategory);
            col.appendChild(form);
                form.appendChild(hiddenInputListingId);
                form.appendChild(hiddenInputUserId);
                form.appendChild(reason);
                form.appendChild(reportButton);
                row.append(col);
            }

            initialResults.append(row);
        }
    });
}
