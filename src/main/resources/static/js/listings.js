if (window.location.pathname == "/listings/add") {
    let titleElement = document.getElementById("title");
    titleElement.focus();

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


                col.classList.add('text-start');
            col.appendChild(img);
            col.appendChild(pTitle);
            col.appendChild(pDescription);
            col.appendChild(pUserID);
            col.appendChild(pPrice);
            col.appendChild(pCategory);
            col.appendChild(form);

            }

            initialResults.append(row);
        }
    });
}
