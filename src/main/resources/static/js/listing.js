async function fetchListing() {
    const response = await fetch('http://localhost:8080/api/listing');
    if (!response.ok) {
        const message = `An error has occured: ${response.status}`;
        throw new Error(message);
    } else {
        const listings = await response.json();
        return listings;
    }
}
document.getElementById("itemList").innerHTML = "Paragraph changed.";

fetchListing().then(listings => {
    console.log(listings);
});