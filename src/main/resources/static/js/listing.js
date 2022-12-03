async function fetchListing() {
    const response = await fetch("http://localhost:8080/api/listings/all");
    if (!response.ok) {
        const message = `An error has occured: ${response.status}`;
        throw new Error(message);
    } else {
        const listings = await response.json();
        return listings;
    }
}

fetchListing().then((listings) => {
    const NUM_OF_COLS = 3;
    let numOfRows = listings.length / NUM_OF_COLS + 1;

    let listingsGridElement = document.getElementById("listings-grid");

    for (let i = 0; i < numOfRows; i++) {
        let row = document.createElement("row");
        row.className = "row";

        for (let j = 0; j < NUM_OF_COLS; j++) {
            let curIndex = (NUM_OF_COLS * i) + j;

            if (curIndex == listings.length) {
                listingsGridElement.appendChild(row);
                return;
            }

            let col = document.createElement("div");
            col.className = "col";

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
            pPrice.innerText = `Price: ${listings[curIndex].price}`;

            let pCategory = document.createElement("p");
            pCategory.innerText = `Category: ${listings[curIndex].category}`;

            col.appendChild(img);
            col.appendChild(pTitle);
            col.appendChild(pDescription);
            col.appendChild(pUserID);
            col.appendChild(pPrice);
            col.appendChild(pCategory);

            row.append(col);
        }

        listingsGridElement.append(row);
    }
});
