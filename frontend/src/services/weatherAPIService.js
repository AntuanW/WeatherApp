import axios from 'axios'

const getWeather = () => {
    return new Promise((resolve, reject) => {
        axios.get("http://localhost:8080/weatherapp/weather", {
        headers: {'Access-Control-Allow-Credentials':true,
        'Authorization': sessionStorage.getItem("token")},
        responseType: "json",
        })
        .then(res => resolve(res.data))
        .catch(err => reject(err))
    })
}

const getWardrobe = () => {
    return new Promise((resolve, reject) => {
        axios.get("http://localhost:8080/weatherapp/wardrobe", {
        headers: {'Access-Control-Allow-Credentials':true,
        'Authorization': sessionStorage.getItem("token")},
        responseType: "json",
        })
        .then(res => {resolve(res.data)})
        .catch(err => {reject(err)})
    })
}

const postLocation = (data) => {
    console.log(data);
    let dataToSend = [];

    for (const key in data) {
        if (data.hasOwnProperty(key)) {
            const match = key.match(/^latitude(\d+)$/);
            if (match) {
                const index = match[1];
                const latitude = data[`latitude${index}`];
                const longitude = data[`longitude${index}`];
                const time = data[`time${index}`];

                if (latitude === '') break;

                const locationObject = { latitude, longitude, time };
                dataToSend.push(locationObject);

                const name = data[`name${index}`];
                if (name !== null && name !== undefined) {
                    const locationWithName = { ...locationObject, name };
                    addLocationToStorage(locationWithName)
                }
            }
        }
    }

    console.log(dataToSend)

    return new Promise((resolve, reject) => {
        axios.post("http://localhost:8080/users/configuration/location", 
        dataToSend, {
            headers: {'Access-Control-Allow-Credentials':true,
            'Authorization': sessionStorage.getItem("token")},
            responseType: "json",
        })
        .then(res => resolve(res))
        .catch(err => reject(err))
    })
}

const checkUser = () => {
    return new Promise((resolve, reject) => {
        axios.post("http://localhost:8080/users/configuration/checkUser", "aa",
        {
            headers: {
                'Access-Control-Allow-Credentials':true,
                'Authorization': sessionStorage.getItem("token")
            },
            responseType: "json",
        })
        .then(res => {
            resolve(res)})
        .catch(err => {
            console.log(err);
            if(err.response.status === 401) {
                console.log("new token")
                sessionStorage.removeItem("token")
            }
            reject(err)
        })
    })
}

const openUserSession = () => {
    return new Promise((resolve, reject) => {
        checkUser().then()
        .catch(e => console.log(e))
        .finally(() => {
            if(sessionStorage.getItem("token") === null) {
                axios.post("http://localhost:8080/users/configuration/user", 
                {headers: {
                        'Access-Control-Allow-Credentials':true,
                    },
                    responseType: "json",
                })
                .then(res => {
                    sessionStorage.setItem("token", res.data.token);
                    resolve(res)})
                .catch(err => reject(err)) 
            }

            resolve(sessionStorage.getItem("token"))
        })
    })
}

const addLocationToStorage = (newLocation) => {
    const savedLocations = getSavedLocationsFromStorage()
    const updatedList = [...savedLocations, newLocation];
    sessionStorage.setItem('savedLocations', JSON.stringify(updatedList));
};

const getSavedLocationsFromStorage = () => {
    const savedLocationsString = sessionStorage.getItem('savedLocations');
    return savedLocationsString ? JSON.parse(savedLocationsString) : [];
};

export {getWeather, getWardrobe, postLocation, openUserSession, getSavedLocationsFromStorage}