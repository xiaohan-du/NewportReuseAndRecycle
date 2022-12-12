async function fetchCategories() {
    const response = await fetch(
        "http://localhost:8080/api/listings/categories"
    );
    if (!response.ok) {
        const message = `An error has occurred: ${response.status}`;
        throw new Error(message);
    } else {
        const categories = await response.json();
        return categories;
    }
}

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

async function fetchListingByCategory(category) {
    if (category === undefined) category = "all";

    const response = await fetch(
        `http://localhost:8080/api/listings/${category}`
    );
    if (!response.ok) {
        const message = `An error has occured: ${response.status}`;
        throw new Error(message);
    } else {
        const listings = await response.json();
        return listings;
    }
}

// Currently not being used anywhere
async function postEditRequest(id) {
    const location = window.location.hostname;
    const settings = {
        method: "PUT",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
    };
    try {
        const fetchResponse = await fetch(
            `http://${location}:8080/api/listings/edit/${id}`,
            settings
        );
        return fetchResponse;
    } catch (e) {
        return e;
    }
}
