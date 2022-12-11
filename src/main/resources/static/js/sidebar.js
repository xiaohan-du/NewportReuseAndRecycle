const handleSidebar = (btnId) => {
    fetchListingByCategory(btnId).then((listings) => {
        const NUM_OF_COLS = 3;
        let numOfRows = listings.length / NUM_OF_COLS + 1;

        let results = document.getElementById("listings-grid");

        results.innerHTML = "";

        for (let i = 0; i < numOfRows; i++) {
            let row = document.createElement("div");
            row.className = "row";

            for (let j = 0; j < NUM_OF_COLS; j++) {
                let curIndex = NUM_OF_COLS * i + j;

                if (curIndex == listings.length) {
                    results.appendChild(row);
                    return;
                }

                let col = document.createElement("div");
                col.className = "col-4 card";

                let img = document.createElement("img");
                img.src = listings[curIndex].imageUrl;
                img.width = img.height = "128";

                let pTitle = document.createElement("p");
                pTitle.innerText = `Title: ${listings[curIndex].title}`;

                let pDescription = document.createElement("p");
                pDescription.innerText = `Description: ${listings[curIndex].description}`;

                let pUserID = document.createElement("p");
                pUserID.innerText = `User ID: ${listings[curIndex].userId}`;

                let pPrice = document.createElement("p");
                pPrice.innerText = `Price: £${listings[curIndex].price.toFixed(
                    2
                )}`;

                let pCategory = document.createElement("p");
                pCategory.innerText = `Category: ${listings[curIndex].category}`;

                let pCollectionOrDelivery = document.createElement("p");
                pCategory.innerText = `Collection or delivery: ${listings[curIndex].collectionOrDelivery}`;

                let pLatitude = document.createElement("div");
                pLatitude.innerText = `Latitude: ${listings[curIndex].latitude}`;

                let pLongitude = document.createElement("div");
                pLongitude.innerText = `Longitude: ${listings[curIndex].longitude}`;

                col.classList.add("text-start");
                col.appendChild(img);
                col.appendChild(pTitle);
                col.appendChild(pDescription);
                col.appendChild(pUserID);
                col.appendChild(pPrice);
                col.appendChild(pCategory);
                col.appendChild(pCollectionOrDelivery);
                col.appendChild(pLatitude);
                col.appendChild(pLongitude);

                col.addEventListener("click", () => {
                    window.location.href = `/listings/${listings[curIndex].id}`;
                });

                row.append(col);
            }

            results.append(row);
        }
    });
};

handleSidebar();

fetchCategories().then((categories) => {
    let categoriesList = document.getElementById("sidebar-list");
    let allBtn = document.createElement("button");
    allBtn.className = "btn btn-warning w-100 mt-2";
    allBtn.innerHTML = "All";
    allBtn.id = "all";
    allBtn.addEventListener("click", () => {
        handleSidebar(allBtn.id);
    });
    categoriesList.appendChild(allBtn);
    categories.map((c) => {
        let btn = document.createElement("button");
        btn.className = "btn btn-warning w-100 mt-2";
        btn.innerHTML = c.category;
        btn.id = c.category;
        btn.addEventListener("click", () => {
            handleSidebar(btn.id);
        });
        categoriesList.appendChild(btn);
    });
});
