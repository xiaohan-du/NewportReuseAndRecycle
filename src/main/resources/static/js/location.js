const viewType = {
    DIRECTIONS: "directions",
    PLACE: "place",
    SEARCH: "search",
    STREETVIEW: "streetview",
    VIEW: "view",
};

const API_KEY = "AIzaSyC1kqGLTqeYfGRpye5MMzepDONxxVxBJrQ";

let autocomplete;
let addressField1;
let addressField2;
let postcodeField;

let geocoder;

function requestLocation() {
    let location = navigator.geolocation;

    location.getCurrentPosition(showMap, showError);
}

function showMap(lat, long) {
    let locationRow = document.getElementById("location");

    // Populate the map's attributes
    let locationFrame = document.createElement("img");
    locationFrame.src = `https://maps.googleapis.com/maps/api/staticmap?size=256x256&zoom=17&scale=2&center=${lat},${long}&markers=color:red|${lat},${long}&key=${API_KEY}`;
    locationFrame.height = locationFrame.width = 256;

    locationRow.replaceChildren(locationFrame);

    setTimeout(() => {
        alert(`LAT: ${lat}\nLONG: ${long}`);
    }, 1000);
}

function showError(err) {
    alert(err);
}

function initGeocoder() {
    geocoder = new google.maps.Geocoder();
}

function initAutoComplete() {
    addressField1 = document.getElementById("ship-address");
    addressField2 = document.getElementById("address2");
    postcodeField = document.getElementById("postcode");

    autocomplete = new google.maps.places.Autocomplete(addressField1, {
        componentRestrictions: { country: ["uk"] },
        fields: ["address_components", "geometry", "place_id"],
        types: ["address"],
    });
    autocomplete.addListener("place_changed", fillAddress);
}

function fillAddress() {
    const PLACE = autocomplete.getPlace();
    const LOCATION = PLACE.geometry?.location;

    let address1 = "";
    let postcode = "";

    for (const COMPONENT of PLACE.address_components) {
        const COMPONENT_TYPE = COMPONENT.types[0];

        switch (COMPONENT_TYPE) {
            case "street_number": {
                address1 = `${COMPONENT.long_name} ${address1}`;
                break;
            }

            case "route": {
                address1 += COMPONENT.short_name;
                break;
            }

            case "postal_code_prefix": {
                postcode = `${COMPONENT.long_name}${postcode}`;
                break;
            }

            case "postal_code": {
                postcode = `${COMPONENT.long_name}${postcode}`;
                break;
            }

            case "postal_code_suffix": {
                postcode = `${postcode}-${COMPONENT.long_name}`;
                break;
            }

            case "postal_town":
                document.querySelector("#postal_town").value =
                    COMPONENT.long_name;
                break;

            case "administrative_area_level_1": {
                document.querySelector("#state").value = COMPONENT.short_name;
                break;
            }

            case "country":
                document.querySelector("#country").value = COMPONENT.long_name;
                break;
        }
    }

    addressField1.value = address1;
    postcodeField.value = postcode;

    addressField2.focus();

    if (LOCATION) {
        showMap(LOCATION.lat(), LOCATION.lng());
    }
}

initGeocoder();
initAutoComplete();
